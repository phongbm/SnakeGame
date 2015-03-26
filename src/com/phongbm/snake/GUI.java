/*
 * GUI.java
 */

package com.phongbm.snake;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int W_SCREEN = Toolkit.getDefaultToolkit()
			.getScreenSize().width;
	public static final int H_SCREEN = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public static final int W_FRAME = 800;
	public static final int H_FRAME = 490;
	private boolean lbInstructionsClicked = false, lbAboutClicked = false,
			lbHighScoreClicked = false;
	private TopHighScore topHighScore;
	private Menu menu;
	private GamePlay gamePlay;

	public GUI() {
		super("Snake Game Version 1.0.2015");
		initFrame();
	}

	private void initFrame() {
		this.setBounds((W_SCREEN - W_FRAME) / 2, (H_SCREEN - H_FRAME) / 2,
				W_FRAME, H_FRAME);
		this.setLayout(new CardLayout());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menu = new Menu();
		this.add(menu);

		// Menu new game
		menu.getLbNewGame().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				gamePlay = new GamePlay(GUI.this);
				GUI.this.add(gamePlay);
				gamePlay.setVisible(true);
				gamePlay.setFocusable(true);
				gamePlay.requestFocusInWindow();
				menu.setVisible(false);
			}
		});
		// End

		// Menu high score
		menu.getLbHigtScore().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				lbHighScoreClicked = true;
				lbAboutClicked = false;
				lbInstructionsClicked = false;
				Info info = new Info(GUI.this);
				info.setVisible(true);
				GUI.this.setEnabled(false);
			}
		});
		// End

		// Menu continue
		menu.getLbContinue().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				gamePlay.setEnabledPlayButton(true);
				gamePlay.setEnabledPauseButton(false);
				gamePlay.setEnableStopButton(false);
				gamePlay.setVisible(true);
				menu.setVisible(false);
			}
		});
		// End

		// Menu instructions
		menu.getLbInstructions().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				lbInstructionsClicked = true;
				lbAboutClicked = false;
				lbHighScoreClicked = false;
				Info info = new Info(GUI.this);
				info.setVisible(true);
				GUI.this.setEnabled(false);
			}
		});
		// End

		// Menu about
		menu.getLbAbout().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				lbAboutClicked = true;
				lbInstructionsClicked = false;
				lbHighScoreClicked = false;
				Info info = new Info(GUI.this);
				info.setVisible(true);
				GUI.this.setEnabled(false);
			}
		});
		// End

		// Menu exit
		menu.getExit().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				super.mouseClicked(arg0);
				System.exit(0);
			}
		});
		// End
	}

	public Menu getMenu() {
		return menu;
	}

	public boolean isSounding() {
		return menu.isSounding();
	}

	public boolean isModernBox() {
		return menu.isModernBox();
	}

	public int getLevel() {
		return menu.getLevel();
	}

	/*
	 * Info.java support for GUI.java. Display content of menu high score, menu
	 * instruction, menu about
	 */

	public class Info extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public static final int W_INFO = 500;
		public static final int H_INFO = 300;
		private JLabel lbInfo;

		public Info(final GUI gui) {
			this.setBounds((GUI.W_SCREEN - W_INFO) / 2,
					(GUI.H_SCREEN - H_INFO) / 2, W_INFO, H_INFO);
			this.setLayout(new CardLayout());
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setUndecorated(true);
			this.setOpacity(0.75f);

			lbInfo = new JLabel();
			lbInfo.setBounds(0, 0, 500, 300);
			lbInfo.setBackground(Color.WHITE);
			lbInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			// Display content of menu about
			if (lbAboutClicked) {
				lbInfo.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgAbout.png")));
			} else {
				// Display content of menu instruction
				if (lbInstructionsClicked) {
					lbInfo.setIcon(new ImageIcon(getClass().getResource(
							"/images/imgInstructions.png")));
				} else {
					// Display content of menu high score
					if (lbHighScoreClicked) {
						// Initialize component
						topHighScore = new TopHighScore();
						topHighScore.readData();
						JLabel lbTitle = new JLabel("Top High Score");
						lbTitle.setBounds(50, 20, 450, 50);
						lbTitle.setForeground(Color.RED);
						lbTitle.setFont(new Font("Arial", Font.BOLD, 30));
						JLabel[] lbsName = new JLabel[TopHighScore.MAX_HIGHSCORE];
						JLabel[] lbsScore = new JLabel[TopHighScore.MAX_HIGHSCORE];
						int yLbs = 80;
						for (int i = 0; i < TopHighScore.MAX_HIGHSCORE; i++) {
							lbsName[i] = new JLabel();
							lbsName[i].setForeground(Color.RED);
							lbsName[i]
									.setFont(new Font("Arial", Font.BOLD, 25));
							lbsName[i].setBounds(50, yLbs, 300, 40);
							lbsScore[i] = new JLabel();
							lbsScore[i].setForeground(Color.BLUE);
							lbsScore[i]
									.setFont(new Font("Arial", Font.BOLD, 25));
							lbsScore[i].setBounds(350, yLbs, 150, 40);
							lbInfo.add(lbTitle);
							lbInfo.add(lbsName[i]);
							lbInfo.add(lbsScore[i]);
							yLbs += 40;
						}
						// Initialize content
						for (int i = 0; i < topHighScore.getLength(); i++) {
							lbsName[i].setText(topHighScore.getTopHightScore()
									.get(i).getName());
							lbsScore[i].setText(String.valueOf(topHighScore
									.getTopHightScore().get(i).getScores()
									+ " points"));
						}
					}
				}
			}
			this.add(lbInfo);
			// Clicked
			lbInfo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					Info.this.setVisible(false);
					gui.setEnabled(true);
				}
			});
			// End
		}
	}

}