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
	
	private void checkIfVictorious(int field) {
		int player = playerXPlaying ? 0 : 1;

		for(int winOption = 0; winOption < 8; winOption++) {
			boolean check = false;
			if(winOption == 0) {
				check = (field == 0 || field == 1 || field == 2);
			} else if(winOption == 1) {
				check = (field == 3 || field == 4 || field == 5);
			} else if(winOption == 2) {
				check = (field == 6 || field == 7 || field == 8);
			} else if(winOption == 3) {
				check = (field == 0 || field == 3 || field == 6);
			} else if(winOption == 4) {
				check = (field == 1 || field == 4 || field == 7);
			} else if(winOption == 5) {
				check = (field == 2 || field == 5 || field == 8);
			} else if(winOption == 6) {
				check = (field == 0 || field == 4 || field == 8);
			} else if(winOption == 7) {
				check = (field == 2 || field == 4 || field == 6);
			}
			if(check) {
				state[winOption][player]++;
				if(state[winOption][player] == 3) {
					win();
					return;
				}
			}
		}
		if(turns == 9) {
			draw();
		}
		playerXPlaying ^= true;
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