package me.mani.dreamzz;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LobbyListener implements Listener {
	
	private GameManager gameManager;
	
	public LobbyListener(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		ev.setJoinMessage(GameManager.PREFIX + "§a[>>>] §e" + ev.getPlayer().getName());
		ev.getPlayer().teleport(gameManager.locationManager.getLobbySpawn());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent ev) {
		ev.setQuitMessage(GameManager.PREFIX + "§c[<<<] §e" + ev.getPlayer().getName());
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent ev) {
		ev.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent ev) {
		ev.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent ev) {
		ev.setCancelled(true);
	}

}
