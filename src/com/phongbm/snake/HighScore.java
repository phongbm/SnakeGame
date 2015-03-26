/*
 * HighScore.java
 */

package com.phongbm.snake;

public class HighScore {
	private String name;
	private int scores;

	public HighScore() {
	}

	public HighScore(String name, int scores) {
		this.name = name;
		this.scores = scores;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

}