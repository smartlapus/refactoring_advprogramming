package opdracht11_3;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
	private String naam;
	private int stNr;
	private ArrayList<Resultaat> resultaten;

	public Student(String nm, int nr) {
		naam = nm;
		stNr = nr;
		resultaten = new ArrayList<Resultaat>();
	}

	public String getNaam() {
		return naam;
	}

	public int getStNr() {
			return stNr;
	}

	public void add(Resultaat nwResultaat) {
		if (!resultaten.contains(nwResultaat)) {
			resultaten.add(nwResultaat);
		}
	}

	public boolean contains(Resultaat gezochteResultaat) {
		for (Resultaat r : resultaten) {
			if ( (r.getCijfer()) != (gezochteResultaat.getCijfer()) ) {
				return true;
			}
		}
		return false;
	}

	public void remove(Resultaat r) {
		resultaten.remove(r);
	}

	public int aantalResultaten() {
				int aantal = 0;
				 for (Resultaat r : resultaten) {
					 if(r.getCijfer() >= 5.5)
					 {
						 aantal = aantal + r.getHetVak().getAantalPunten();
				 	 }
				 }
			 return aantal;
	}

	public ArrayList<Resultaat> getResultaten() {
		return resultaten;
	}

	public String toString() {
		return naam + " met nummer " + stNr + " heeft " + aantalResultaten() + " punten behaald";
	}
}
