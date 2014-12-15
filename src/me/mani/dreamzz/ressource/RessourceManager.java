package me.mani.dreamzz.ressource;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RessourceManager {
	
	private JavaPlugin plugin;
	
	private List<Ressource> ressources = new ArrayList<>();
	
	public RessourceManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void registerRessource(Ressource ressource) {
		ressources.add(ressource);
	}
	
	public void startSpawning() {
		new RessourceSpawnThread().runTaskTimer(plugin, 20L, 20L);
	}
	
	private void spawnRessource(RessourceType type) {
		for (Ressource ressource : ressources)
			if (ressource.getType() == type)
				ressource.spawn();
	}
	
	private class RessourceSpawnThread extends BukkitRunnable {

		int iron = 0;
		int gold = 0;
		
		@Override
		public void run() {
			if (gold == 30) {
				spawnRessource(RessourceType.GOLD);
				gold = 0;
			}
			else
				gold++;
			if (iron == 10) {
				spawnRessource(RessourceType.IRON);
				iron = 0;
			}
			else
				iron++;			
			spawnRessource(RessourceType.CLAY);
		}
		
	}

}
