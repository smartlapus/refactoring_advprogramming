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
	    
	    JPanel historyPanel = new JPanel();

	    textArea = new JTextArea(5, 25);
	    textArea.setEditable(false);
	    textArea.setText("Memento Example started");
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    scrollPane.setPreferredSize(new Dimension(350, 280));
	    historyPanel.add(scrollPane, BorderLayout.CENTER);
	    
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
        if(e.getActionCommand() == ActionCommands.undo.toString()) {
          	updateTextAreaWithValue("\nUndo action event triggered!");	
        	updateState(-1);
        	originator.restoreFromMemento(savedStates.get(state));
        	updateButtons();
        	
        } else if(e.getActionCommand() == ActionCommands.redo.toString()) {
          	updateTextAreaWithValue("\nRedo action event triggered");
        	updateState(1);
        	originator.restoreFromMemento(savedStates.get(state));
        	updateButtons();
        	
        } else if(e.getActionCommand() == ActionCommands.edit.toString()) {
           updateTextAreaWithValue("\nEdit action event triggered");
        	stateField.setText(stateLabel.getText());
        	toggleButtons();
        	undoButton.setEnabled(false);
        	redoButton.setEnabled(false);	
        } else if(e.getActionCommand() == ActionCommands.save.toString()) {
        	stateLabel.setText(stateField.getText());
        	toggleButtons();
        	originator.set(stateField.getText());
            savedStates.add(originator.saveToMemento());
            state = savedStates.size() - 1;
            updateButtons();       
        }
    }
	
	private void updateTextAreaWithValue(String textValue){
		textArea.setText("" + textArea.getText() + textValue);
	}
	
	private void updateState(int value){
		state = state + value;
	}
	
	private void toggleButtons() {
    	editButton.setVisible(!editButton.isVisible());
    	saveButton.setVisible(!saveButton.isVisible());
    	stateLabel.setVisible(!stateLabel.isVisible());
    	stateField.setVisible(!stateField.isVisible());
	}
	
	private void updateButtons() {
		stateLabel.setText(originator.get());
		textArea.setText(textArea.getText() + "\nText Changed into: " + originator.get());
		if(state > 0){
			undoButton.setEnabled(true);
		}
		else{
			undoButton.setEnabled(false);
		}
		if(state < savedStates.size()-1){
			redoButton.setEnabled(true);
		}
		else{
			redoButton.setEnabled(false);
		}
	}
	
	public enum ActionCommands {
		undo ("Undo"),
		redo ("Redo"),
		edit ("Edit"),
		save ("Save");
		
		private final String action;
		
		private ActionCommands(String s){
			action = s;
		}
		
		public String toString(){
			return action;
		}
	}
}


