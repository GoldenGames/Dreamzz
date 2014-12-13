package me.mani.dreamzz;

public enum GameState {
	SETUP, LOBBY, INGAME, SHUTDOWN;
	
	private static GameState gameState;

	/**
	 * @return the gameState
	 */
	public static GameState getGameState() {
		return gameState;
	}

	/**
	 * @param gameState the gameState to set
	 */
	public static void setGameState(GameState gameState) {
		GameState.gameState = gameState;
	}

}
