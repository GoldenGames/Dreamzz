package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameState;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener extends DreamzzListener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent ev) {
		
		Player p = (Player) ev.getWhoClicked();
		Inventory inv = ev.getInventory();
		Inventory clickedInv = ev.getClickedInventory();
		ItemStack item = ev.getCurrentItem();
		
		if (GameState.getGameState() == GameState.LOBBY) {}
		else if (GameState.getGameState() == GameState.INGAME) {
			
			if (dreamzz.gameManager.shopManager.isShopInventory(inv) && dreamzz.gameManager.shopManager.isShopInventory(clickedInv) && item != null) {
				if ((ev.isRightClick() || ev.isLeftClick()) && !ev.isShiftClick())
					dreamzz.gameManager.shopManager.onItemClick(p, ev.getSlot());		
				else if ((ev.isRightClick() || ev.isLeftClick()) && ev.isShiftClick())
					dreamzz.gameManager.shopManager.onItemShiftClick((Player) ev.getWhoClicked(), ev.getSlot());
				ev.setCancelled(true);
			}
			
		}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {}
		
	}

}
