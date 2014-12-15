package me.mani.dreamzz.util;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

	public static ItemStack customItem(Material mat, int amount, short damage, String displayName) {
		ItemStack item = new ItemStack(mat, amount, damage);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack customItem(Material mat, int amount, short damage, String displayName, String... lore) {
		ItemStack item = new ItemStack(mat, amount, damage);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}
	
}
