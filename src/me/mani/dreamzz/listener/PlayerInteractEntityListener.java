package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameState;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityListener extends DreamzzListener {

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent ev) {
		
		Player p = ev.getPlayer();
		Entity e = ev.getRightClicked();
		
		if (GameState.getGameState() == GameState.LOBBY) {
			
			ev.setCancelled(true);
			
		}
		else if (GameState.getGameState() == GameState.INGAME) {
						
			if (!(e instanceof Villager))
				return;
			p.openInventory(dreamzz.gameManager.shopManager.getShopInventory().getInventory());			
			ev.setCancelled(true);
			
		}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {
			
			ev.setCancelled(true);
			
		}
		
	}
	
}
