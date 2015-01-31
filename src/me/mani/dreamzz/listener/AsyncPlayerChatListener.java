package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameState;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener extends DreamzzListener {
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent ev) {
		
		Player p = ev.getPlayer();
		String msg = ev.getMessage();	
		
		if (GameState.getGameState() == GameState.LOBBY) {
			
			ev.setFormat("§e" + p.getName() + " §8> §b" + msg);
			
		}
		else if (GameState.getGameState() == GameState.INGAME) {
			
			if (msg.startsWith("!"))
				ev.setFormat("§bMAP §8> §e" + p.getName() + " §8> §b" + msg.substring(1));
			else {
				ev.setFormat("§dTEAM §8> §e" + p.getName() + " §8> §d" + msg);
				ev.getRecipients().removeIf((Player reciever) -> dreamzz.gameManager.teamManager.getTeam(reciever) == p);
			}
			
		}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {
			
			ev.setFormat("§e" + p.getName() + " §8> §b" + msg);
			
		}
		
	}

}
