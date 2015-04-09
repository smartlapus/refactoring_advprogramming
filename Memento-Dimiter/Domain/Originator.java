package nl.hu.PaF.Memento.Domain;

public class Originator {
	
	private String state;
    
    public void set(String newState) {
        this.state = newState;
    }
    
    public String getState() {
    	return state;
    }
 
    public Memento saveToMemento() {
        return new Memento(state);
    }
 
    public void restoreFromMemento(Memento memento) {
        state = memento.getSavedState();
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
