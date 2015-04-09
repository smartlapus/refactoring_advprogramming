import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LingoFrame extends JFrame implements ActionListener {

	private JLabel comment;
	private JPanel panel;
	private JTextField inputTextField;
	private JButton enterButton;
	private Graphics graphics;

	int backupLine = 0;
	int currentLine = 0;
	int currentColumn = 0;

	public LingoFrame() {
		setLayout(new FlowLayout());
		setTitle("Opgave 4-6, LINGO!");
		setSize(500, 500);
		setVisible(true);

		comment = new JLabel("Welcome to Lingo!");
		comment.setVisible(true);
		add(comment);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 400));
		panel.setBackground(Color.BLUE);
		setVisible(true);
		add(panel);

		enterButton = new JButton("Enter Word");
		enterButton.addActionListener(this);
		enterButton.setVisible(true);
		add(enterButton);

		inputTextField = new JTextField(15);
		add(inputTextField);
		setVisible(true);

		try {
			Thread.sleep(500);
			drawGrid();
		} catch (InterruptedException e) {
		}
	}

	public void drawGrid() {
		int gridWidth = 2;
		int gridSize = 400;
		int gridDistance = 80;
		
		updatePanelGraphics().setColor(Color.WHITE);
		for (int x = 0; x <= gridSize; x += gridDistance) {
			graphics.fillRect(x, 0, gridWidth, gridSize);
			graphics.fillRect(0, x, gridSize, gridWidth);
		}
	}

	private Graphics updatePanelGraphics() {
		graphics = panel.getGraphics();
		return graphics;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterButton && currentLine < 5) {
			enterWord();
		}
		else {
			System.out.println("Maximum amount of guesses performed");
		}
	}

	private void enterWord() {
		int[] textline = new int[5];
		textline[0] = 60;
		textline[1] = 140;
		textline[2] = 220;
		textline[3] = 300;
		textline[4] = 380;
		
		int[] column = new int[5];
		column[0] = 20;
		column[1] = 100;
		column[2] = 180;
		column[3] = 260;
		column[4] = 340;
		
		String UserInput = inputTextField.getText();
		int length = UserInput.length();

		System.out.println("b1 pressed");
		System.out.println("UserInput is: " + UserInput);

		if (!inputTextField.getText().equals("") && length == 5) {
			Font font = new Font("Calibri", Font.BOLD, 55);
			graphics.setFont(font);
			graphics.setColor(Color.ORANGE);

			String inputText = inputTextField.getText();
			inputText = inputText.toUpperCase();

			String[] letters;
			letters = new String[length];

			String[] lingoWord;
			lingoWord = new String[5];
			lingoWord[0] = "P";
			lingoWord[1] = "L";
			lingoWord[2] = "A";
			lingoWord[3] = "N";
			lingoWord[4] = "T";
			
			System.out.println("To guess word is: " + lingoWord[0]
					+ lingoWord[1] + lingoWord[2] + lingoWord[3]
					+ lingoWord[4]);

			for (int x = 0; x < 5; x++) {
				letters[x] = inputText.substring(x, x+1);
				System.out.println("Array " + x + " has letter: "
						+ letters[x]);
			}

			if (lingoWord[0].equals(letters[0])
					&& lingoWord[1].equals(letters[1])
					&& lingoWord[2].equals(letters[2])
					&& lingoWord[3].equals(letters[3])
					&& lingoWord[4].equals(letters[4])) {
				gameWon();
			} else {

				for (int lArray = 0; lArray <= length - 1; currentColumn++, lArray++) {
					graphics.setColor(Color.ORANGE);
					graphics.drawString(letters[lArray],
							column[currentColumn], textline[currentLine]);
				}

				if (letters[0].equals(lingoWord[1])
						| letters[0].equals(lingoWord[2])
						| letters[0].equals(lingoWord[3])
						| letters[0].equals(lingoWord[4])) {
					currentColumn = 0;
					drawLetterInColorOval(textline, column, letters, Color.yellow);
					System.out.println("Array 0 is in the word");
				}
				if (letters[1].equals(lingoWord[0])
						| letters[1].equals(lingoWord[2])
						| letters[1].equals(lingoWord[3])
						| letters[1].equals(lingoWord[4])) {
					currentColumn = 1;
					drawLetterInColorOval(textline, column, letters, Color.yellow);
					System.out.println("Array 0 is in the word");
				}
				if (letters[2].equals(lingoWord[0])
						| letters[2].equals(lingoWord[1])
						| letters[2].equals(lingoWord[3])
						| letters[2].equals(lingoWord[4])) {
					currentColumn = 2;
					drawLetterInColorOval(textline, column, letters, Color.yellow);
					System.out.println("Array 0 is in the word");
				}
				if (letters[3].equals(lingoWord[0])
						| letters[3].equals(lingoWord[1])
						| letters[3].equals(lingoWord[2])
						| letters[3].equals(lingoWord[4])) {
					currentColumn = 3;
					drawLetterInColorOval(textline, column, letters, Color.yellow);
					System.out.println("Array 0 is in the word");
				}
				if (letters[4].equals(lingoWord[1])
						| letters[4].equals(lingoWord[2])
						| letters[4].equals(lingoWord[3])
						| letters[4].equals(lingoWord[0])) {
					currentColumn = 4;
					drawLetterInColorOval(textline, column, letters, Color.yellow);
					System.out.println("Array 0 is in the word");
				}

				if (lingoWord[0].equals(letters[0])) {
					for (lingoWord[0].equals(letters[0]); currentLine < 5; currentLine++) {
						currentColumn = 0;
						drawLetterInColorOval(textline, column, letters, Color.red);
					}
					currentLine = backupLine;
					System.out.println("Array 0 matches");
				}

				if (lingoWord[1].equals(letters[1])) {
					for (lingoWord[1].equals(letters[1]); currentLine < 5; currentLine++) {
						currentColumn = 1;
						drawLetterInColorOval(textline, column, letters, Color.red);
					}
					currentLine = backupLine;
					System.out.println("Array 1 matches");
				}

				if (lingoWord[2].equals(letters[2])) {
					for (lingoWord[2].equals(letters[2]); currentLine < 5; currentLine++) {
						currentColumn = 2;
						drawLetterInColorOval(textline, column, letters, Color.red);
					}
					currentLine = backupLine;
					System.out.println("Array 2 matches");
				}

				if (lingoWord[3].equals(letters[3])) {
					for (lingoWord[3].equals(letters[3]); currentLine < 5; currentLine++) {
						currentColumn = 3;
						drawLetterInColorOval(textline, column, letters, Color.red);
					}
					currentLine = backupLine;
					System.out.println("Array 3 matches");
				}

				if (lingoWord[4].equals(letters[4])) {
					for (lingoWord[4].equals(letters[4]); currentLine < 5; currentLine++) {
						currentColumn = 4;
						drawLetterInColorOval(textline, column, letters, Color.red);
					}
					currentLine = backupLine;
					System.out.println("Array 4 matches");
				}
				currentColumn = 0;
				currentLine += 1;
				backupLine = currentLine;
				System.out.println(currentLine);
			}
		} else {
			System.out.println("Word entered was " + length
					+ " characters long. Needs 5.");
		}
	}

	private void drawLetterInColorOval(int[] textline, int[] column,
			String[] letters, Color ovalColor) {
		graphics.setColor(ovalColor);
		graphics.fillOval(column[currentColumn] - 17,
				textline[currentLine] - 57, 75, 75);

		graphics.setColor(Color.ORANGE);
		graphics.drawString(letters[0],
				column[currentColumn],
				textline[currentLine]);
	}

	public void gameWon() {
		System.out.println("Winner!");
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 400, 400);
		currentLine = 3;

		Font font = new Font("Calibri", Font.BOLD, 55);
		graphics.setFont(font);
		graphics.setColor(Color.red);
		graphics.drawString("Winner", 100, 220);
	}
}