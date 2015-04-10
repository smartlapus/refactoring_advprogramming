import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
	private JButton[] buttons = new JButton[9];
	private boolean playerXPlaying = true;
	private int turns;
	// State defines the way the game is won (|,-,/,\) and the player who does (0, 1)
	private int[][] state = new int[8][2];
		
	public TicTacToe() {
		initUI();
	}
	
	private void initUI() {
		setTitle("Tic Tac Toe");
		setResizable(false);
		setLayout(new FlowLayout());
		
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton("");
			buttons[i].setPreferredSize(new Dimension(80, 80));
			buttons[i].setFont(new Font("sansserif", Font.BOLD, 75));
			add(buttons[i]);
			buttons[i].addActionListener(this);
		}
				
		setSize(260, 280);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void checkIfVictorious(int i) {
		int player = playerXPlaying ? 0 : 1;

		for(int j = 0; j < 8; j++) {
			boolean check = false;
			if(j == 0) {
				check = (i == 0 || i == 1 || i == 2);
			} else if(j == 1) {
				check = (i == 3 || i == 4 || i == 5);
			} else if(j == 2) {
				check = (i == 6 || i == 7 || i == 8);
			} else if(j == 3) {
				check = (i == 0 || i == 3 || i == 6);
			} else if(j == 4) {
				check = (i == 1 || i == 4 || i == 7);
			} else if(j == 5) {
				check = (i == 2 || i == 5 || i == 8);
			} else if(j == 6) {
				check = (i == 0 || i == 4 || i == 8);
			} else if(j == 7) {
				check = (i == 2 || i == 4 || i == 6);
			}
			if(check) {
				state[j][player]++;
				if(state[j][player] == 3) {
					win();
					return;
				}
			}
		}
		if(turns == 9) {
			draw();
		}
		playerXPlaying = !playerXPlaying;
	}
	
	private void win() {
		String player = playerXPlaying ? "X" : "O";
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		JOptionPane.showMessageDialog(null, "Gefeliciteerd, Speler " + player + "!");
		resetGame();
	}
	
	private void draw() {
		JOptionPane.showMessageDialog(null, "Helaas, geen winnaar.");
		resetGame();
	}
	
	private void resetGame() {
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(true);
			buttons[i].setText("");
		}
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 2; j++) {
				state[i][j] = 0;
			}
		}
		turns = 0;
	}
	
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		turns++;
		
		String player = playerXPlaying ? "X" : "O";
		
		for(int i = 0; i < 9; i++) {
			if(source == buttons[i]) {
				buttons[i].setText(player);
				buttons[i].setEnabled(false);
				checkIfVictorious(i);
				break;
			}
		}
	}
}