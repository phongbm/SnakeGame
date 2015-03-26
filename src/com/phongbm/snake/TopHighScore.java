/*
 * TopHighScore.java
 */

package com.phongbm.snake;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class TopHighScore {
	public static final int MAX_HIGHSCORE = 5;

	private List<HighScore> topHightScore;
	private File file;
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	private boolean isEmpty, isFull;
	private int length;

	public TopHighScore() {
	}

	public List<HighScore> getTopHightScore() {
		return topHightScore;
	}

	public void setTopHightScore(List<HighScore> topHightScore) {
		this.topHightScore = topHightScore;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void readData() {
		try {
			length = 0;
			String filePath = new String(getClass().getResource(
					"/files/TopHighScore.txt").getPath());
			file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			topHightScore = new ArrayList<HighScore>(MAX_HIGHSCORE);
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			String name = new String();
			int scores = 0;
			while ((name = bufferedReader.readLine()) != null) {
				scores = Integer.parseInt(bufferedReader.readLine());
				topHightScore.add(new HighScore(name, scores));
				length++;
			}
			if (length == 0) {
				isEmpty = true;
				isFull = false;
			} else {
				isEmpty = false;
				if (length == MAX_HIGHSCORE) {
					isFull = true;
				} else {
					isFull = false;
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeData() {
		try {
			String filePath = new String(getClass().getResource(
					"/files/TopHighScore.txt").getPath());
			file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));
			for (int i = 0; i < length - 1; i++) {
				bufferedWriter.write(topHightScore.get(i).getName() + "\n"
						+ topHightScore.get(i).getScores() + "\n");
			}
			bufferedWriter.write(topHightScore.get(length - 1).getName() + "\n"
					+ topHightScore.get(length - 1).getScores());
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void swap() {
		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				if (topHightScore.get(j).getScores() > topHightScore.get(i)
						.getScores()) {
					HighScore temp = topHightScore.get(i);
					topHightScore.set(i, topHightScore.get(j));
					topHightScore.set(j, temp);
				}
			}
		}
	}

}