package me.mani.dreamzz;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Team {

	private TeamColor teamColor;
	private int playerCount;
	
	private List<Player> players = new ArrayList<>();
	
	public Team(TeamColor teamColor, int playerCount) {
		this.teamColor = teamColor;
		this.playerCount = playerCount;
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public void teleport(Location loc) {
		for (Player p : players)
			p.teleport(loc);
	}
	
}
