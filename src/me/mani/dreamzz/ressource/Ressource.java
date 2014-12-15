package me.mani.dreamzz.ressource;

import me.mani.dreamzz.util.ItemUtil;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class Ressource {
	
	private RessourceType type;
	private Location loc;
	private ItemStack item;
	
	public Ressource(RessourceType type, Location loc) {
		this.type = type;
		this.loc = loc;
		this.item = ItemUtil.customItem(type.getMaterial(), 1, (short) 0, type.getColor() + type.getDisplayName());
		if (loc.getBlock().getRelative(BlockFace.UP).getType() == Material.AIR)
			loc.add(0, 1, 0);
		else
			loc.subtract(0, 1, 0);
	}
	
	public void spawn() {
		Item item = loc.getWorld().dropItemNaturally(loc, this.item);
		item.setItemStack(this.item);
		item.setPickupDelay(0);
	}

	public RessourceType getType() {
		return type;
	}
	
	public Location getLocation() {
		return loc;
	}

	public ItemStack getItem() {
		return item;
	}
	
}
