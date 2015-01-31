package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameState;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener extends DreamzzListener {
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent ev) {
		
//		Entity e = ev.getEntity();	
		
		if (GameState.getGameState() == GameState.LOBBY) {
			
			ev.setCancelled(true);
			
		}
		else if (GameState.getGameState() == GameState.INGAME) {}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {
			
			ev.setCancelled(true);
			
		}
		
	}

}
