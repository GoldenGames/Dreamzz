package me.mani.dreamzz;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.mani.dreamzz.ressource.Ressource;
import me.mani.dreamzz.ressource.RessourceManager;
import me.mani.dreamzz.ressource.RessourceType;
import me.mani.dreamzz.shop.ShopManager;
import me.mani.dreamzz.util.ItemUtil;
import me.mani.goldenapi.GoldenAPI;
import me.mani.goldenapi.mysql.ConvertUtil;
import me.mani.goldenapi.mysql.DatabaseManager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class SetupManager {

	private GameManager gameManager;
	private String mapName;
	
	private Map map;
	private TeamManager teamManager;
	private LocationManager locationManager;
	private RessourceManager ressourceManager;
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
		World world = Bukkit.getWorlds().get(0); // THIS IS CARGO, we need an system here
		YamlConfiguration mapInfo = YamlConfiguration.loadConfiguration(new File(world.getWorldFolder(), "mapInfo.yml"));
		
		String displayName = mapInfo.getString(Alias.MAP_DISPLAY_NAME);
		String builderName = mapInfo.getString(Alias.MAP_BUILDER_NAME);
		int teamCount = mapInfo.getInt(Alias.MAP_TEAM_COUNT);
		int playerCount = mapInfo.getInt(Alias.MAP_PLAYER_COUNT);
		map = new Map(world, mapName, displayName, builderName, teamCount, playerCount, mapInfo);
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
		
		// TODO: Add Lobby Spawn Location
		
		teamManager = new TeamManager(allTeams);
		locationManager = new LocationManager(null, null, spawnLocations, bedLocations);
		
		// Ressource Locations
		
		ressourceManager = new RessourceManager(gameManager.getPlugin());
				
		for (String s : map.getMapInfo().getStringList("goldSpawner"))
			ressourceManager.registerRessource(new Ressource(RessourceType.GOLD, ConvertUtil.toLocation(s, map.getWorld())));
		for (String s : map.getMapInfo().getStringList("ironSpawner"))
			ressourceManager.registerRessource(new Ressource(RessourceType.IRON, ConvertUtil.toLocation(s, map.getWorld())));
		for (String s : map.getMapInfo().getStringList("claySpawner"))
			ressourceManager.registerRessource(new Ressource(RessourceType.CLAY, ConvertUtil.toLocation(s, map.getWorld())));
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
	
	public ShopManager getShopManager() {
		return shopManager;
	}
	
}
