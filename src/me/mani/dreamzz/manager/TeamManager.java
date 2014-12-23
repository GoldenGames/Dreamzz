package me.mani.dreamzz.manager;

import java.util.List;

import me.mani.dreamzz.Team;

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
