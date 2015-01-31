package me.mani.dreamzz.ressource;

import me.mani.dreamzz.util.ItemUtil;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum RessourceType {
	CLAY ("Bronze", ChatColor.RED, Material.CLAY_BRICK, (short) 0, Material.HARD_CLAY),
	IRON ("Silber", ChatColor.GRAY, Material.IRON_INGOT, (short) 0, Material.IRON_BLOCK),
	GOLD ("Gold", ChatColor.YELLOW, Material.GOLD_INGOT, (short) 0, Material.GOLD_BLOCK),
	LAPIS_LAZULI ("Lapis Lazuli", ChatColor.BLUE, Material.INK_SACK, (short) 4, null);
	
	private ItemStack item;
	private String displayName;
	private ChatColor color;
	private Material itemMaterial;
	private Material blockMaterial;
	
	private RessourceType(String displayName, ChatColor color, Material itemMaterial, short dataValue, Material blockMaterial) {
		this.item = ItemUtil.customItem(itemMaterial, 1, dataValue, color + displayName);
		this.displayName = displayName;
		this.color = color;
		this.itemMaterial = itemMaterial;
		this.blockMaterial = blockMaterial;
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
	
	public Material getItemMaterial() {
		return itemMaterial;
	}
	
	public Material getBlockMaterial() {
		return blockMaterial;
	}
}
