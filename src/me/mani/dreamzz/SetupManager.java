package me.mani.dreamzz;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.mani.goldenapi.GoldenAPI;
import me.mani.goldenapi.mysql.ConvertUtil;
import me.mani.goldenapi.mysql.DatabaseManager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class SetupManager {

	private GameManager gameManager;
	private String mapName;
	
	private Map map;
	private TeamManager teamManager;
	private LocationManager locationManager;
	
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
		List<Team> allTeams = new ArrayList<>();
		HashMap<Team, Location> teamLocations = new HashMap<>();		
		for (int i = 1; i < map.getTeamCount(); i++) {
			TeamColor teamColor = TeamColor.values()[i - 1];
			if (!map.getMapInfo().contains("teams." + Alias.getTeamAlias(teamColor)))
				continue;
			Team team = new Team(teamColor, map.getPlayerCount());
			Bukkit.broadcastMessage(teamColor.toString());
			Location loc = ConvertUtil.toLocation(map.getMapInfo().getString("teams." + Alias.getTeamAlias(teamColor) + ".spawn"), map.getWorld());
			allTeams.add(team);
			teamLocations.put(team, loc);
		}
		
		// TODO: Add spawn Locations
		
		teamManager = new TeamManager(allTeams);
		locationManager = new LocationManager(null, null, teamLocations);
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
	
}
