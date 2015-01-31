package me.mani.dreamzz.manager;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

public class Players {
	
	public static final int NEEDED_TO_START = 1;
	
	private static Set<Player> players = new HashSet<>();
	
	public static void addPlayer(Player player) {
		players.add(player);
	}
	
	public static void removePlayer(Player player) {
		players.remove(player);
	}
	
	public static boolean containsPlayer(Player player) {
		return players.contains(player);
	}
	
	public static Set<Player> getPlayers() {
		return players;
	}
	
	public static int getPlayerCount() {
		return players.size();
	}

}
