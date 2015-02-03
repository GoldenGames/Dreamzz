package me.mani.dreamzz.listener;

import me.mani.dreamzz.Dreamzz;

import org.bukkit.event.Listener;

public abstract class DreamzzListener implements Listener {
	
	protected Dreamzz dreamzz = (Dreamzz) Dreamzz.getInstance();
	
	public DreamzzListener() {
		dreamzz.getServer().getPluginManager().registerEvents(this, dreamzz);
	}
	
}
