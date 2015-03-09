package opdracht11_3;

import java.io.Serializable;

public class Vak implements Serializable {
	private String vakCode;
	private int aantalPunten;

	public Vak(String vC, int aP)
	{
		vakCode = vC;
		aantalPunten = aP;
	}

	public String getVakCode() {
		return vakCode;
	}

	public void setVakCode(String vc)
	{
		vakCode = vc;
	}

	public int getAantalPunten() {
		return aantalPunten;
	}

	public String toString() {
		return vakCode + " is " + aantalPunten + " punten waard";
	}
}
