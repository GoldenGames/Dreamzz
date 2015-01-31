package me.mani.dreamzz.manager;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.block.Block;

public class Blocks {
	
	private static Set<Block> blocks = new HashSet<>();
	
	public static void addBlock(Block block) {
		blocks.add(block);
	}
	
	public static void removeBlock(Block block) {
		blocks.remove(block);
	}
	
	public static boolean containsBlock(Block block) {
		return blocks.contains(block);
	}

}
