package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameManager;
import me.mani.dreamzz.GameState;
import me.mani.dreamzz.manager.Players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends DreamzzListener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		
		Player p = ev.getPlayer();	
		
		if (GameState.getGameState() == GameState.LOBBY) {
			
			ev.setJoinMessage(GameManager.PREFIX + "§a[>>>] §e" + p.getName());
			p.teleport(dreamzz.gameManager.locationManager.getLobbySpawn());
			
			Players.addPlayer(p);
			
			if (dreamzz.gameManager.canStart() && !dreamzz.gameManager.isStarting())
				dreamzz.gameManager.startGameCountdown();
			
		}
		else if (GameState.getGameState() == GameState.INGAME) {}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {}
		
	}
	
}
