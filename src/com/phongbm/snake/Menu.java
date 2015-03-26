/*
 * Menu.java
 */

package com.phongbm.snake;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int NUMBER_LEVELS = 5;
	public static final int NUMBER_GAMETYPES = 2;

	private JLabel lbBackground, lbNewGame, lbHighScore, lbSettings,
			lbContinue, lbInstructions, lbAbout, lbExit, lbImg, lbSubSettings,
			lbSubSettingsGameType, lbGameType, lbSubSettingsLevel, lbLv,
			lbSubSettingsSound, lbSound, sttLevel, sttGameType, sttSound;
	private int level = 1;
	private JButton btnGTClassic, btnGTModernBox, btnSoundOn, btnSoundOff;
	private JButton[] btnLevels;
	private boolean isModernBox = false, isSounding = true;

	public Menu() {
		this.setLayout(new CardLayout());
		this.setBounds(0, 0, GUI.W_FRAME, GUI.H_FRAME);
		initComponent();
	}

	public void initComponent() {
		// Initialize background image
		lbBackground = new JLabel();
		lbBackground.setBounds(0, 0, GUI.W_FRAME, GUI.H_FRAME);
		lbBackground.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgBanner.png")));
		sttGameType = new JLabel("- Game type: Classic");
		sttGameType.setForeground(Color.RED);
		sttGameType.setFont(new Font("Arial", Font.BOLD, 10));
		sttGameType.setBounds(650, 430, 200, 30);
		sttLevel = new JLabel("- Level: 1");
		sttLevel.setForeground(Color.RED);
		sttLevel.setFont(new Font("Arial", Font.BOLD, 10));
		sttLevel.setBounds(600, 430, 200, 30);
		sttSound = new JLabel("STATUS--- Sound: Turn on");
		sttSound.setForeground(Color.RED);
		sttSound.setFont(new Font("Arial", Font.BOLD, 10));
		sttSound.setBounds(470, 430, 200, 30);
		lbBackground.add(sttGameType);
		lbBackground.add(sttLevel);
		lbBackground.add(sttSound);
		// End

		// Initialize menu new game
		lbNewGame = new JLabel();
		lbNewGame.setBounds(90, 200, 100, 100);
		lbNewGame.setToolTipText("New Game");
		lbNewGame.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgNewGame1.png")));
		lbNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbNewGame.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgNewGame2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbNewGame.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgNewGame1.png")));
			}
		});
		// End new game

		// Initialize menu high score
		lbHighScore = new JLabel();
		lbHighScore.setBounds(190, 200, 100, 100);
		lbHighScore.setToolTipText("High Score");
		lbHighScore.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgHighScore1.png")));
		lbHighScore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbHighScore.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgHighScore2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbHighScore.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgHighScore1.png")));
			}
		});
		// End high score

		// Initialize menu settings
		lbSettings = new JLabel();
		lbSettings.setBounds(290, 200, 100, 100);
		lbSettings.setToolTipText("Settings");
		lbSettings.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgSettings1.png")));
		// Settings: game type, level, sound
		lbSubSettings = new JLabel();
		lbSubSettings.setBounds(390, 300, 140, 140);
		lbSubSettings.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		lbSubSettings.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgSubSettings.png")));
		// game type
		lbSubSettingsGameType = new JLabel();
		lbSubSettingsGameType.setBounds(5, 10, 140, 40);
		lbSubSettingsGameType.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgGameType1.png")));
		initGameType();
		lbSubSettingsGameType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbSubSettings.setVisible(true);
				lbSubSettingsGameType.setIcon(new ImageIcon(getClass()
						.getResource("/images/imgGameType2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbSubSettingsGameType.setIcon(new ImageIcon(getClass()
						.getResource("/images/imgGameType1.png")));
			}

			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				lbGameType.setVisible(true);
				// for (int i = 0; i < NUMBER_LEVELS; i++) {
				// btnLevels[i].setBackground(Color.WHITE);
				// }
				// btnLevels[level - 1].setBackground(Color.LIGHT_GRAY);
			}
		});
		// level
		lbSubSettingsLevel = new JLabel("Level");
		lbSubSettingsLevel.setBounds(5, 50, 140, 40);
		lbSubSettingsLevel.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgLevel1.png")));
		// sub level
		initLevel();
		lbSubSettingsLevel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbSubSettings.setVisible(true);
				lbSubSettingsLevel.setIcon(new ImageIcon(getClass()
						.getResource("/images/imgLevel2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbSubSettingsLevel.setIcon(new ImageIcon(getClass()
						.getResource("/images/imgLevel1.png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				lbLv.setVisible(true);
			}
		});
		// sound
		lbSubSettingsSound = new JLabel("Sound");
		lbSubSettingsSound.setBounds(5, 90, 140, 40);
		lbSubSettingsSound.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgSound1.png")));
		initSound();
		lbSubSettingsSound.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbSubSettings.setVisible(true);
				lbSubSettingsSound.setIcon(new ImageIcon(getClass()
						.getResource("/images/imgSound2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbSubSettingsSound.setIcon(new ImageIcon(getClass()
						.getResource("/images/imgSound1.png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				lbSound.setVisible(true);
			}
		});
		lbSubSettings.add(lbSubSettingsGameType);
		lbSubSettings.add(lbSubSettingsLevel);
		lbSubSettings.add(lbSubSettingsSound);
		lbSubSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbSubSettings.setVisible(false);
			}
		});
		lbSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbSettings.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgSettings2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbSettings.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgSettings1.png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				lbSubSettings.setVisible(true);

			}
		});
		// End settings

		// Initialize menu continue
		lbContinue = new JLabel();
		lbContinue.setBounds(390, 200, 100, 100);
		lbContinue.setToolTipText("Continue");
		lbContinue.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgContinue1.png")));
		lbContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbContinue.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgContinue2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbContinue.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgContinue1.png")));
			}
		});
		// End continue

		// Initialize menu instructions
		lbInstructions = new JLabel();
		lbInstructions.setBounds(90, 300, 100, 100);
		lbInstructions.setToolTipText("Instructions");
		lbInstructions.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgInstructions1.png")));
		lbInstructions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbInstructions.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgInstructions2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbInstructions.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgInstructions1.png")));
			}
		});
		// end instructions

		// Initialize menu about
		lbAbout = new JLabel();
		lbAbout.setBounds(190, 300, 100, 100);
		lbAbout.setToolTipText("About");
		lbAbout.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgAbout1.png")));
		lbAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbAbout.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgAbout2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbAbout.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgAbout1.png")));
			}
		});
		// End about

		// Initialize menu exit
		lbExit = new JLabel();
		lbExit.setBounds(290, 300, 100, 100);
		lbExit.setToolTipText("Exit");
		lbExit.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgExit1.png")));
		lbExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbExit.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgExit2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbExit.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgExit1.png")));
			}
		});
		// End exit

		// Image title
		lbImg = new JLabel();
		lbImg.setBounds(20, 10, 750, 150);
		lbImg.setBackground(Color.BLACK);
		lbImg.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgTitle1.png")));
		lbImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbImg.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgTitle2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbImg.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgTitle1.png")));
			}
		});
		// End title

		this.add(lbBackground);
		lbBackground.add(lbNewGame);
		lbBackground.add(lbHighScore);
		lbBackground.add(lbSettings);
		lbBackground.add(lbSubSettings);
		lbBackground.add(lbContinue);
		lbBackground.add(lbInstructions);
		lbBackground.add(lbAbout);
		lbBackground.add(lbExit);
		lbBackground.add(lbImg);
		lbContinue.setVisible(false);
		lbSubSettings.setVisible(false);
	}

	public void initGameType() {
		lbGameType = new JLabel();
		lbGameType.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		lbGameType.setBounds(535, 280, 110, 64);
		lbBackground.add(lbGameType);
		lbGameType.setVisible(false);
		btnGTClassic = new JButton("Classic");
		btnGTClassic.setBorder(BorderFactory.createEmptyBorder());
		btnGTClassic.setBackground(Color.WHITE);
		btnGTClassic.setBounds(2, 2, 106, 30);
		btnGTModernBox = new JButton("Modern Box");
		btnGTModernBox.setBorder(BorderFactory.createEmptyBorder());
		btnGTModernBox.setBackground(Color.WHITE);
		btnGTModernBox.setBounds(2, 32, 106, 30);
		lbGameType.add(btnGTClassic);
		lbGameType.add(btnGTModernBox);
		btnGTClassic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbGameType.setVisible(true);
				btnGTClassic.setBackground(Color.LIGHT_GRAY);
			};

			@Override
			public void mouseExited(MouseEvent e) {
				lbGameType.setVisible(false);
				btnGTClassic.setBackground(Color.WHITE);
			};

			@Override
			public void mouseClicked(MouseEvent e) {
				isModernBox = false;
				lbContinue.setVisible(false);
				lbGameType.setVisible(false);
				lbSubSettings.setVisible(false);
				sttGameType.setText("- Game type: Classic");
			};
		});
		btnGTModernBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbGameType.setVisible(true);
				btnGTModernBox.setBackground(Color.LIGHT_GRAY);
			};

			@Override
			public void mouseExited(MouseEvent e) {
				lbGameType.setVisible(false);
				btnGTModernBox.setBackground(Color.WHITE);
			};

			@Override
			public void mouseClicked(MouseEvent e) {
				isModernBox = true;
				lbContinue.setVisible(false);
				lbGameType.setVisible(false);
				lbSubSettings.setVisible(false);
				sttGameType.setText("- Game type: Modern Box");
			};
		});
		lbGameType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbGameType.setVisible(false);
			}
		});
	}

	public void initLevel() {
		lbLv = new JLabel();
		lbLv.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		lbLv.setBounds(535, 250, 110, 154);
		lbBackground.add(lbLv);
		lbLv.setVisible(false);
		btnLevels = new JButton[NUMBER_LEVELS];
		int y = 2;
		for (int i = 0; i < NUMBER_LEVELS; i++) {
			btnLevels[i] = new JButton("Level " + (i + 1));
			btnLevels[i].setBorder(BorderFactory.createEmptyBorder());
			btnLevels[i].setBackground(Color.WHITE);
			btnLevels[i].setBounds(2, y, 106, 30);
			y += 30;
			lbLv.add(btnLevels[i]);
		}
		for (int i = 0; i < NUMBER_LEVELS; i++) {
			final int ii = i;
			btnLevels[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					super.mouseEntered(e);
					lbLv.setVisible(true);
					btnLevels[ii].setBackground(Color.LIGHT_GRAY);
				};

				@Override
				public void mouseExited(MouseEvent e) {
					lbLv.setVisible(false);
					btnLevels[ii].setBackground(Color.WHITE);
				};

				@Override
				public void mouseClicked(MouseEvent e) {
					level = ii + 1;
					lbLv.setVisible(false);
					lbSubSettings.setVisible(false);
					sttLevel.setText("- Level: " + (ii + 1));
				};
			});
		}
		lbLv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbLv.setVisible(false);
			}
		});
	}

	public void initSound() {
		lbSound = new JLabel();
		lbSound.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		lbSound.setBounds(535, 380, 110, 64);
		lbBackground.add(lbSound);
		lbSound.setVisible(false);

		btnSoundOn = new JButton("Turn On");
		btnSoundOn.setBorder(BorderFactory.createEmptyBorder());
		btnSoundOn.setBackground(Color.WHITE);
		btnSoundOn.setBounds(2, 2, 106, 30);
		btnSoundOff = new JButton("Turn Off");
		btnSoundOff.setBorder(BorderFactory.createEmptyBorder());
		btnSoundOff.setBackground(Color.WHITE);
		btnSoundOff.setBounds(2, 32, 106, 30);
		lbSound.add(btnSoundOn);
		lbSound.add(btnSoundOff);
		btnSoundOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbSound.setVisible(true);
				btnSoundOn.setBackground(Color.LIGHT_GRAY);
			};

			@Override
			public void mouseExited(MouseEvent e) {
				lbSound.setVisible(false);
				btnSoundOn.setBackground(Color.WHITE);
			};

			@Override
			public void mouseClicked(MouseEvent e) {
				isSounding = true;
				lbSound.setVisible(false);
				lbSubSettings.setVisible(false);
				sttSound.setText("STATUS--- Sound: Turn on");
			};
		});
		btnSoundOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbSound.setVisible(true);
				btnSoundOff.setBackground(Color.LIGHT_GRAY);
			};

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseEntered(e);
				lbSound.setVisible(false);
				btnSoundOff.setBackground(Color.WHITE);
			};

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseEntered(e);
				isSounding = false;
				lbSound.setVisible(false);
				lbSubSettings.setVisible(false);
				sttSound.setText("STATUS--- Sound: Turn off");
			};
		});

		lbSound.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbSound.setVisible(false);
			}
		});
	}

	public void setVisibleLbContinue(boolean logic) {
		lbContinue.setVisible(logic);
	}

	public boolean isSounding() {
		return isSounding;
	}

	public boolean isModernBox() {
		return isModernBox;
	}

	public int getLevel() {
		return level;
	}

	public JLabel getLbNewGame() {
		return lbNewGame;
	}

	public JLabel getLbHigtScore() {
		return lbHighScore;
	}

	public JLabel getLbSettings() {
		return lbSettings;
	}

	public JLabel getLbContinue() {
		return lbContinue;
	}

	public JLabel getLbInstructions() {
		return lbInstructions;
	}

	public JLabel getLbAbout() {
		return lbAbout;
	}

	public JLabel getExit() {
		return lbExit;
	}

}