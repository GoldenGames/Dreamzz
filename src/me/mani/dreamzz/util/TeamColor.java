package me.mani.dreamzz.util;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum TeamColor {
	RED (ChatColor.RED, DyeColor.RED, "Rot"),
	BLUE (ChatColor.BLUE, DyeColor.BLUE, "Blau"),
	GREEN (ChatColor.GREEN, DyeColor.LIME, "Grün"),
	YELLOW (ChatColor.YELLOW, DyeColor.YELLOW, "Gelb"),
	PURPLE (ChatColor.LIGHT_PURPLE, DyeColor.PURPLE, "Violett"),
	ORANGE (ChatColor.GOLD, DyeColor.ORANGE, "Orange"),
	BLACK (ChatColor.BLACK, DyeColor.BLACK, "Schwarz"),
	WHITE (ChatColor.WHITE, DyeColor.WHITE, "Weiß");
	
	private ChatColor chatColor;
	private DyeColor dyeColor;
	private String displayName;
	
	private TeamColor(ChatColor chatColor, DyeColor dyeColor, String displayName) {
		this.chatColor = chatColor;
		this.dyeColor = dyeColor;
		this.displayName = displayName;
	}

	public ChatColor getChatColor() {
		return chatColor;
	}

	public DyeColor getDyeColor() {
		return dyeColor;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
}
