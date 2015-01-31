package me.mani.dreamzz.listener;

import me.mani.dreamzz.GameState;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public class AsyncPlayerPreLoginListener extends DreamzzListener {
	
	@EventHandler
	public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent ev) {
		
		if (GameState.getGameState() == GameState.LOBBY) {
			
			if (ev.getLoginResult() == Result.KICK_FULL)
				ev.setKickMessage("§cDer Server ist voll!");
			
		}
		else if (GameState.getGameState() == GameState.INGAME) {
			
			ev.disallow(Result.KICK_OTHER, "§cDas Spiel läuft bereits!");
			
		}
		else if (GameState.getGameState() == GameState.SHUTDOWN) {
			
			ev.disallow(Result.KICK_OTHER, "§cDas Spiel ist schon vorbei!");
			
		}
		
	}

}
