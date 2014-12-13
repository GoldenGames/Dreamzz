package me.mani.dreamzz;

import java.util.Arrays;
import java.util.Iterator;

import me.mani.dreamzz.CountdownManager.Countdown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GameManager {

	public static final String PREFIX = "§7[§aDreamzz§7] §6";
	
	private JavaPlugin plugin;
	private TeamManager teamManager;
	private LocationManager locationManager;
	private Map map;
	
	public GameManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void startBootstrap() {
		
		GameState.setGameState(GameState.SETUP);
		
		// TODO: Add Map choosing system. For now only cargo works
		
		SetupManager setupManager = new SetupManager(this, "mapCargo");
		if(!setupManager.setup()) {
			stopBootstrap();
			return;
		}
		
		map = setupManager.getMap();
		teamManager = setupManager.getTeamManager();
		locationManager = setupManager.getLocationManager();
		
		startGameLobby();
	}
	
	public void stopBootstrap() {
		plugin.getServer().shutdown();
	}
	
	public void startGameLobby() {
		
		GameState.setGameState(GameState.LOBBY);
		
		Bukkit.getPluginManager().registerEvents(new LobbyListener(), plugin);
		
		Countdown c = CountdownManager.createCountdown(new CountdownCallback() {
			
			@Override
			public void onCountdownFinish() {
				startGameIngame();
			}
			
			@Override
			public void onCountdownCount(CountdownCountEvent ev) {
				if (Arrays.asList(1,2,3,4,5,10,15,30,40).contains(ev.getCurrentNumber()))
					ev.setMessage(PREFIX + "Noch §e" + ev.getCurrentNumber() + " §6Sekunde" + (ev.getCurrentNumber() == 1 ? "" : "n") + ".");
			}
			
		}, 30, 0, 20L);
		
	}
	
	public void startGameIngame() {
		
		GameState.setGameState(GameState.INGAME);
		
		int i = 0;
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (teamManager.getAllTeams().get(i) == null)
				i = 0;
			teamManager.getAllTeams().get(i).addPlayer(p);
			i++;
		}
		
		for (Team team : teamManager.getAllTeams())
			team.teleport(locationManager.getLocation(team));
		
	}
	
	public void stopGame() {
		
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}
	
}
