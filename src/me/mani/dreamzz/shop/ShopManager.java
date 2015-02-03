package me.mani.dreamzz.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.mani.dreamzz.GameManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftInventoryPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ShopManager {
	
	private ShopInventory shopInventory;
	private List<ShopItem> shopItems = new ArrayList<>();
	
	public ShopManager() {
		shopInventory = new ShopInventory(this);
		shopInventory.setup();
	}

	public ShopInventory getShopInventory() {
		return shopInventory;
	}
	
	public void registerShopItem(ShopItem shopItem) {
		shopItems.add(shopItem);
	}
	
	public boolean isShopItem(int slot) {
		return getShopItem(slot) != null;
	}
	
	public ShopItem getShopItem(int slot) {
		for (ShopItem shopItem : shopItems)
			if (shopItem.getSlot() == slot)
				return shopItem;
		return null;
	}
	
	public List<ShopItem> getShopItems() {
		return shopItems;
	}
	
	public boolean isShopInventory(Inventory inv) {
		return shopInventory.getInventory().equals(inv);
	}
	
	public void onItemClick(Player p, int slot) {
		if (!isShopItem(slot))
			return;
		ShopItem item = getShopItem(slot);
		PlayerInventory inv = p.getInventory();
		ItemStack readyItem = item.toItemStack();
		ItemStack cursorItem = p.getItemOnCursor();
		ItemStack ressourceNeeded = item.getRessourceType().toItemStack();
		if (!inv.containsAtLeast(ressourceNeeded, item.getRessourceCount())) {
			p.sendMessage(GameManager.PREFIX + "Du hast nicht genügend Ressourcen für den Kauf!");
			p.playSound(p.getLocation(), Sound.ANVIL_LAND, 0.1f, 1f);
			return;
		}
		else if (cursorItem != null && cursorItem.getType() != Material.AIR) {
			if (!cursorItem.getType().equals(readyItem.getType()))
				return;
			else if (cursorItem.getItemMeta().equals(readyItem.getItemMeta())) {
				if (cursorItem.getAmount() + readyItem.getAmount() < cursorItem.getMaxStackSize())
					cursorItem.setAmount(cursorItem.getAmount() + readyItem.getAmount());
				else
					return;
				p.setItemOnCursor(cursorItem);
			}
		}
		else
			p.setItemOnCursor(readyItem);
		ressourceNeeded.setAmount(item.getRessourceCount());
		inv.removeItem(ressourceNeeded);
		p.playSound(p.getLocation(), Sound.ORB_PICKUP, 0.1f, 1f);
	}		
	
	public void onItemShiftClick(Player p, int slot) {
		if (!isShopItem(slot))
			return;
		ShopItem item = getShopItem(slot);
		PlayerInventory inv = p.getInventory();	
		ItemStack readyItem = item.toItemStack();
		ItemStack ressourceNeeded = item.getRessourceType().toItemStack();
		ressourceNeeded.setAmount(item.getRessourceCount());	
		if (!inv.containsAtLeast(ressourceNeeded, item.getRessourceCount())) {
			p.sendMessage(GameManager.PREFIX + "Du hast nicht genügend Ressourcen für den Kauf!");
			p.playSound(p.getLocation(), Sound.ANVIL_LAND, 0.1f, 1f);
			return;
		}	
		Inventory clone = Bukkit.createInventory(null, 9 * 4);
		clone.setContents(inv.getContents());
		int times = 0;
		while (inv.containsAtLeast(ressourceNeeded, item.getRessourceCount())) {
			HashMap<Integer, ItemStack> remainingItems = clone.addItem(readyItem);
			if (remainingItems.isEmpty() && times < 64)
				inv.removeItem(ressourceNeeded);
			else
				break;
			times++;
		}
		for (int i = 0; i < times; i++)
			inv.addItem(readyItem);
		p.playSound(p.getLocation(), Sound.ORB_PICKUP, 0.1f, 1f);
	}

}
