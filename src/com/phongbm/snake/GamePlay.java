/*
 * GamePlay.java
 */

package com.phongbm.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class GamePlay extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ReentrantLock reentrantLock;
	private Maps maps;
	private Snake snake;
	private Food food;
	private boolean isEndGame, isPausing, isPressStopButton, addTop = false;
	private Thread thread;
	private JLabel lbLogo, lbControl, lbState, lbScore, lbScoreNumber, lbLevel,
			lbLevelNumber;
	private JButton btnPlay, btnPause, btnStop, btnBack;
	private JProgressBar pbTime;
	private int scores;
	private TopHighScore topHighScore;
	private GUI gui;
	Graphics2D g2D;
	private SoundLibrary soundLibrary;
	private int countTime, countEat, countBonusTime;
	private Image imgPauseGame = new ImageIcon(getClass().getResource(
			"/images/imgPauseGame.png")).getImage();
	private Image imgStopGame = new ImageIcon(getClass().getResource(
			"/images/imgStopGame.png")).getImage();
	private Image imgGameOver = new ImageIcon(getClass().getResource(
			"/images/imgGameOver.png")).getImage();

	public GamePlay(GUI gui) {
		this.setLayout(null);
		this.setBounds(0, 0, GUI.W_FRAME, GUI.H_FRAME);

		snake = new Snake();
		snake.setLevel(gui.getLevel());
		scores = 0;
		maps = new Maps();
		if (gui.isModernBox()) {
			snake.setModernBox(true);
			maps.setModernBox(true);
		}
		this.add(maps);
		food = new Food();
		food.initFood(snake);
		food.setBonus(false);
		reentrantLock = new ReentrantLock();
		isEndGame = true;
		isPausing = false;
		isPressStopButton = false;
		countTime = 0;
		countEat = 0;
		countBonusTime = 0;
		this.gui = gui;
		soundLibrary = new SoundLibrary();
		soundLibrary.setSound(gui.isSounding());
		initComponent();
		btnPause.setEnabled(false);
		btnStop.setEnabled(false);

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					if (snake.getDir() == 2)
						break;
					snake.setDir(1);
					break;
				case KeyEvent.VK_DOWN:
					if (snake.getDir() == 1)
						break;
					snake.setDir(2);
					break;
				case KeyEvent.VK_LEFT:
					if (snake.getDir() == 4)
						break;
					snake.setDir(3);
					break;
				case KeyEvent.VK_RIGHT:
					if (snake.getDir() == 3)
						break;
					snake.setDir(4);
					break;
				}
			}
		});
	}

	public Graphics2D getG2D() {
		return g2D;
	}

	public void setG2D(Graphics2D g2d) {
		g2D = g2d;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g2D = (Graphics2D) g;
		reentrantLock.lock();
		if (!isEndGame && !isPausing) {
			snake.move();
		}
		reentrantLock.unlock();
		for (int i = 0; i <= snake.getLength(); i++) {
			g2D.drawImage(snake.getSnakePosition()[i].getImg(),
					snake.getSnakePosition()[i].getX(),
					snake.getSnakePosition()[i].getY(), null);
		}
		eatFood();
		if (food.isBonus()) {
			eatBonusFood();
		}
		if (countTime % 2 == 0)
			drawFood(g2D);
		if (isPausing) {
			g2D.drawImage(imgPauseGame,
					20 + (Maps.W_MAPS - imgPauseGame.getWidth(null)) / 2,
					20 + (Maps.H_MAPS - imgPauseGame.getHeight(null)) / 2, null);
		}
		if (isPressStopButton) {
			isPausing = false;
			isPressStopButton = false;
			isEndGame = true;
			g2D.drawImage(imgStopGame,
					20 + (Maps.W_MAPS - imgPauseGame.getWidth(null)) / 2,
					20 + (Maps.H_MAPS - imgPauseGame.getHeight(null)) / 2, null);
			GamePlay.this.closeGame();
		}
		if (snake.isDead()) {
			g2D.drawImage(imgGameOver,
					20 + (Maps.W_MAPS - imgGameOver.getWidth(null)) / 2,
					20 + (Maps.H_MAPS - imgGameOver.getHeight(null)) / 2, null);
			isEndGame = true;
			addTop = true;
			endGame();
		}
	}

	public void drawFood(Graphics2D g2D) {
		g2D.drawImage(food.getImgFood(), food.getxFood(), food.getyFood(), null);
		if (food.isBonus()) {
			if (countBonusTime <= 100) {
				g2D.drawImage(food.getImgBonusFood(), food.getxBonusFood(),
						food.getyBonusFood(), null);
				pbTime.setValue(countBonusTime);
			}
			if (countBonusTime > 100) {
				countBonusTime = 0;
				food.setBonus(false);
				pbTime.setValue(0);
			}
		}
	}

	@Override
	public void run() {
		while (!isEndGame) {
			repaint();
			try {
				switch (snake.getLevel()) {
				case 1:
					Thread.sleep(250);
					break;
				case 2:
					Thread.sleep(200);
					break;
				case 3:
					Thread.sleep(150);
					break;
				case 4:
					Thread.sleep(100);
					break;
				case 5:
					Thread.sleep(75);
					break;
				}
				countTime = countTime + 1;
				countBonusTime += 5;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (addTop)
			addTopHighScore();
	}

	public void endGame() {
		soundLibrary.playEndGame();
		btnPlay.setEnabled(true);
		btnPause.setEnabled(false);
		btnStop.setEnabled(false);
	}

	public void addTopHighScore() {
		topHighScore = new TopHighScore();
		topHighScore.readData();
		if (!topHighScore.isFull()
				|| (topHighScore.isFull() && scores >= topHighScore
						.getTopHightScore().get(topHighScore.getLength() - 1)
						.getScores())) {
			String name = JOptionPane.showInputDialog("Name:");
			if (topHighScore.isFull()) {
			} else {
				topHighScore.getTopHightScore().add(new HighScore());
				topHighScore.setLength(topHighScore.getLength() + 1);
			}
			topHighScore.getTopHightScore().set(topHighScore.getLength() - 1,
					new HighScore(name, scores));
			topHighScore.swap();
			topHighScore.writeData();
		} else {
		}
	}

	public void eatFood() {
		if (Math.abs(snake.getSnakePosition()[0].getX() - food.getxFood()) <= food
				.getImgFood().getWidth(null) / 2
				&& Math.abs(snake.getSnakePosition()[0].getY()
						- food.getyFood()) <= food.getImgFood().getHeight(null) / 2) {
			soundLibrary.playEatFood();
			if (snake.getLength() < Snake.MAX_LENGTH - 1) {
				snake.makeBody();
			}
			switch (snake.getLevel()) {
			case 1:
				scores += 1;
				break;
			case 2:
				scores += 2;
				break;
			case 3:
				scores += 3;
				break;
			case 4:
				scores += 4;
				break;
			case 5:
				scores += 5;
				break;
			}
			countEat++;
			if (countEat % 5 == 0) {
				if (!food.isBonus()) {
					food.setBonus(true);
					food.initBonusFood(snake);
					countBonusTime = 0;
				}
			}
			lbScoreNumber.setText(String.valueOf(scores));
			food.initFood(snake);
		}
	}

	public void eatBonusFood() {
		if (Math.abs(snake.getSnakePosition()[0].getX() - food.getxBonusFood()) <= food
				.getImgBonusFood().getWidth(null) / 2
				&& Math.abs(snake.getSnakePosition()[0].getY()
						- food.getyBonusFood()) <= food.getImgBonusFood()
						.getHeight(null) / 2) {
			soundLibrary.playEatFood();
			if (snake.getLength() < Snake.MAX_LENGTH - 1) {
				snake.makeBody();
			}
			scores *= 2;
			lbScoreNumber.setText(String.valueOf(scores));
			food.setBonus(false);
			pbTime.setValue(0);
		}
	}

	public void startGame() {
		if (!isPausing) {
			snake = new Snake();
			snake.setLevel(gui.getLevel());
			if (gui.isModernBox()) {
				snake.setModernBox(true);
				maps.setModernBox(true);
			}
			isEndGame = false;
			snake.setDir(4);
			soundLibrary = new SoundLibrary();
			soundLibrary.setSound(gui.isSounding());
			thread = new Thread(this);
			thread.start();
			scores = 0;
			lbScoreNumber.setText("0");
			countEat = 0;
		} else {
			isPausing = false;
			soundLibrary.setSound(gui.isSounding());
			snake.setLevel(gui.getLevel());
			lbLevelNumber.setText(String.valueOf(snake.getLevel()));
		}
		countTime = 0;
	}

	public void closeGame() {
		isEndGame = true;
	}

	public boolean getIsPausing() {
		return isPausing;
	}

	public void setEnabledPlayButton(boolean logic) {
		btnPlay.setEnabled(logic);
	}

	public void setEnabledPauseButton(boolean logic) {
		btnPause.setEnabled(logic);
	}

	public void setEnableStopButton(boolean logic) {
		btnStop.setEnabled(logic);
	}

	public void initComponent() {
		// Logo Game
		lbLogo = new JLabel();
		lbLogo.setBounds(620, 20, 160, 110);
		lbLogo.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgLogo.png")));
		this.add(lbLogo);

		// Initialize control panel
		lbControl = new JLabel();
		lbControl.setBounds(620, 260, 160, 210);
		lbControl.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgControl.png")));
		// Play button
		btnPlay = new JButton();
		btnPlay.setToolTipText("Play");
		btnPlay.setBounds(15, 55, 65, 65);
		btnPlay.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgPlay.png")));
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnPlay.setEnabled(false);
				btnPause.setEnabled(true);
				btnStop.setEnabled(true);
				startGame();
				setFocusable(true);
				requestFocusInWindow();
			}
		});
		// Pause button
		btnPause = new JButton();
		btnPause.setToolTipText("Pause");
		btnPause.setBounds(80, 55, 65, 65);
		btnPause.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgPause.png")));
		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnPause.setEnabled(false);
				btnPlay.setEnabled(true);
				isPausing = true;
				soundLibrary.playPause();
			}
		});
		// Stop button
		btnStop = new JButton();
		btnStop.setToolTipText("Stop");
		btnStop.setBounds(15, 120, 65, 65);
		btnStop.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgStop.png")));
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStop.setEnabled(false);
				btnPause.setEnabled(false);
				btnPlay.setEnabled(true);
				isPressStopButton = true;
			}
		});
		// Back button
		btnBack = new JButton();
		btnBack.setToolTipText("Back");
		btnBack.setBounds(80, 120, 65, 65);
		btnBack.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgBack.png")));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPressStopButton) {
					gui.getMenu().setVisibleLbContinue(false);
					gui.getMenu().setVisible(true);
					GamePlay.this.setVisible(false);
				} else {
					if (isEndGame) {
						gui.getMenu().setVisibleLbContinue(false);
						gui.getMenu().setVisible(true);
						GamePlay.this.setVisible(false);
					} else {
						isPausing = true;
						gui.getMenu().setVisibleLbContinue(true);
						gui.getMenu().setVisible(true);
						GamePlay.this.setVisible(false);
					}
				}
			}
		});
		this.add(lbControl);
		lbControl.add(btnPlay);
		lbControl.add(btnPause);
		lbControl.add(btnStop);
		lbControl.add(btnBack);
		// End panel

		// Initialize state panel
		lbState = new JLabel();
		lbState.setBounds(620, 125, 160, 150);
		lbState.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgState.png")));
		// Score
		lbScore = new JLabel();
		lbScore.setBounds(20, 40, 35, 35);
		lbScore.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgScore.png")));
		lbScoreNumber = new JLabel("0");
		lbScoreNumber.setForeground(Color.BLUE);
		lbScoreNumber.setHorizontalAlignment(JLabel.CENTER);
		lbScoreNumber.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbScoreNumber.setBounds(70, 45, 70, 25);
		// Level
		lbLevel = new JLabel();
		lbLevel.setBounds(25, 70, 35, 35);
		lbLevel.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgLevel.png")));
		lbLevelNumber = new JLabel(String.valueOf(snake.getLevel()));
		lbLevelNumber.setForeground(Color.BLUE);
		lbLevelNumber.setHorizontalAlignment(JLabel.CENTER);
		lbLevelNumber.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lbLevelNumber.setBounds(70, 75, 70, 25);
		pbTime = new JProgressBar(0, 100);
		pbTime.setBounds(20, 110, 120, 20);
		pbTime.setForeground(Color.GREEN);
		pbTime.setValue(0);
		this.add(lbState);
		lbState.add(lbScore);
		lbState.add(lbScoreNumber);
		lbState.add(lbLevel);
		lbState.add(lbLevelNumber);
		lbState.add(pbTime);
		// End panel
	}

}