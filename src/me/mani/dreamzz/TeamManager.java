package me.mani.dreamzz;

import java.util.List;

import org.bukkit.entity.Player;

public class TeamManager {
	
	private List<Team> allTeams;
	
	public TeamManager(List<Team> allTeams) {
		this.allTeams = allTeams;
	}
	
	public List<Team> getAllTeams() {
		return allTeams;
	}
	
	public Team getTeam(Player p) {
		for (Team team : allTeams)
			if (team.containsPlayer(p))
				return team;
		return null;
	}

}
