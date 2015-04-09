import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener
{

private JLabel comment;
private JPanel p;

private JTextField tfInput;
private JButton b1;

int i = 0; // Default value of first array is 0
int win = 0;

// Variables for location
int backupLine = 0;
int curLine = 0;
int curColumn = 0;

public MyFrame()
{
	
	// Start Layout
	setLayout(new FlowLayout());
	setTitle("Opgave 4-6, LINGO!");
	setSize(500,500); setVisible(true);
	
	comment = new JLabel("Welcome to Lingo!");
	comment.setVisible(true); add(comment);
	
	p = new JPanel();
	p.setPreferredSize(new Dimension(400,400));
	p.setBackground(Color.BLUE);
	setVisible(true); add(p);	
	
	b1 = new JButton("Enter Word");
	b1.addActionListener(this);
	b1.setVisible(true); add(b1);
	
	tfInput = new JTextField(15);
	add(tfInput); setVisible(true);
	// -- End Layout --
	
	// Makes it so that after executing MyFrame it pauses for 500ms (0,5 second)
	try {
			Thread.sleep(500);
		} 
	catch (InterruptedException e) 
		{
		}
	
	DrawGrid();
	
}

	public void DrawGrid()
	{
		Graphics g = p.getGraphics();
		g.setColor(Color.WHITE);
		
		for ( int y = 0, x = 0 ; x <= 400 ; x += 80 )
		{ 
			g.fillRect(x,y,2,400);
		}
		for ( int x = 0, y = 0 ; y <= 400 ; y += 80 )
		{ 
			g.fillRect(x,y,400,2);
		}
	}
		
	public void actionPerformed(ActionEvent e)
	{
		
		// Line-height from top, so on which line you're drawing.
		int[] textline;
		textline = new int[5];
			textline[0] = 60;
			textline[1] = 140;
			textline[2] = 220; 
			textline[3] = 300; 
			textline[4] = 380; 
		
		// Distance from left, so which column you're drawing in.
		int[] column;
		column = new int[5];
			column[0] = 20;
			column[1] = 100;
			column[2] = 180;
			column[3] = 260;
			column[4] = 340;
		
			
		if(e.getSource() == b1 && curLine < 5)
		{
			String UserInput = tfInput.getText(); // Creates User Input to a String
			int length = UserInput.length(); // Sets the amount of characters the user filled in.
		
			// System info
			System.out.println("b1 pressed");
			System.out.println("UserInput is: " + UserInput);

			// Only executes process when either field isn't empty or has at least 5 characters.
			
			if (! tfInput.getText().equals("") && length == 5 && win == 0)
			{
			
				// Font style
				Graphics g = p.getGraphics();
				Font font = new Font("Calibri", Font.BOLD, 55);
				g.setFont(font);				
				g.setColor(Color.ORANGE);
				// -- End Font Style --
				
				String UI = tfInput.getText(); // Gets the user input and puts it in string "UI".
				UI = UI.toUpperCase(); // Converts all characters to Upper Case.
				
				// Defines array for letters
				String[] letters;
		        letters = new String[length];
		        // -- End of letters array --
			       
		        // To guess word:
		        String[] guess;
		        guess = new String[5];
		        guess[0] = "P";
		        guess[1] = "L";
		        guess[2] = "A";
		        guess[3] = "N";
		        guess[4] = "T";
		        System.out.println("To guess word is: " + guess[0] + guess[1] + guess[2] + guess[3] + guess[4]);
			    
			    // Adds letter of your chosen word in individual array objects in the letters array  
				for (int x = 0, y = 1 ; i < 5 ; i++, x++, y++)
				{
					letters[i] = UI.substring(x,y);
					System.out.println("Array " + i + " has letter: " + letters[i]);
				}
				// -- end of array objects --
					
				// Set Win
				if(guess[0].equals(letters[0]) && guess[1].equals(letters[1]) && guess[2].equals(letters[2]) && guess[3].equals(letters[3]) && guess[4].equals(letters[4]) )
				{
					win = 1;
					ifWinner();
				}
				
				if(win == 0)
				{
				
				// Draws the word on first line
				for ( int lArray = 0 ; lArray <= length-1 ; curColumn++, lArray++) 
				{ 
					g.setColor(Color.ORANGE);
					g.drawString(letters[lArray],column[curColumn],textline[curLine]); // (string, from left, from top)
				}
				
				
				
				
				// -- Checks if letter is in word, but not on right position --
				if(letters[0].equals(guess[1]) | letters[0].equals(guess[2]) | letters[0].equals(guess[3]) | letters[0].equals(guess[4]))
				{
					curColumn = 0;
					g.setColor(Color.YELLOW);
					g.fillOval(column[curColumn]-17, textline[curLine]-57, 75, 75);
					
					g.setColor(Color.ORANGE);
					g.drawString(letters[0],column[curColumn],textline[curLine]); // (string, from left, from top)
					System.out.println("Array 0 is in the word");
				}
				if(letters[1].equals(guess[0])| letters[1].equals(guess[2]) | letters[1].equals(guess[3]) | letters[1].equals(guess[4]))
				{
					curColumn = 1;
					g.setColor(Color.YELLOW);
					g.fillOval(column[curColumn]-17, textline[curLine]-57, 75, 75);
					
					g.setColor(Color.ORANGE);
					g.drawString(letters[1],column[curColumn],textline[curLine]); // (string, from left, from top)
					System.out.println("Array 0 is in the word");
				}
				if(letters[2].equals(guess[0]) | letters[2].equals(guess[1]) | letters[2].equals(guess[3]) | letters[2].equals(guess[4]))
				{
					curColumn = 2;
					g.setColor(Color.YELLOW);
					g.fillOval(column[curColumn]-17, textline[curLine]-57, 75, 75);
					
					g.setColor(Color.ORANGE);
					g.drawString(letters[2],column[curColumn],textline[curLine]); // (string, from left, from top)
					System.out.println("Array 0 is in the word");
				}
				if(letters[3].equals(guess[0]) | letters[3].equals(guess[1]) | letters[3].equals(guess[2]) | letters[3].equals(guess[4]))
				{
					curColumn = 3;
					g.setColor(Color.YELLOW);
					g.fillOval(column[curColumn]-17, textline[curLine]-57, 75, 75);
					
					g.setColor(Color.ORANGE);
					g.drawString(letters[3],column[curColumn],textline[curLine]); // (string, from left, from top)
					System.out.println("Array 0 is in the word");
				}
				if(letters[4].equals(guess[1]) | letters[4].equals(guess[2]) | letters[4].equals(guess[3]) | letters[4].equals(guess[0]))
				{
					curColumn = 4;
					g.setColor(Color.YELLOW);
					g.fillOval(column[curColumn]-17, textline[curLine]-57, 75, 75);
					
					g.setColor(Color.ORANGE);
					g.drawString(letters[4],column[curColumn],textline[curLine]); // (string, from left, from top)
					System.out.println("Array 0 is in the word");
				}
				// -- End of check if letter in word but not position --
				
				// Draws oval for correct letter on correct position
				if(guess[0].equals(letters[0]))
				{
					for(guess[0].equals(letters[0]); curLine < 5 ; curLine++)
					{
						curColumn = 0;
						g.setColor(Color.RED);
						g.fillOval(column[curColumn]-17, textline[curLine]-57, 75, 75);
						
						g.setColor(Color.ORANGE);
						g.drawString(letters[0],column[curColumn],textline[curLine]); // (string, from left, from top)
					}
				curLine = backupLine;
				System.out.println("Array 0 matches");
				}
				
					
				if(guess[1].equals(letters[1]))
				{
					for(guess[1].equals(letters[1]); curLine < 5 ; curLine++)
					{
						curColumn = 1;
						g.setColor(Color.RED);
						g.fillOval(column[curColumn]-17, textline[curLine]-57, 75, 75);
						
						g.setColor(Color.ORANGE);
						g.drawString(letters[1],column[curColumn],textline[curLine]); // (string, from left, from top)
					}
				curLine = backupLine;
				System.out.println("Array 1 matches");
				}
				
				if(guess[2].equals(letters[2]))
				{
					for(guess[2].equals(letters[2]); curLine < 5 ; curLine++)
					{
						curColumn = 2;
						g.setColor(Color.RED);
						g.fillOval(column[curColumn]-17, textline[curLine]-57, 75, 75);
						
						g.setColor(Color.ORANGE);
						g.drawString(letters[2],column[curColumn],textline[curLine]); // (string, from left, from top)
					}
				curLine = backupLine;
				System.out.println("Array 2 matches");
				}
				
				if(guess[3].equals(letters[3]))
				{
					for(guess[3].equals(letters[3]); curLine < 5 ; curLine++)
					{
						curColumn = 3;
						g.setColor(Color.RED);
						g.fillOval(column[curColumn]-17, textline[curLine]-57, 75, 75);
						
						g.setColor(Color.ORANGE);
						g.drawString(letters[3],column[curColumn],textline[curLine]); // (string, from left, from top)
					}
				curLine = backupLine;
				System.out.println("Array 3 matches");
				}
				
				if(guess[4].equals(letters[4]))
				{
					for(guess[4].equals(letters[4]); curLine < 5 ; curLine++)
					{
						curColumn = 4;
						g.setColor(Color.RED);
						g.fillOval(column[curColumn]-17, textline[curLine]-57, 75, 75);
						
						g.setColor(Color.ORANGE);
						g.drawString(letters[4],column[curColumn],textline[curLine]); // (string, from left, from top)
					}
				curLine = backupLine;
				System.out.println("Array 4 matches");
				}
				// -- End check for Correct letter + position --
				
				
				
				i = 0;
				curColumn = 0;
				curLine += 1;	
				backupLine = curLine;
				System.out.println(curLine);
				}
				
						
			} // end of: if(! tfInput.getText().equals("") && length == 5)
					
						
			
			else
			{
				System.out.println("Word entered was " + length + " characters long. Needs 5.");
			}
					
		} // end of: if(e.getSource() == b1)
		
		else
		{
			System.out.println("Maximum amount of guesses performed");
		}
		
	} // End of public void actionPerformed(ActionEvent e)
	
	public void ifWinner()
	{
	// Execute winner form
				
					System.out.println("Winner!");
					Graphics g = p.getGraphics();
					g.setColor(Color.WHITE);
					g.fillRect(0,0,400,400);
					curLine = 3;
					
					Font font = new Font("Calibri", Font.BOLD, 55);
					g.setFont(font);
					g.setColor(Color.red);
					g.drawString("Winner",100,220);
	}
}
