package me.mani.dreamzz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Location;

public class LocationManager {

	private Location spawnLocation;
	private Location statsLocation;
	
	private HashMap<Team, Location> spawnLocations;
	private HashMap<Team, Location> bedLocations;
	
	private List<Location> ressourceLocations = new ArrayList<>();;
	
	public LocationManager(Location spawnLocation, Location statsLocation, HashMap<Team, Location> spawnLocations, HashMap<Team, Location> bedLocations) {
		this.spawnLocation = spawnLocation;
		this.statsLocation = statsLocation;
		this.spawnLocations = spawnLocations;
		this.bedLocations = bedLocations;
	}
	
	public void addRessource(Location loc) {
		ressourceLocations.add(loc);
	}
	
	public Location getSpawnLocation(Team team) {
		return spawnLocations.get(team);
	}
	
	public Location getBedLocation(Team team) {
		return bedLocations.get(team);
	}
	
	public Team getTeam(Location bedLoc) {
		for (Entry<Team, Location> entry : bedLocations.entrySet())
			if (entry.getValue().equals(bedLoc))
				return entry.getKey();
		return null;
	}
	
}
