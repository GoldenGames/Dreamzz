package me.mani.dreamzz;

import org.bukkit.World;

public class Map {
	
	private World world;
	private String mapName;
	private String displayName;
	private String builderName;
	private int teamCount;
	private int playerCount;
	
	private static Map currentMap;
	
	public Map(World world, String mapName, String displayName, String builderName, int teamCount, int playerCount) {
		this.world = world;
		this.mapName = mapName;
		this.displayName = displayName;
		this.builderName = builderName;
		this.teamCount = teamCount;
		this.playerCount = playerCount;
		currentMap = this;
	}

	public World getWorld() {
		return world;
	}

	public String getMapName() {
		return mapName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getBuilderName() {
		return builderName;
	}

	public int getTeamCount() {
		return teamCount;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public static Map getCurrentMap() {
		return currentMap;
	}
	
}
