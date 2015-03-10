package nl.hu.PaF.Memento.Domain;

public class Originator {
	
	private String state;
    
    public void set(String newState) {
        System.out.println("Originator: Setting state to " + newState);
        this.state = newState;
    }
    
    public String get() {
    	return state;
    }
 
    public Memento saveToMemento() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(state);
    }
 
    public void restoreFromMemento(Memento memento) {
        state = memento.getSavedState();
        System.out.println("Originator: State after restoring from Memento: " + state);
    }

	public class Memento {
		private final String savedState;

	    public Memento(String state) {
	        savedState = state;
	    }
	
	    public String getSavedState() {
	        return savedState;
	    }
	    
	}

}
