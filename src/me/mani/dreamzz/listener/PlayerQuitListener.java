package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameManager;
import me.mani.dreamzz.GameState;
import me.mani.dreamzz.manager.Players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener extends DreamzzListener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent ev) {
		
		Player p = ev.getPlayer();	
		
		if (GameState.getGameState() == GameState.LOBBY) {
			
			ev.setQuitMessage(GameManager.PREFIX + "§c[<<<] §e" + p.getName());
			
			Players.removePlayer(p);
			
			if (!dreamzz.gameManager.canStart() && dreamzz.gameManager.isStarting())
				dreamzz.gameManager.cancelGameCountdown();
			
		}
		else if (GameState.getGameState() == GameState.INGAME) {
			
			dreamzz.getServer().getPluginManager().callEvent(new PlayerDeathEvent(p, null, 0, ""));
			ev.setQuitMessage(GameManager.PREFIX + "§e" + p.getName() + " §7hat das Spiel verlassen!");
			
		}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {}
		
	}

}
