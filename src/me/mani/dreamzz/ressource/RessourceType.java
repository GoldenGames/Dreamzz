package me.mani.dreamzz.ressource;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum RessourceType {
	CLAY ("Bronze", ChatColor.RED, Material.CLAY_BRICK),
	IRON ("Silber", ChatColor.GRAY, Material.IRON_INGOT),
	GOLD ("Gold", ChatColor.YELLOW, Material.GOLD_INGOT);
	
	private String displayName;
	private ChatColor color;
	private Material material;
	
	private RessourceType(String displayName, ChatColor color, Material material) {
		this.displayName = displayName;
		this.color = color;
		this.material = material;
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
