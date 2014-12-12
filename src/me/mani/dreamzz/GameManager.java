package me.mani.dreamzz;

import org.bukkit.plugin.java.JavaPlugin;

public class GameManager {

	private JavaPlugin plugin;
	
	public GameManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void startBootstrap() {
		
		// TODO: Add Map choosing system. For now only cargo works
		
		SetupManager setupManager = new SetupManager(this, "cargo");
	}
	
	public void stopBootstrap() {
		
	}
	
	public void startGame() {
		
	}
	
	public void stopGame() {
		
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}
	
}
