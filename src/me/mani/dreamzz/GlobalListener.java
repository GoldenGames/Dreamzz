package me.mani.dreamzz;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class GlobalListener implements Listener {
	
	private GameManager gameManager;
	
	public GlobalListener(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev) {
		Player p = ev.getPlayer();
		if (p.getItemInHand() == null && ev.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		ItemStack item = p.getItemInHand();
		if (item.getType() != null && item.getType() == Material.BONE)
			p.sendMessage(ev.getClickedBlock().getX() + ";" + ev.getClickedBlock().getY() + ";" + ev.getClickedBlock().getZ() + ";0;0"); 
	}

}
