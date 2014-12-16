package me.mani.dreamzz.ressource;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;

public class Ressource {
	
	private RessourceType type;
	private Location loc;
	
	public Ressource(RessourceType type, Location loc) {
		this.type = type;
		this.loc = loc;
		if (loc.getBlock().getRelative(BlockFace.UP).getType() == Material.AIR)
			loc.add(0, 1, 0);
		else
			loc.subtract(0, 1, 0);
	}
	
	public void spawn() {
		Item item = loc.getWorld().dropItemNaturally(loc, type.toItemStack());
		item.setItemStack(type.toItemStack());
		item.setPickupDelay(0);
	}
	
	public RessourceType getType() {
		return type;
	}
	
	public Location getLocation() {
		return loc;
	}
	
}
