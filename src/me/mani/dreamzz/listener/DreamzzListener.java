package me.mani.dreamzz.listener;

import me.mani.dreamzz.Dreamzz;
import me.mani.dreamzz.GameManager;

import org.bukkit.event.Listener;

public class DreamzzListener implements Listener {
	
	protected Dreamzz dreamzz = (Dreamzz) Dreamzz.getInstance();
	
	public DreamzzListener() {
		dreamzz.getServer().getPluginManager().registerEvents(this, dreamzz);
	}
	
}
