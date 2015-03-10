package nl.hu.PaF.Memento.Controller;

import java.util.ArrayList;
import java.util.List;

import nl.hu.PaF.Memento.Domain.Originator;
import nl.hu.PaF.Memento.View.*;

public class Caretaker {
	
	private static ViewController vc = new ViewController();

	public static void main(String[] args) {
		
		List<Originator.Memento> savedStates = new ArrayList<Originator.Memento>();
		
		
		
        Originator originator = new Originator();
        
        vc.createGui(originator, savedStates);
        
	} 

}
