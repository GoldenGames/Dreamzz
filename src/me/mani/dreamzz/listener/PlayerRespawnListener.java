package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameManager;
import me.mani.dreamzz.GameState;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener extends DreamzzListener {
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent ev) {
		
		Player p = ev.getPlayer();	
		
		if (GameState.getGameState() == GameState.LOBBY) {}
		else if (GameState.getGameState() == GameState.INGAME) {
			
			if (dreamzz.gameManager.teamManager.getTeam(p).canRespawn()) {
				ev.setRespawnLocation(dreamzz.gameManager.locationManager.getSpawnLocation(dreamzz.gameManager.teamManager.getTeam(p)));
				p.sendMessage(GameManager.PREFIX + "Du konntest respawnen, da dein Team noch sein Bett hat.");
			}
			else {
				ev.setRespawnLocation(dreamzz.gameManager.locationManager.getLobbySpawn());
				p.sendMessage(GameManager.PREFIX + "Du bist raus, da dein Team kein Bett mehr hat.");
				Bukkit.broadcastMessage(GameManager.PREFIX + p.getName() + " ist ausgeschieden!");
			}
			
		}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {}
		
	}

}
