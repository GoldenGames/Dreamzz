package me.mani.dreamzz;

import java.util.ArrayList;
import java.util.List;

import me.mani.dreamzz.util.TeamColor;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Team {

	private TeamColor teamColor;
	private int playerCount;
	
	private List<Player> players = new ArrayList<>();
	private boolean canRespawn = true;
	
	public Team(TeamColor teamColor, int playerCount) {
		this.teamColor = teamColor;
		this.playerCount = playerCount;
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public boolean containsPlayer(Player p) {
		return players.contains(p);
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void teleport(Location loc) {
		for (Player p : players)
			p.teleport(loc);
	}
	
	public TeamColor getTeamColor() {
		return teamColor;
	}
	
	public boolean canRespawn() {
		return canRespawn;
	}
	
	public void setRespawn(boolean canRespawn) {
		this.canRespawn = canRespawn;
	}
	
}
