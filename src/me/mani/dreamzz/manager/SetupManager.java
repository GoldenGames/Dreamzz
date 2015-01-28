package me.mani.dreamzz.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.mani.dreamzz.Alias;
import me.mani.dreamzz.GameManager;
import me.mani.dreamzz.Map;
import me.mani.dreamzz.Team;
import me.mani.dreamzz.ressource.Ressource;
import me.mani.dreamzz.ressource.RessourceManager;
import me.mani.dreamzz.ressource.RessourceType;
import me.mani.dreamzz.shop.ShopManager;
import me.mani.dreamzz.util.TeamColor;
import me.mani.goldenapi.GoldenAPI;
import me.mani.goldenapi.mysql.ConvertUtil;
import me.mani.goldenapi.mysql.DatabaseManager;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class SetupManager {

	private GameManager gameManager;
	private String mapName;
	
	private Map map;
	private TeamManager teamManager;
	private LocationManager locationManager;
	private RessourceManager ressourceManager;
	private ScoreboardManager scoreboardManager;
	private ShopManager shopManager;
	
	private DatabaseManager dbManager;
	
	public SetupManager(GameManager gameManager, String mapName) {
		this.gameManager = gameManager;
		this.mapName = mapName;
	}
	
	public boolean setup() {
		try {
			loadMySQL();
			loadMap();
			loadLocations();
			loadScoreboard();
			shopManager = new ShopManager();
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void loadMySQL() throws Exception {
		dbManager = GoldenAPI.connectToSQL(gameManager.getPlugin());
		GoldenAPI.setPlayerNameWatching(true);
	}
	
	private void loadMap() throws Exception {
		World world = Bukkit.createWorld(new WorldCreator("map"));
		world.setSpawnFlags(false, false);
		YamlConfiguration mapInfo = YamlConfiguration.loadConfiguration(new File(world.getWorldFolder(), "mapInfo.yml"));
		
		String displayName = mapInfo.getString(Alias.MAP_DISPLAY_NAME);
		String builderName = mapInfo.getString(Alias.MAP_BUILDER_NAME);
		int teamCount = mapInfo.getInt(Alias.MAP_TEAM_COUNT);
		int playerCount = mapInfo.getInt(Alias.MAP_PLAYER_COUNT);
		int mapRadius = mapInfo.getInt(Alias.MAP_RADIUS);
		map = new Map(world, mapName, displayName, builderName, teamCount, playerCount, mapRadius, mapInfo);
	}
	
	private void loadLocations() throws Exception {
		
		// Team Spawn and Bed Locations
		
		List<Team> allTeams = new ArrayList<>();
		HashMap<Team, Location> spawnLocations = new HashMap<>();
		HashMap<Team, Location> bedLocations = new HashMap<>();	
		for (int i = 0; i < map.getTeamCount(); i++) {
			TeamColor teamColor = TeamColor.values()[i];
			if (!map.getMapInfo().contains("teams." + Alias.getTeamAlias(teamColor)))
				continue;
			Team team = new Team(teamColor, map.getPlayerCount());
			System.out.println(teamColor.toString());
			Location spawn = ConvertUtil.toLocation(map.getMapInfo().getString("teams." + Alias.getTeamAlias(teamColor) + ".spawn"), map.getWorld());
			Location bed = ConvertUtil.toLocation(map.getMapInfo().getString("teams." + Alias.getTeamAlias(teamColor) + ".bed"), map.getWorld());
			allTeams.add(team);
			spawnLocations.put(team, spawn);
			bedLocations.put(team, bed);
		}
		
		Location lobbySpawn = ConvertUtil.toLocation(String.valueOf(dbManager.get(Alias.TABLE, Alias.SETTING, "lobbySpawn", Alias.VALUE)), Bukkit.getWorld("world"));
		Location centerLocation = ConvertUtil.toLocation(map.getMapInfo().getString(Alias.MAP_CENTER_LOCATION), map.getWorld());
		
		teamManager = new TeamManager(allTeams);
		locationManager = new LocationManager(lobbySpawn, null, spawnLocations, bedLocations, centerLocation);
		
		// Ressource Locations
		
		ressourceManager = new RessourceManager(gameManager.getPlugin());
			
		long timeBefore = System.currentTimeMillis();
		int blocksScanned = 0;
		
		for (int x = centerLocation.getBlockX() - map.getMapRadius(); x < (centerLocation.getBlockX() + map.getMapRadius()); x++) {
			for (int z = centerLocation.getBlockZ() - map.getMapRadius(); z < (centerLocation.getBlockZ() + map.getMapRadius()); z++) {
				Chunk c = map.getWorld().getChunkAt(x * 16, z * 16);
				if (!c.isLoaded())
					c.load();
				for (int y = 0; y < map.getWorld().getHighestBlockYAt(x, z); y++) {	
					Block b = map.getWorld().getBlockAt(x, y, z);
					if (b.getType() == RessourceType.CLAY.getBlockMaterial())
						ressourceManager.registerRessource(new Ressource(RessourceType.CLAY, b.getLocation()));
					else if (b.getType() == RessourceType.IRON.getBlockMaterial())
						ressourceManager.registerRessource(new Ressource(RessourceType.IRON, b.getLocation()));
					else if (b.getType() == RessourceType.GOLD.getBlockMaterial())
						ressourceManager.registerRessource(new Ressource(RessourceType.GOLD, b.getLocation()));
					blocksScanned++;
				}
			}
		}
		
		System.out.println(blocksScanned + " blocks scanned! Took " + (System.currentTimeMillis() - timeBefore) / 1000 + " sec.");
	}
	
	private void loadScoreboard() {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("§7[§aDreamzz§7]", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		scoreboardManager = new ScoreboardManager(scoreboard, objective);
	}
	
	public Map getMap() {
		return map;
	}
	
	public TeamManager getTeamManager() {
		return teamManager;
	}
	
	public LocationManager getLocationManager() {
		return locationManager;
	}
	
	public RessourceManager getRessourceManager() {
		return ressourceManager;
	}
	
	public ScoreboardManager getScoreboardManager() {
		return scoreboardManager;
	}
	
	public ShopManager getShopManager() {
		return shopManager;
	}
	
}
