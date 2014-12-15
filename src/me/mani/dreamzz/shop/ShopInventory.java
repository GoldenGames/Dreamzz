package me.mani.dreamzz.shop;

import me.mani.dreamzz.ressource.RessourceType;
import me.mani.dreamzz.util.ItemUtil;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopInventory {
	
	private ShopManager shopManager;
	
	private Inventory inv;
	
	public ShopInventory(ShopManager shopManager) {
		this.shopManager = shopManager;
	}
	
	public void setup() {
		
		inv = Bukkit.createInventory(null, 6*9, "§6Dreamzz §7- §eDer Shop");
		
		// Spacer (Total: 12)
		
		inv.setItem(0, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7Waffen >"));
		inv.setItem(8, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7< Waffen"));
		inv.setItem(9, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7Nahrung >"));
		inv.setItem(17, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7< Nahrung"));
		inv.setItem(18, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7Blöcke >"));
		inv.setItem(26, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7< Blöcke"));
		inv.setItem(27, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7Werkzeuge >"));
		inv.setItem(35, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7< Werkzeuge"));
		inv.setItem(36, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7Rüstung >"));
		inv.setItem(44, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7< Rüstung"));
		inv.setItem(45, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7Specials >"));
		inv.setItem(53, ItemUtil.customItem(Material.STAINED_GLASS_PANE, 1, (short) 5, "§7< Specials"));
		
		// Waffen (Total: 7)
		
		shopManager.registerShopItem(new ShopItem("Schwert", "I", new ItemStack(Material.GOLD_SWORD), 1, RessourceType.IRON, 1));
		shopManager.registerShopItem(new ShopItem("Schwert", "II", new ItemStack(Material.GOLD_SWORD), 2, RessourceType.IRON, 3));
		shopManager.registerShopItem(new ShopItem("Schwert", "III", new ItemStack(Material.IRON_SWORD), 3, RessourceType.GOLD, 5));
		
		for (ShopItem shopItem : shopManager.getShopItems())
			inv.setItem(shopItem.getSlot(), shopItem.toItemStack());
	}

	public Inventory getInventory() {
		return inv;
	}

}
