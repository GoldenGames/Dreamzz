package me.mani.dreamzz;

public class Alias {
	
	public static final String MAP_TABLE = "dreamzzMaps";
	public static final String MAP_NAME = "mapName";
	public static final String MAP_DISPLAY_NAME = "displayName";
	public static final String MAP_BUILDER_NAME = "builderName";
	public static final String MAP_TEAM_COUNT = "teamCount";
	public static final String MAP_PLAYER_COUNT = "playerCount";
	
	public static final String LOC_TABLE = "dreamzzLocs";
	public static final String LOC_EMPTY = "empty";
	
	public static String getTeamAlias(TeamColor teamColor) {
		return "team" + teamColor.toString().toUpperCase();
	}
	
	public static TeamColor getTeamColor(String alias) {
		return TeamColor.valueOf(alias.replace("team", ""));
	}

}
