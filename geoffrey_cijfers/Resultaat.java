package opdracht11_3;

import java.io.Serializable;

public class Resultaat implements Serializable {
	 private double cijfer;
	 private Vak hetVak;

	 public Resultaat() {
		 cijfer = 0;
	 }

	 public Resultaat(double cf, Vak hV) {
		 cijfer = cf;
		 hetVak = hV;
	 }

	 public void updateResultaat(Resultaat up) {
		 cijfer = up.cijfer;
		 hetVak = up.hetVak;
	 }

	 public void updateResultaat(double cf, Vak hV) {
		 cijfer = cf;
		 hetVak = hV;
	 }

	 public int behaaldePunten()
	 {
	 	 if(cijfer >= 5.5)
	 	 {
			 return hetVak.getAantalPunten();
		 }
		 else
		 {
			 return 0;
		 }
	 }

	 public void setHetVak(Vak hV)
	 {
	 	 hetVak = hV;
	 }

	 public Vak getHetVak()
	 {
	 	 return hetVak;
	 }

	 public void setCijfer(double cf) { cijfer = cf; }

	 public double getCijfer() { return cijfer; }

	 public String toString() {
		 if(hetVak != null)
		 {
		 	return "cijfer " + cijfer + " voor het vak " + hetVak.getVakCode() + " en heeft " + behaaldePunten() + " punten behaald                   ";
	 	 }
	 	 else
	 	 {
			return "cijfer " + cijfer + " voor het vak onbekend";
		 }
	 }
}
