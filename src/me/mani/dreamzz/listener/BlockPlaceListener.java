package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameState;
import me.mani.dreamzz.manager.Blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R1.block.CraftBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener {
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent ev) {
		
//		Player p = ev.getPlayer();
		Block b = ev.getBlock();
		
		if (GameState.getGameState() == GameState.LOBBY) {
			
			ev.setCancelled(true);
			
		}
		else if (GameState.getGameState() == GameState.INGAME) {
			
			Blocks.addBlock(b);
			if (b.getType() == Material.WOOL || b.getType() == Material.STAINED_GLASS || b.getType() == Material.STAINED_CLAY) {
				byte data = (byte) (0 + (int) (Math.random() * ((15 - 0) + 1)));
				((CraftBlock) ev.getBlock()).setData(data);
			}
			
		}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {
			
			ev.setCancelled(true);
			
		}
		
	}

}
