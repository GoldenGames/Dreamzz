package me.mani.dreamzz;

import java.util.Arrays;

import me.mani.dreamzz.manager.CountdownCallback;
import me.mani.dreamzz.manager.CountdownCountEvent;
import me.mani.dreamzz.manager.CountdownManager;
import me.mani.dreamzz.manager.CountdownManager.Countdown;
import me.mani.dreamzz.manager.LocationManager;
import me.mani.dreamzz.manager.Players;
import me.mani.dreamzz.manager.ScoreboardManager;
import me.mani.dreamzz.manager.SetupManager;
import me.mani.dreamzz.manager.TeamManager;
import me.mani.dreamzz.ressource.RessourceManager;
import me.mani.dreamzz.shop.ShopManager;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GameManager {

	public static final String PREFIX = "§7[§aDreamzz§7] §6";
	
	private JavaPlugin plugin;
	public TeamManager teamManager;
	public LocationManager locationManager;
	public RessourceManager ressourceManager;
	public ShopManager shopManager;
	public ScoreboardManager scoreboardManager;
	
	private Map map;
	
	private Countdown gameCountdown;
	
	public GameManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void startBootstrap() {
		
		GameState.setGameState(GameState.SETUP);
		
		SetupManager setupManager = new SetupManager(this, "mapCargo");
		if(!setupManager.setup()) {
			stopBootstrap();
			return;
		}
		
		map = setupManager.getMap();
		teamManager = setupManager.getTeamManager();
		locationManager = setupManager.getLocationManager();
		ressourceManager = setupManager.getRessourceManager();
		shopManager = setupManager.getShopManager();
		scoreboardManager = setupManager.getScoreboardManager();
		
		GameState.setGameState(GameState.LOBBY);
	}
	
	public void stopBootstrap() {
		plugin.getServer().shutdown();
	}
	
	public boolean canStart() {
		return Players.getPlayerCount() >= Players.NEEDED_TO_START;
	}
	
	public boolean isStarting() {
		return gameCountdown != null;
	}
	
	public void startGameCountdown() {

		gameCountdown = CountdownManager.createCountdown(new CountdownCallback() {
			
			@Override
			public void onCountdownFinish() {
				startGame();
			}
			
			@Override
			public void onCountdownCount(CountdownCountEvent ev) {
				if (Arrays.asList(1,2,3,4,5,10,15,30,40).contains(ev.getCurrentNumber()))
					ev.setMessage(PREFIX + "Noch §e" + ev.getCurrentNumber() + " §6Sekunde" + (ev.getCurrentNumber() == 1 ? "" : "n") + ".");
				if (ev.getCurrentNumber() == 5) {
					Bukkit.broadcastMessage(
						  PREFIX + "<--------------------->" + "\n"
						+ PREFIX + "| Map:  §e§l" + map.getDisplayName() + "\n"
						+ PREFIX + "| Von:  §e§l" + map.getBuilderName() + "\n"
						+ PREFIX + "| Type: §e§l" + map.getTeamCount() + " x " + map.getPlayerCount() + "\n"
						+ PREFIX + "<--------------------->"
					);
				}
			}
			
		}, 30, 0, 20L);
		
	}
	
	public void cancelGameCountdown() {
		
		gameCountdown.forceStop();
		gameCountdown = null;
		
		Bukkit.broadcastMessage(PREFIX + "Der Countdown wurde wegen zu wenigen Spielern gestoppt!");
		
	}
	
	public void startGame() {
		
		GameState.setGameState(GameState.INGAME);
		
		int i = 0;
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (teamManager.getAllTeams().get(i) == null)
				i = 0;
			teamManager.getAllTeams().get(i).addPlayer(p);
			p.setNoDamageTicks(20);
			p.setScoreboard(scoreboardManager.getScoreboard());
			i++;
		}
		
		for (Team team : teamManager.getAllTeams())
			team.teleport(locationManager.getSpawnLocation(team));
		
		scoreboardManager.setupTeams(teamManager);
		scoreboardManager.update(teamManager);
		ressourceManager.startSpawning();
	}
	
	public void stopGame() {
		
		// TODO: Game end
		
	}

	public boolean onBedBreak(Location loc, Player p) {
		Team team = locationManager.getTeam(loc);
		if (team == null)
			return false;
		else if (team == teamManager.getTeam(p)) {	
			p.sendMessage(PREFIX + "Du kannst dein eigenes Bett nicht zerstören!");
			return false;
		}
		Bukkit.broadcastMessage(PREFIX + "Das Bett von Team " + team.getTeamColor().getChatColor() + team.getTeamColor().getDisplayName() + " §6wurde von §e"
				+ p.getName() + " §6zerstört.");
		Bukkit.broadcastMessage(PREFIX + "Die Spieler dieses Teams können sich nun §enicht §6mehr wiederbeleben.");
		map.getWorld().spigot().playEffect(loc.add(0.5, 0.5, 0.5), Effect.EXPLOSION_LARGE, 0, 0, 2, 2, 2, 0.5f, 10, 64);
		team.setRespawn(false);
		scoreboardManager.update(teamManager);
		return true;
	}
	
	public JavaPlugin getPlugin() {
		return plugin;
	}
	
}
