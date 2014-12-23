package me.mani.dreamzz;

import java.util.Arrays;

import me.mani.dreamzz.manager.CountdownCallback;
import me.mani.dreamzz.manager.CountdownCountEvent;
import me.mani.dreamzz.manager.CountdownManager;
import me.mani.dreamzz.manager.LocationManager;
import me.mani.dreamzz.manager.ScoreboardManager;
import me.mani.dreamzz.manager.SetupManager;
import me.mani.dreamzz.manager.TeamManager;
import me.mani.dreamzz.manager.CountdownManager.Countdown;
import me.mani.dreamzz.ressource.RessourceManager;
import me.mani.dreamzz.shop.ShopManager;
import me.mani.dreamzz.util.ParticleEffect;
import me.mani.dreamzz.util.ParticleEffect.Offset;
import me.mani.dreamzz.util.ParticleEffect.ParticleEffectType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
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
	private Listener currentListener;
	
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
						  PREFIX + "<------------------------------>" + "\n"
						+ PREFIX + "| Map:  §e§l" + map.getDisplayName() + "\n"
						+ PREFIX + "| Von:  §e§l" + map.getBuilderName() + "\n"
						+ PREFIX + "| Type: §e§l" + map.getTeamCount() + " x " + map.getPlayerCount() + "\n"
						+ PREFIX + "<------------------------------>"
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
		
	}

	public void onBedBreak(Location loc, Player p) {
		Team team = locationManager.getTeam(loc);
		if (team == null)
			return;
		Bukkit.broadcastMessage(PREFIX + "Das Bett von Team " + team.getTeamColor().getChatColor() + team.getTeamColor().getDisplayName() + " §6wurde von §e"
				+ p.getName() + " §6zerstört.");
		Bukkit.broadcastMessage(PREFIX + "Die Spieler dieses Teams können sich nun §enicht §6mehr wiederbeleben.");
		ParticleEffect.broadcast(ParticleEffectType.EXPLODE, loc, new Offset(2f, 2f, 2f), 0, 3);
		team.setRespawn(false);
		scoreboardManager.update(teamManager);
	}
	
	public JavaPlugin getPlugin() {
		return plugin;
	}
	
}
