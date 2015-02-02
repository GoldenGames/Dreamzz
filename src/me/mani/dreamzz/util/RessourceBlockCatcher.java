package me.mani.dreamzz.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import me.mani.dreamzz.Dreamzz;
import me.mani.dreamzz.ressource.RessourceType;

import org.bukkit.Bukkit;
import org.bukkit.ChunkSnapshot;

public class RessourceBlockCatcher implements Runnable {
	
	private RessourceBlockCatcher instance;
	private ExecutorService executer;

	private ChunkSnapshot chunk;
	private Set<WorldLocation> ressourceLocations = new HashSet<>();

	public RessourceBlockCatcher(ChunkSnapshot chunk) {
		this.instance = this;
		this.executer = Executors.newCachedThreadPool();
		this.chunk = chunk;
	}

	public void getRessourceLocations(Consumer<Set<WorldLocation>> consumer) {
		executer.execute(() -> {
			new Thread(instance).start();
			Bukkit.getScheduler().runTask(Dreamzz.getInstance(), () -> consumer.accept(ressourceLocations));
		});
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < chunk.getHighestBlockYAt(x, z); y++) {
					int blockType = chunk.getBlockTypeId(x, y, z);
					if (blockType == RessourceType.CLAY.getBlockMaterial().getId()
							|| blockType == RessourceType.IRON.getBlockMaterial().getId()
							|| blockType == RessourceType.GOLD.getBlockMaterial().getId())
						ressourceLocations.add(new WorldLocation(chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z));
				}
			}
		}
	}
}
