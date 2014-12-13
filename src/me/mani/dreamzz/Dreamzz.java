package me.mani.dreamzz;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Dreamzz extends JavaPlugin {

	private static JavaPlugin dreamzzInstance; 
	
	private GameManager gameManager;
	
	@Override
	public void onEnable() {
		dreamzzInstance = this;
		
		gameManager = new GameManager(this);
		gameManager.startBootstrap();
	}
	
	public static Plugin getInstance() {
		return dreamzzInstance;
	}
	
	public static void broadcastSound(Sound sound) {
		for (Player p : Bukkit.getOnlinePlayers())
			p.playSound(p.getLocation(), sound, 1f, 1f);
	}
	
}
