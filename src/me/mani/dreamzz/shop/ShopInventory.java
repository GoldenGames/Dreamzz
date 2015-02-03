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
		
		shopManager.registerShopItem(new ShopItem(1, "Schwert", "I", new ItemStack(Material.GOLD_SWORD), RessourceType.IRON, 1));
		shopManager.registerShopItem(new ShopItem(2, "Schwert", "II", new ItemStack(Material.GOLD_SWORD), RessourceType.IRON, 3));
		shopManager.registerShopItem(new ShopItem(3, "Schwert", "III", new ItemStack(Material.IRON_SWORD), RessourceType.GOLD, 5));
		shopManager.registerShopItem(new ShopItem(4, "Bogen", "I", new ItemStack(Material.BOW), RessourceType.GOLD, 4));
		shopManager.registerShopItem(new ShopItem(5, "Bogen", "II", new ItemStack(Material.BOW), RessourceType.GOLD, 8));
		shopManager.registerShopItem(new ShopItem(6, "Bogen", "III", new ItemStack(Material.BOW), RessourceType.GOLD, 16));
		shopManager.registerShopItem(new ShopItem(7, "Pfeil", "", new ItemStack(Material.ARROW), RessourceType.GOLD, 1));
		
		// Essen (Total: 4)
		
		shopManager.registerShopItem(new ShopItem(10, "Essen", "I", new ItemStack(Material.POTATO_ITEM), RessourceType.CLAY, 1));
		shopManager.registerShopItem(new ShopItem(12, "Essen", "II", new ItemStack(Material.BAKED_POTATO), RessourceType.CLAY, 3));
		shopManager.registerShopItem(new ShopItem(14, "Essen", "III", new ItemStack(Material.GOLDEN_APPLE), RessourceType.GOLD, 2));
		shopManager.registerShopItem(new ShopItem(16, "Essen", "IV", new ItemStack(Material.CAKE), RessourceType.IRON, 1));
		
		// Blöcke (Total: 7)
		
		shopManager.registerShopItem(new ShopItem(19, "Block", "I", new ItemStack(Material.SANDSTONE, 2, (short) 2), RessourceType.CLAY, 1));
		shopManager.registerShopItem(new ShopItem(20, "Block", "II", new ItemStack(Material.ENDER_STONE), RessourceType.CLAY, 8));
		shopManager.registerShopItem(new ShopItem(21, "Block", "III", new ItemStack(Material.IRON_BLOCK), RessourceType.IRON, 1));
		shopManager.registerShopItem(new ShopItem(22, "Kiste", "", new ItemStack(Material.CHEST), RessourceType.IRON, 1));
		shopManager.registerShopItem(new ShopItem(23, "Glas", "", new ItemStack(Material.GLASS), RessourceType.CLAY, 2));
		shopManager.registerShopItem(new ShopItem(24, "Seelensand", "", new ItemStack(Material.SOUL_SAND), RessourceType.IRON, 1));
		shopManager.registerShopItem(new ShopItem(25, "Schleimblock", "", new ItemStack(Material.SLIME_BLOCK), RessourceType.GOLD, 1));
		
		
		// Werkzeuge (Total: 3)
		
		shopManager.registerShopItem(new ShopItem(28, "Spitzhacke", "I", new ItemStack(Material.WOOD_PICKAXE), RessourceType.CLAY, 4));
		shopManager.registerShopItem(new ShopItem(31, "Spitzhacke", "II", new ItemStack(Material.GOLD_PICKAXE), RessourceType.IRON, 2));
		shopManager.registerShopItem(new ShopItem(34, "Spitzhacke", "III", new ItemStack(Material.IRON_PICKAXE), RessourceType.GOLD, 2));
		
		// Rüstung (Total: 6)
		
		shopManager.registerShopItem(new ShopItem(37, "Basishelm", "", new ItemStack(Material.LEATHER_HELMET), RessourceType.CLAY, 2));
		shopManager.registerShopItem(new ShopItem(38, "Basishose", "", new ItemStack(Material.LEATHER_LEGGINGS), RessourceType.CLAY, 2));
		shopManager.registerShopItem(new ShopItem(39, "Basisschuhe", "", new ItemStack(Material.LEATHER_BOOTS), RessourceType.CLAY, 2));
		shopManager.registerShopItem(new ShopItem(41, "Rüstung", "I", new ItemStack(Material.CHAINMAIL_CHESTPLATE), RessourceType.IRON, 2));
		shopManager.registerShopItem(new ShopItem(42, "Rüstung", "II", new ItemStack(Material.CHAINMAIL_CHESTPLATE), RessourceType.IRON, 5));
		shopManager.registerShopItem(new ShopItem(43, "Rüstung", "III", new ItemStack(Material.CHAINMAIL_CHESTPLATE), RessourceType.GOLD, 3));
		
		for (ShopItem shopItem : shopManager.getShopItems())
			inv.setItem(shopItem.getSlot(), shopItem.toItemStack());
	}

	public Inventory getInventory() {
		return inv;
	}

}
