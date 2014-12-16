package me.mani.dreamzz;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.material.Bed;

public class IngameListener implements Listener {
	
	private GameManager gameManager;
	
	private List<Location> placedBlocks = new ArrayList<>();
	
	public IngameListener(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent ev) {
		if (placedBlocks.contains(ev.getBlock().getLocation()))
			placedBlocks.remove(ev.getBlock().getLocation());
		else if (ev.getBlock().getType() == Material.BED_BLOCK) {
			Location bedLoc;
			Bed bed = (Bed) ev.getBlock().getState().getData();
			if (bed.isHeadOfBed())
				bedLoc = ev.getBlock().getLocation();
			else 
				bedLoc = ev.getBlock().getRelative(bed.getFacing()).getLocation();	
			gameManager.onBedBreak(bedLoc, ev.getPlayer());
		}
		else
			ev.setCancelled(true);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent ev) {
		placedBlocks.add(ev.getBlock().getLocation());
		if (ev.getBlock().getType() == Material.WOOL || ev.getBlock().getType() == Material.STAINED_GLASS || ev.getBlock().getType() == Material.STAINED_CLAY) {
			byte data = (byte) (0 + (int) (Math.random() * ((15 - 0) + 1)));
			ev.getBlock().setData(data);
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent ev) {
		if (!(ev.getRightClicked() instanceof Villager))
			return;
		ev.getPlayer().openInventory(gameManager.shopManager.getShopInventory().getInventory());
		ev.setCancelled(true);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent ev) {
		if (gameManager.shopManager.isShopInventory(ev.getClickedInventory()) && ev.getCurrentItem() != null) {
			ev.setCancelled(true);
			gameManager.shopManager.onItemClick((Player) ev.getWhoClicked(), ev.getSlot());		
		}
	}

}
