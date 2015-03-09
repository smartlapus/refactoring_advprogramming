package opdracht11_3;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

 public class ResultaatInvoerPanel extends JPanel {
	 private Resultaat hetResultaat;
	 private JTextField vakTextField, cijferTextField, puntenTextField;
	 private JLabel uitvoerLabel;

	 public ResultaatInvoerPanel() {
		 setLayout(new BorderLayout());
		 JPanel hulpPanel = new JPanel();
		 hulpPanel.setLayout(new GridLayout(3,2));
		 add(hulpPanel, BorderLayout.CENTER);

		 JLabel label1 = new JLabel("Cijfer");
		 hulpPanel.add(label1);
		 cijferTextField = new JTextField("", 12);
		 hulpPanel.add(cijferTextField);

		 JLabel label2 = new JLabel("Vak");
		 hulpPanel.add(label2);
		 vakTextField = new JTextField("", 12);
		 hulpPanel.add(vakTextField);


		 JLabel label3 = new JLabel("Punten");
		 hulpPanel.add(label3);
		 puntenTextField = new JTextField("", 12);
		 hulpPanel.add(puntenTextField);

		 uitvoerLabel = new JLabel("<html><b>hier komt de invoer te staan</b></html>");
		 add(uitvoerLabel, BorderLayout.NORTH);

		 setVisible(true);
	 }

	 public void setHetResultaat(Resultaat nwResultaat) {
		 hetResultaat = nwResultaat;
	 }

	 public Resultaat getHetResultaat() {
		 return hetResultaat;
	 }

	 public void flush() {
		 if(hetResultaat == null) {
			 uitvoerLabel.setText("Invoeren van gegevens is niet mogelijk");
		 }
		 else {
			 if(!vakTextField.getText().equals("") || !puntenTextField.getText().equals("")) {
				 int aantal = 0;
				 String vak = "";

				 if(!puntenTextField.getText().equals(""))
				 {
					 aantal = Integer.parseInt(puntenTextField.getText());
				 }
				 else {
					 aantal = hetResultaat.getHetVak().getAantalPunten();
				 }

				 if(!vakTextField.getText().equals(""))
				 {
				 	 vak = vakTextField.getText();
				 }
				 else {
					 vak = hetResultaat.getHetVak().getVakCode();
				 }

				 Vak v = new Vak(vak, aantal);
				 hetResultaat.setHetVak(v);
			 }

			 if(!cijferTextField.getText().equals("")) {
				 hetResultaat.setCijfer(Double.parseDouble(cijferTextField.getText()));
			 }
			 uitvoerLabel.setText("" + hetResultaat);
		 }
	 }

	 public void plaatsTekstInLabel(String s) {
		 uitvoerLabel.setText(s);
	 }
 }
