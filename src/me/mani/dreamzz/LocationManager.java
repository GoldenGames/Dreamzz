package me.mani.dreamzz;

import java.util.HashMap;

import org.bukkit.Location;

public class LocationManager {

	private Location spawnLocation;
	private Location statsLocation;
	
	private HashMap<Team, Location> teamLocations;
	
	public LocationManager(Location spawnLocation, Location statsLocation, HashMap<Team, Location> teamLocations) {
		this.spawnLocation = spawnLocation;
		this.statsLocation = statsLocation;
		this.teamLocations = teamLocations;
	}
	
}
