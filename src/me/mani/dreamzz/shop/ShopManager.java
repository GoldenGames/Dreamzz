package me.mani.dreamzz.shop;

import java.util.ArrayList;
import java.util.List;

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

}
