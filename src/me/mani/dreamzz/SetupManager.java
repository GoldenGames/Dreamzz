package me.mani.dreamzz;

import java.util.HashMap;

import me.mani.goldenapi.GoldenAPI;
import me.mani.goldenapi.mysql.ConvertUtil;
import me.mani.goldenapi.mysql.DatabaseManager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.NumberConversions;

public class SetupManager {

	private GameManager gameManager;
	private String mapName;
	private Map map;
	
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
			return false;
		}
		return true;
	}

	private void loadMySQL() throws Exception {
		GoldenAPI.setPlayerNameWatching(true);
		dbManager = GoldenAPI.connectToSQL(gameManager.getPlugin());
	}
	
	private void loadMap() throws Exception {
		World world = Bukkit.getWorlds().get(0);
		String displayName = String.valueOf(dbManager.get(Alias.MAP_TABLE, Alias.MAP_NAME, mapName, Alias.MAP_DISPLAY_NAME));
		String builderName = String.valueOf(dbManager.get(Alias.MAP_TABLE, Alias.MAP_NAME, mapName, Alias.MAP_BUILDER_NAME));
		int teamCount = NumberConversions.toInt(dbManager.get(Alias.MAP_TABLE, Alias.MAP_NAME, mapName, Alias.MAP_TEAM_COUNT));
		int playerCount = NumberConversions.toInt(dbManager.get(Alias.MAP_TABLE, Alias.MAP_NAME, mapName, Alias.MAP_PLAYER_COUNT));
		map = new Map(world, mapName, displayName, builderName, teamCount, playerCount);
	}
	
	private void loadLocations() throws Exception {
		HashMap<Team, Location> teamLocations = new HashMap<>();		
		for (int i = 1; i < map.getTeamCount(); i++) {
			TeamColor teamColor = TeamColor.values()[i - 1];
			if (String.valueOf(dbManager.get(Alias.LOC_TABLE, Alias.MAP_NAME, mapName, Alias.getTeamAlias(teamColor))).equals(Alias.LOC_EMPTY))
				continue;
			Location loc = ConvertUtil.toLocation(String.valueOf(dbManager.get(Alias.LOC_TABLE, Alias.MAP_NAME, mapName, Alias.getTeamAlias(teamColor))), map.getWorld());
			teamLocations.put(new Team(teamColor, map.getPlayerCount()), loc);
		}
		
		// TODO: Add spawn Locations
		
		new LocationManager(null, null, teamLocations);
	}
	
}
