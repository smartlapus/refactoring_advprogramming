import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener
{

private JLabel comment;
private JPanel panel;

private JTextField inputTextField;
private JButton enterButton;

int win = 0;

// Variables for location
int backupLine = 0;
int currentLine= 0;
int currentColumn = 0;

public MyFrame()
{
	
	// Start Layout
	setLayout(new FlowLayout());
	setTitle("Opgave 4-6, LINGO!");
	setSize(500,500); setVisible(true);
	
	comment = new JLabel("Welcome to Lingo!");
	comment.setVisible(true); add(comment);
	
	panel = new JPanel();
	panel.setPreferredSize(new Dimension(400,400));
	panel.setBackground(Color.BLUE);
	setVisible(true); add(panel);	
	
	enterButton = new JButton("Enter Word");
	enterButton.addActionListener(this);
	enterButton.setVisible(true); add(enterButton);
	
	inputTextField = new JTextField(15);
	add(inputTextField); setVisible(true);
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
		Graphics graphics = panel.getGraphics();
		graphics.setColor(Color.WHITE);
		
		for ( int y = 0, x = 0 ; x <= 400 ; x += 80 )
		{ 
			graphics.fillRect(x,y,2,400);
		}
		for ( int x = 0, y = 0 ; y <= 400 ; y += 80 )
		{ 
			graphics.fillRect(x,y,400,2);
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
		
			
		if(e.getSource() == enterButton && currentLine < 5)
		{
			String UserInput = inputTextField.getText(); // Creates User Input to a String
			int length = UserInput.length(); // Sets the amount of characters the user filled in.
		
			// System info
			System.out.println("b1 pressed");
			System.out.println("UserInput is: " + UserInput);

			// Only executes process when either field isn't empty or has at least 5 characters.
			
			if (! inputTextField.getText().equals("") && length == 5 && win == 0)
			{
			
				// Font style
				Graphics graphics = panel.getGraphics();
				Font font = new Font("Calibri", Font.BOLD, 55);
				graphics.setFont(font);				
				graphics.setColor(Color.ORANGE);
				// -- End Font Style --
				
				String inputText = inputTextField.getText(); // Gets the user input and puts it in string "UI".
				inputText = inputText.toUpperCase(); // Converts all characters to Upper Case.
				
				// Defines array for letters
				String[] letters;
		        letters = new String[length];
		        // -- End of letters array --
			       
		        // To guess word:
		        String[] lingoWord;
		        lingoWord = new String[5];
		        lingoWord[0] = "P";
		        lingoWord[1] = "L";
		        lingoWord[2] = "A";
		        lingoWord[3] = "N";
		        lingoWord[4] = "T";
		        System.out.println("To guess word is: " + lingoWord[0] + lingoWord[1] + lingoWord[2] + lingoWord[3] + lingoWord[4]);
			    
			    // Adds letter of your chosen word in individual array objects in the letters array  
				for (int x = 0, y = 1 ; x < 5 ; x++, y++)
				{
					letters[x] = inputText.substring(x,y);
					System.out.println("Array " + x + " has letter: " + letters[x]);
				}
				// -- end of array objects --
					
				// Set Win
				if(lingoWord[0].equals(letters[0]) && lingoWord[1].equals(letters[1]) && lingoWord[2].equals(letters[2]) && lingoWord[3].equals(letters[3]) && lingoWord[4].equals(letters[4]) )
				{
					win = 1;
					ifWinner();
				}
				
				if(win == 0)
				{
				
				// Draws the word on first line
				for ( int lArray = 0 ; lArray <= length-1 ; currentColumn++, lArray++) 
				{ 
					graphics.setColor(Color.ORANGE);
					graphics.drawString(letters[lArray],column[currentColumn],textline[currentLine]); // (string, from left, from top)
				}
				
				
				
				
				// -- Checks if letter is in word, but not on right position --
				if(letters[0].equals(lingoWord[1]) | letters[0].equals(lingoWord[2]) | letters[0].equals(lingoWord[3]) | letters[0].equals(lingoWord[4]))
				{
					currentColumn = 0;
					graphics.setColor(Color.YELLOW);
					graphics.fillOval(column[currentColumn]-17, textline[currentLine]-57, 75, 75);
					
					graphics.setColor(Color.ORANGE);
					graphics.drawString(letters[0],column[currentColumn],textline[currentLine]); // (string, from left, from top)
					System.out.println("Array 0 is in the word");
				}
				if(letters[1].equals(lingoWord[0])| letters[1].equals(lingoWord[2]) | letters[1].equals(lingoWord[3]) | letters[1].equals(lingoWord[4]))
				{
					currentColumn = 1;
					graphics.setColor(Color.YELLOW);
					graphics.fillOval(column[currentColumn]-17, textline[currentLine]-57, 75, 75);
					
					graphics.setColor(Color.ORANGE);
					graphics.drawString(letters[1],column[currentColumn],textline[currentLine]); // (string, from left, from top)
					System.out.println("Array 0 is in the word");
				}
				if(letters[2].equals(lingoWord[0]) | letters[2].equals(lingoWord[1]) | letters[2].equals(lingoWord[3]) | letters[2].equals(lingoWord[4]))
				{
					currentColumn = 2;
					graphics.setColor(Color.YELLOW);
					graphics.fillOval(column[currentColumn]-17, textline[currentLine]-57, 75, 75);
					
					graphics.setColor(Color.ORANGE);
					graphics.drawString(letters[2],column[currentColumn],textline[currentLine]); // (string, from left, from top)
					System.out.println("Array 0 is in the word");
				}
				if(letters[3].equals(lingoWord[0]) | letters[3].equals(lingoWord[1]) | letters[3].equals(lingoWord[2]) | letters[3].equals(lingoWord[4]))
				{
					currentColumn = 3;
					graphics.setColor(Color.YELLOW);
					graphics.fillOval(column[currentColumn]-17, textline[currentLine]-57, 75, 75);
					
					graphics.setColor(Color.ORANGE);
					graphics.drawString(letters[3],column[currentColumn],textline[currentLine]); // (string, from left, from top)
					System.out.println("Array 0 is in the word");
				}
				if(letters[4].equals(lingoWord[1]) | letters[4].equals(lingoWord[2]) | letters[4].equals(lingoWord[3]) | letters[4].equals(lingoWord[0]))
				{
					currentColumn = 4;
					graphics.setColor(Color.YELLOW);
					graphics.fillOval(column[currentColumn]-17, textline[currentLine]-57, 75, 75);
					
					graphics.setColor(Color.ORANGE);
					graphics.drawString(letters[4],column[currentColumn],textline[currentLine]); // (string, from left, from top)
					System.out.println("Array 0 is in the word");
				}
				// -- End of check if letter in word but not position --
				
				// Draws oval for correct letter on correct position
				if(lingoWord[0].equals(letters[0]))
				{
					for(lingoWord[0].equals(letters[0]); currentLine < 5 ; currentLine++)
					{
						currentColumn = 0;
						graphics.setColor(Color.RED);
						graphics.fillOval(column[currentColumn]-17, textline[currentLine]-57, 75, 75);
						
						graphics.setColor(Color.ORANGE);
						graphics.drawString(letters[0],column[currentColumn],textline[currentLine]); // (string, from left, from top)
					}
				currentLine = backupLine;
				System.out.println("Array 0 matches");
				}
				
					
				if(lingoWord[1].equals(letters[1]))
				{
					for(lingoWord[1].equals(letters[1]); currentLine < 5 ; currentLine++)
					{
						currentColumn = 1;
						graphics.setColor(Color.RED);
						graphics.fillOval(column[currentColumn]-17, textline[currentLine]-57, 75, 75);
						
						graphics.setColor(Color.ORANGE);
						graphics.drawString(letters[1],column[currentColumn],textline[currentLine]); // (string, from left, from top)
					}
				currentLine = backupLine;
				System.out.println("Array 1 matches");
				}
				
				if(lingoWord[2].equals(letters[2]))
				{
					for(lingoWord[2].equals(letters[2]); currentLine < 5 ; currentLine++)
					{
						currentColumn = 2;
						graphics.setColor(Color.RED);
						graphics.fillOval(column[currentColumn]-17, textline[currentLine]-57, 75, 75);
						
						graphics.setColor(Color.ORANGE);
						graphics.drawString(letters[2],column[currentColumn],textline[currentLine]); // (string, from left, from top)
					}
				currentLine = backupLine;
				System.out.println("Array 2 matches");
				}
				
				if(lingoWord[3].equals(letters[3]))
				{
					for(lingoWord[3].equals(letters[3]); currentLine < 5 ; currentLine++)
					{
						currentColumn = 3;
						graphics.setColor(Color.RED);
						graphics.fillOval(column[currentColumn]-17, textline[currentLine]-57, 75, 75);
						
						graphics.setColor(Color.ORANGE);
						graphics.drawString(letters[3],column[currentColumn],textline[currentLine]); // (string, from left, from top)
					}
				currentLine = backupLine;
				System.out.println("Array 3 matches");
				}
				
				if(lingoWord[4].equals(letters[4]))
				{
					for(lingoWord[4].equals(letters[4]); currentLine < 5 ; currentLine++)
					{
						currentColumn = 4;
						graphics.setColor(Color.RED);
						graphics.fillOval(column[currentColumn]-17, textline[currentLine]-57, 75, 75);
						
						graphics.setColor(Color.ORANGE);
						graphics.drawString(letters[4],column[currentColumn],textline[currentLine]); // (string, from left, from top)
					}
				currentLine = backupLine;
				System.out.println("Array 4 matches");
				}
				// -- End check for Correct letter + position --
				
				
				
				currentColumn = 0;
				currentLine += 1;	
				backupLine = currentLine;
				System.out.println(currentLine);
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
					Graphics graphics = panel.getGraphics();
					graphics.setColor(Color.WHITE);
					graphics.fillRect(0,0,400,400);
					currentLine = 3;
					
					Font font = new Font("Calibri", Font.BOLD, 55);
					graphics.setFont(font);
					graphics.setColor(Color.red);
					graphics.drawString("Winner",100,220);
	}
}
