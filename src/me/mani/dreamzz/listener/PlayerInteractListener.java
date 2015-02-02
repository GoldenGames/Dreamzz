package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameState;
import me.mani.dreamzz.SpecialItems;
import me.mani.dreamzz.SpecialItems.Type;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener extends DreamzzListener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev) {
		
		Player p = ev.getPlayer();
		Action a = ev.getAction();
		ItemStack item = ev.getItem();
		
		if (GameState.getGameState() == GameState.LOBBY) {
			
			if (a == Action.PHYSICAL)
				ev.setCancelled(true);
			
		}
		else if (GameState.getGameState() == GameState.INGAME) {
			
			if (a == Action.PHYSICAL || item == null || !Type.hasType(item.getType()))
				return;
			SpecialItems.castSpecialItem(Type.getType(item.getType()), p, a);
			ev.setCancelled(true);
			
		}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {}
		
	}

}
