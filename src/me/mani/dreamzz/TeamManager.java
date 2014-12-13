package me.mani.dreamzz;

import java.util.List;

public class TeamManager {
	
	private List<Team> allTeams;
	
	public TeamManager(List<Team> allTeams) {
		this.allTeams = allTeams;
	}
	
	public List<Team> getAllTeams() {
		return allTeams;
	}

}
