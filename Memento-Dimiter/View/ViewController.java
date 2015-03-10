package nl.hu.PaF.Memento.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import nl.hu.PaF.Memento.Domain.Originator;

public class ViewController implements ActionListener {
	
    JButton undoButton = new JButton("Undo");
    JButton redoButton = new JButton("Redo");
    JTextField stateField = new JTextField("Edit this text");
    JLabel stateLabel = new JLabel("Edit this text");
    JButton saveButton = new JButton("Save");
    JButton editButton = new JButton("Edit");
    JTextArea textArea = new JTextArea();
    
    Originator originator;
    List<Originator.Memento> savedStates;
    
    int state = 1;
	
	public void createGui(Originator originator,  List<Originator.Memento> savedStates) {
		
		this.originator = originator;
		this.savedStates = savedStates;
		
		originator.set(stateField.getText());
	    savedStates.add(originator.saveToMemento());
	   
		
		// Panel with Undo and Redo buttons
	    undoButton.setVerticalTextPosition(AbstractButton.CENTER);
	    undoButton.setHorizontalTextPosition(AbstractButton.LEADING);
	    undoButton.setActionCommand("undo");
	    undoButton.addActionListener( this );
	    undoButton.setEnabled(false);
    	
	    redoButton.setVerticalTextPosition(AbstractButton.CENTER);
	    redoButton.setHorizontalTextPosition(AbstractButton.LEADING);
	    redoButton.setActionCommand("redo");
	    redoButton.addActionListener( this );
	    redoButton.setEnabled(false);
	    
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.add(undoButton);
	    buttonPanel.add(redoButton);
	    
	    // Text Panel
	    JPanel historyPanel = new JPanel();

	    textArea = new JTextArea(5, 25);
	    textArea.setEditable(false);
	    textArea.setText("Memento Example started");
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    scrollPane.setPreferredSize(new Dimension(350, 280));
	    historyPanel.add(scrollPane, BorderLayout.CENTER);
	    
	    // Panel with text edit buttons
	    stateField.setVisible(false);
	    stateField.setPreferredSize(new Dimension(200,30));
	    stateLabel.setVisible(true);
	    stateLabel.setPreferredSize(new Dimension(200,30));
	    
	    saveButton.setVerticalTextPosition(AbstractButton.CENTER);
	    saveButton.setHorizontalTextPosition(AbstractButton.LEADING);
	    saveButton.setActionCommand("save");
	    saveButton.addActionListener( this );
	    saveButton.setVisible(false);
	    
	    editButton.setVerticalTextPosition(AbstractButton.CENTER);
	    editButton.setHorizontalTextPosition(AbstractButton.LEADING);
	    editButton.setActionCommand("edit");
	    editButton.addActionListener( this );
	    editButton.setVisible(true);
	   
	    JPanel textPanel = new JPanel();
	    textPanel.add(stateLabel);
	    textPanel.add(editButton);
	    textPanel.add(stateField);
	    textPanel.add(saveButton);


	    // Main frame setup
        JFrame frame = new JFrame("Memento Example");
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(historyPanel, BorderLayout.CENTER);
        frame.add(textPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "undo") {
        	
        	System.out.println("Undo action event triggered");
        	textArea.setText(textArea.getText() + "\nUndo action event triggered!");
        	        	
        	//UNDO
        	state = state - 1;
        	originator.restoreFromMemento(savedStates.get(state));
        	updateButtons();
        	
        } else if(e.getActionCommand() == "redo") {
        	
        	System.out.println("Redo action event triggered");
        	textArea.setText(textArea.getText() + "\nRedo action event triggered");
        	
        	//REDO
        	state = state + 1;
        	originator.restoreFromMemento(savedStates.get(state));
        	updateButtons();
        	
        } else if(e.getActionCommand() == "edit") {
        	
        	System.out.println("Edit action event triggered");
        	textArea.setText(textArea.getText() + "\nEdit action event triggered");
        	
        	//EDIT
        	stateField.setText(stateLabel.getText());
        	toggleButtons();
        	
        	undoButton.setEnabled(false);
        	redoButton.setEnabled(false);
        	
        } else if(e.getActionCommand() == "save") {
        	
        	//SAVE
        	stateLabel.setText(stateField.getText());
        	toggleButtons();
        	originator.set(stateField.getText());
            savedStates.add(originator.saveToMemento());
            state = savedStates.size() - 1;
            updateButtons();
            
        }
    }
	
	private void toggleButtons() {
    	editButton.setVisible(!editButton.isVisible());
    	saveButton.setVisible(!saveButton.isVisible());
    	stateLabel.setVisible(!stateLabel.isVisible());
    	stateField.setVisible(!stateField.isVisible());
	}
	
	private void updateButtons() {
		System.out.println("state:"+state+",size:"+savedStates.size());
		stateLabel.setText(originator.get());
		textArea.setText(textArea.getText() + "\nText Changed into: " + originator.get());
		if(state > 0)
			undoButton.setEnabled(true);
		else
			undoButton.setEnabled(false);
		if(state < savedStates.size()-1)
			redoButton.setEnabled(true);
		else
			redoButton.setEnabled(false);
	}
}
