package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameState;
import me.mani.dreamzz.manager.Blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Bed;

public class BlockBreakListener extends DreamzzListener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent ev) {
		
		Player p = ev.getPlayer();	
		Block b = ev.getBlock();
		
		if (GameState.getGameState() == GameState.LOBBY) {
			
			ev.setCancelled(true);
			
		}
		else if (GameState.getGameState() == GameState.INGAME) {
			
			if (Blocks.containsBlock(b))
				Blocks.removeBlock(b);
			else if (b.getType() == Material.BED_BLOCK) {
				Location bedLocation;
				Bed bed = (Bed) b.getState().getData();
				if (bed.isHeadOfBed())
					bedLocation = b.getLocation();
				else 
					bedLocation = b.getRelative(bed.getFacing()).getLocation();
				if (dreamzz.gameManager.onBedBreak(bedLocation, p)) {
					bedLocation.getBlock().setType(Material.AIR, false);
					bedLocation.getBlock().getRelative(bed.getFacing().getOppositeFace()).setType(Material.AIR, false);
				}
				ev.setCancelled(true);
			}
			else 
				ev.setCancelled(true);
			
		}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {
			
			ev.setCancelled(true);
			
		}
		
	}

}
