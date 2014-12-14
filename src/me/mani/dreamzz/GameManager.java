package me.mani.dreamzz;

import java.util.Arrays;

import me.mani.dreamzz.CountdownManager.Countdown;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class GameManager {

	public static final String PREFIX = "§7[§aDreamzz§7] §6";
	
	private JavaPlugin plugin;
	private TeamManager teamManager;
	private LocationManager locationManager;
	private Map map;
	private Listener currentListener;
	
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
		
		Bukkit.getPluginManager().registerEvents(new GlobalListener(this), plugin);
		
		startGameLobby();
	}
	
	public void stopBootstrap() {
		plugin.getServer().shutdown();
	}
	
	public void startGameLobby() {
		
		GameState.setGameState(GameState.LOBBY);
		
		currentListener = new LobbyListener(this);
		Bukkit.getPluginManager().registerEvents(currentListener, plugin);
		
		Countdown c = CountdownManager.createCountdown(new CountdownCallback() {
			
			@Override
			public void onCountdownFinish() {
				startGameIngame();
			}
			
			@Override
			public void onCountdownCount(CountdownCountEvent ev) {
				if (Arrays.asList(1,2,3,4,5,10,15,30,40).contains(ev.getCurrentNumber()))
					ev.setMessage(PREFIX + "Noch §e" + ev.getCurrentNumber() + " §6Sekunde" + (ev.getCurrentNumber() == 1 ? "" : "n") + ".");
				if (ev.getCurrentNumber() == 5) {
					Bukkit.broadcastMessage(
								PREFIX + "Map: §e" + map.getDisplayName() + "\n"
							  + PREFIX + "Von: §e" + map.getBuilderName() + "\n"
							  + PREFIX + "Type: §e" + map.getTeamCount() + " x " + map.getPlayerCount()
					);
				}
			}
			
		}, 30, 0, 20L);
		
	}
	
	public void startGameIngame() {
		
		GameState.setGameState(GameState.INGAME);
		
		HandlerList.unregisterAll(currentListener);
		currentListener = new IngameListener(this);
		Bukkit.getPluginManager().registerEvents(currentListener, plugin);
		
		int i = 0;
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (teamManager.getAllTeams().get(i) == null)
				i = 0;
			teamManager.getAllTeams().get(i).addPlayer(p);
			i++;
		}
		
		for (Team team : teamManager.getAllTeams())
			team.teleport(locationManager.getSpawnLocation(team));
		
	}
	
	public void stopGame() {
		
	}

	public void onBedBreak(Location loc, Player p) {
		Team team = locationManager.getTeam(loc);
		if (team == null)
			return;
		Bukkit.broadcastMessage(PREFIX + "Das Bett von Team " + team.getTeamColor().getChatColor() + team.getTeamColor().getDisplayName() + " §6wurde von §e"
				+ p.getName() + " §6zerstört.");
		Bukkit.broadcastMessage(PREFIX + "Die Spieler dieses Teams können sich nun §enicht §6mehr wiederbeleben.");
		team.setRespawn(false);
	}
	
	public JavaPlugin getPlugin() {
		return plugin;
	}
	
}
