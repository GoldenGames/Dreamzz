package me.mani.dreamzz.shop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
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
			// XXX: Player hasn't enough ressources!
			System.out.println("Player hasn't enough ressources!");
			return;
		}
		else if (cursorItem != null && cursorItem.getType() != Material.AIR) {
			if (!cursorItem.getType().equals(readyItem.getType())) {
				// XXX: Player already have another item on cursor
				System.out.println("Player already have another item on cursor");
				return;
			}
			else if (cursorItem.getItemMeta().equals(readyItem.getItemMeta())) {
				if (cursorItem.getAmount() + readyItem.getAmount() < cursorItem.getMaxStackSize())
				cursorItem.setAmount(cursorItem.getAmount() + readyItem.getAmount());
				else {
					// XXX: Player has already a full stack on the cursor
					System.out.println("Player has already a full stack on the cursor");
					return;
				}
				p.setItemOnCursor(cursorItem);
				System.out.println("Complete - Amount added");
				return;
			}
		}
		ressourceNeeded.setAmount(item.getRessourceCount());
		inv.removeItem(ressourceNeeded);
		p.setItemOnCursor(readyItem);
		System.out.println("Complete - New Item");
		
		// TODO: FIXME: When the player has an item on the cursor the buttom code doesn't get executed!
	}		

}
