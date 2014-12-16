package me.mani.dreamzz.ressource;

import me.mani.dreamzz.util.ItemUtil;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum RessourceType {
	CLAY ("Bronze", ChatColor.RED, Material.CLAY_BRICK),
	IRON ("Silber", ChatColor.GRAY, Material.IRON_INGOT),
	GOLD ("Gold", ChatColor.YELLOW, Material.GOLD_INGOT);
	
	private ItemStack item;
	private String displayName;
	private ChatColor color;
	private Material material;
	
	private RessourceType(String displayName, ChatColor color, Material material) {
		this.item = ItemUtil.customItem(material, 1, (short) 0, color + displayName);
		this.displayName = displayName;
		this.color = color;
		this.material = material;
	}
	
	public ItemStack toItemStack() {
		return item;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public Material getMaterial() {
		return material;
	}

}
