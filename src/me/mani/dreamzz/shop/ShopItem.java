package me.mani.dreamzz.shop;

import java.util.Arrays;

import me.mani.dreamzz.ressource.RessourceType;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopItem {
	
	private String name;
	private String lvl;
	private ItemStack rawItem;
	private int inventorySlot;
	private RessourceType ressourceType;
	private int ressourceCount;	
	
	public ShopItem(String name, String lvl, ItemStack rawItem, int inventorySlot, RessourceType ressourceType, int ressourceCount) {
		this.name = name;
		this.lvl = lvl;
		this.rawItem = rawItem;
		this.inventorySlot = inventorySlot;
		this.ressourceType = ressourceType;
		this.ressourceCount = ressourceCount;
	}
	
	public ItemStack toItemStack() {
		ItemMeta meta = rawItem.getItemMeta();
		meta.setDisplayName("§7" + name + (lvl != "" ? " §3[Lv. " + lvl + "]" : ""));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ressourceCount; i++)
			sb.append("█");
		meta.setLore(Arrays.asList("§8Preis: " + ressourceType.getColor() + sb.toString() + " " + ressourceType.getDisplayName() + " §8[" + ressourceCount + "]"));
		rawItem.setItemMeta(meta);
		return rawItem;
	}
	
	public int getSlot() {
		return inventorySlot;
	}

}
