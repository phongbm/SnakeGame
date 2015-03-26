package com.phongbm.snake;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundLibrary {
	public static AudioClip PAUSE;
	public static AudioClip ENDGAME;
	public static AudioClip EATFOOD;

	public void setSound(boolean logic) {
		if (logic) {
			PAUSE = Applet.newAudioClip(getClass().getResource(
					"/sounds/pause.wav"));
			ENDGAME = Applet.newAudioClip(getClass().getResource(
					"/sounds/endGame.wav"));
			EATFOOD = Applet.newAudioClip(getClass().getResource(
					"/sounds/eatFood.wav"));
		} else {
			PAUSE = null;
			ENDGAME = null;
			EATFOOD = null;
		}
	}

	public void playPause() {
		if (PAUSE != null) {
			PAUSE.play();
		}
	}

	public void playEndGame() {
		if (ENDGAME != null) {
			ENDGAME.play();
		}
	}

	public void playEatFood() {
		if (EATFOOD != null) {
			EATFOOD.play();
		}
	}

}