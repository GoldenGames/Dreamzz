package me.mani.dreamzz;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Dreamzz extends JavaPlugin {

	private static JavaPlugin dreamzzInstance; 
	
	private GameManager gameManager;
	
	@Override
	public void onEnable() {
		if (getServer().getPluginManager().isPluginEnabled("DreamzzSetup")) {
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		dreamzzInstance = this;
		
		gameManager = new GameManager(this);
		gameManager.startBootstrap();
	}
	
	public static Plugin getInstance() {
		return dreamzzInstance;
	}	
	
	public static void broadcastSound(Sound sound) {
		for (Player p : dreamzzInstance.getServer().getOnlinePlayers()) {
			p.playSound(p.getLocation(), sound, 1f, 1f);
		}
	}
}
