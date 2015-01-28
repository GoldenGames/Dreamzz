package me.mani.dreamzz;

import me.mani.dreamzz.util.TeamColor;

public class Alias {
	
	public static final String TABLE = "dreamzz";
	public static final String SETTING = "setting";
	public static final String VALUE = "value";
	
	public static final String MAP_NAME = "mapName";
	public static final String MAP_DISPLAY_NAME = "displayName";
	public static final String MAP_BUILDER_NAME = "builderName";
	public static final String MAP_TEAM_COUNT = "teamCount";
	public static final String MAP_PLAYER_COUNT = "playerCount";
	public static final String MAP_RADIUS = "mapRadius";
	public static final String MAP_CENTER_LOCATION = "centerLocation";
	
	public static String getTeamAlias(TeamColor teamColor) {
		return "team" + teamColor.toString().toUpperCase();
	}
	
	public static TeamColor getTeamColor(String alias) {
		return TeamColor.valueOf(alias.replace("team", ""));
	}

}
