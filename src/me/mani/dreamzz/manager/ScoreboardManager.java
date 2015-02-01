package me.mani.dreamzz.manager;

import me.mani.dreamzz.Team;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {
	
	private Scoreboard scoreboard;
	private Objective objective;
	
	public ScoreboardManager(Scoreboard scoreboard, Objective objective) {
		this.scoreboard = scoreboard;
		this.objective = objective;
	}

	public void setupTeams(TeamManager teamManager) {
		for (Team team : teamManager.getAllTeams()) {
			org.bukkit.scoreboard.Team scoreboardTeam = scoreboard.registerNewTeam(team.getTeamColor().getChatColor() + team.getTeamColor().getDisplayName());
			scoreboardTeam.setPrefix(team.getTeamColor().getChatColor().toString());
			scoreboardTeam.setAllowFriendlyFire(false);
			for (Player p : team.getPlayers())
				scoreboardTeam.addPlayer(p);
		}
	}
	
	public void update(TeamManager teamManager) {
		for (String s : scoreboard.getEntries())
			scoreboard.resetScores(s);
		for (Team team : teamManager.getAllTeams())
			objective.getScore((team.canRespawn() ? "§a" : "§7") + "█ " + team.getTeamColor().getChatColor() + team.getTeamColor().getDisplayName()).setScore(team.getPlayers().size());
	}
	
	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public Objective getObjective() {
		return objective;
	}

}
