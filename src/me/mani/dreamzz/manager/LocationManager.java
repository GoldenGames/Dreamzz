package me.mani.dreamzz.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import me.mani.dreamzz.Team;

import org.bukkit.Location;

public class LocationManager {

	private Location lobbySpawn;
	private Location statsDisplay;
	
	private HashMap<Team, Location> spawnLocations;
	private HashMap<Team, Location> bedLocations;
	private Location centerLocation;
	
	private List<Location> ressourceLocations = new ArrayList<>();;
	
	public LocationManager(Location spawnLocation, Location statsLocation, HashMap<Team, Location> spawnLocations, HashMap<Team, Location> bedLocations, Location centerLocation) {
		this.lobbySpawn = spawnLocation;
		this.statsDisplay = statsLocation;
		this.spawnLocations = spawnLocations;
		this.bedLocations = bedLocations;
		this.centerLocation = centerLocation;
	}
	
	public Location getLobbySpawn() {
		return lobbySpawn;
	}

	public Location getStatsDisplay() {
		return statsDisplay;
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
	
	public Location getCenterLocation() {
		return centerLocation;
	}
	
	public Team getTeam(Location bedLoc) {
		for (Entry<Team, Location> entry : bedLocations.entrySet())
			if (entry.getValue().equals(bedLoc))
				return entry.getKey();
		return null;
	}
	
}
