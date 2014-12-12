package me.mani.dreamzz;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum TeamColor {
	RED (ChatColor.RED, DyeColor.RED),
	BLUE (ChatColor.BLUE, DyeColor.BLUE),
	GREEN (ChatColor.GREEN, DyeColor.LIME),
	YELLOW (ChatColor.YELLOW, DyeColor.YELLOW),
	PURPLE (ChatColor.LIGHT_PURPLE, DyeColor.PURPLE),
	ORANGE (ChatColor.GOLD, DyeColor.ORANGE),
	BLACK (ChatColor.BLACK, DyeColor.BLACK),
	WHITE (ChatColor.WHITE, DyeColor.WHITE);
	
	private ChatColor chatColor;
	private DyeColor dyeColor;
	
	private TeamColor(ChatColor chatColor, DyeColor dyeColor) {
		this.chatColor = chatColor;
		this.dyeColor = dyeColor;
	}

	public ChatColor getChatColor() {
		return chatColor;
	}

	public DyeColor getDyeColor() {
		return dyeColor;
	}
	
}
