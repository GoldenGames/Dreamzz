package me.mani.dreamzz;

import org.bukkit.plugin.java.JavaPlugin;

public class Dreamzz extends JavaPlugin {

	private GameManager gameManager;
	
	@Override
	public void onEnable() {
		gameManager = new GameManager(this);
		gameManager.startBootstrap();
	}
	
}
