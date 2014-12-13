package me.mani.dreamzz;

import me.mani.dreamzz.CountdownManager.Countdown;

public abstract class CountdownCallback {
	
	private Countdown countdown;

	public void boundToCountdown(Countdown countdown) {
		this.countdown = countdown;
	}
	
	public abstract void onCountdownFinish();
	
	public abstract void onCountdownCount(CountdownCountEvent ev);
	
	public void cancelCountdown() {
		countdown.stop();
	}

}
