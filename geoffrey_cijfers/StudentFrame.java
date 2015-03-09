package opdracht11_3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;

public class StudentFrame extends JFrame implements ActionListener, ListSelectionListener {

	 private Student deStudent;
	 private Resultaat geselecteerdeResultaat;
	 private JLabel keuzelijstLabel;
	 private JList resultatenLijst;
	 private JLabel uitvoerLabel;
	 private JButton updateButton, nieuwButton, verwijderButton, saveButton;

	 public StudentFrame() {
		 setLayout(new BorderLayout());
		 uitvoerLabel = new JLabel();
		 add(uitvoerLabel, BorderLayout.NORTH);

		 JPanel resultatenPanel = new JPanel();
		 resultatenPanel.setPreferredSize(new Dimension(400, 200));
		 add(resultatenPanel, BorderLayout.CENTER);
		 keuzelijstLabel = new JLabel("<html><b>Kies hier een resultaat</b></html>");
		 resultatenPanel.add(keuzelijstLabel);

		 resultatenLijst = new JList();
		 resultatenLijst.addListSelectionListener(this);
		 resultatenPanel.add(new JScrollPane(resultatenLijst));

		 JPanel knoppenPanel = new JPanel();
		 add(knoppenPanel, BorderLayout.SOUTH);

		 updateButton = new JButton("<html><b>Update resultaat</b></html>");
		 knoppenPanel.add(updateButton);
		 updateButton.addActionListener(this);

		 nieuwButton = new JButton("<html><b>Nieuw resultaat</b></html>");
		 knoppenPanel.add(nieuwButton);
		 nieuwButton.addActionListener(this);

		 verwijderButton = new JButton("<html><b>Verwijder resultaat</b></html>");
		 knoppenPanel.add(verwijderButton);
		 verwijderButton.addActionListener(this);
		 
		 saveButton = new JButton("<html><b>Save</b></html>");
		 knoppenPanel.add(saveButton);
		 saveButton.addActionListener(this);

		 setTitle("StudentFrame");
		 pack();
		 setVisible(true);
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 }

	 public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == updateButton)
		 {
			 if(geselecteerdeResultaat == null) {
				 uitvoerLabel.setText("<html><b>Selecteer eerst een bestaande resultaat, of kies voor de optie 'Nieuw resultaat'</b></html>");
			 }
			 else {
				 ResultaatInvoerPanel rip = new ResultaatInvoerPanel();
				 rip.plaatsTekstInLabel("<html><b>Wijzig gegevens: " + geselecteerdeResultaat + "</b></html>");
				 rip.setHetResultaat(geselecteerdeResultaat);

				 int result = JOptionPane.showConfirmDialog(this, rip, "update resultaat", JOptionPane.OK_CANCEL_OPTION);

				 if(result == JOptionPane.OK_OPTION) {
					 rip.flush();
					 refresh();
					 uitvoerLabel.setText("<html><b>Het resultaat is nu gewijzigd</b></html>");
				 }
			 }
		 }
		 else if(e.getSource() == nieuwButton)
		 {
			 Resultaat nieuw = new Resultaat();
			 ResultaatInvoerPanel rip = new ResultaatInvoerPanel();
			 rip.plaatsTekstInLabel("<html><b>Voer gegevens nieuw resultaat in</b></html>");
			 rip.setHetResultaat(nieuw);

			 int result = JOptionPane.showConfirmDialog(this, rip, "nieuw resultaat", JOptionPane.YES_NO_OPTION);

			 if(result == JOptionPane.YES_OPTION) {
				 rip.flush();
				 deStudent.add(nieuw);
				 refresh();
				 uitvoerLabel.setText("<html><b>Het resultaat is toegevoegd</b></html>");
			 }
		 }
		 else if(e.getSource() == verwijderButton)
		 {
			 if(geselecteerdeResultaat == null) {
				 uitvoerLabel.setText("<html><b>U moet eerst een resultaat selecteren</b></html>");
			 }
			 else {
				 int result = JOptionPane.showConfirmDialog(this, geselecteerdeResultaat, "Weet u zeker dat u het resultaat wilt verwijderen?", JOptionPane.YES_NO_OPTION);

				 if(result == JOptionPane.YES_OPTION) {
					 Resultaat hulp = geselecteerdeResultaat;
					 deStudent.remove(geselecteerdeResultaat);
					 refresh();
					 uitvoerLabel.setText("<html><b>Het resultaat: " + hulp + " is nu gewist</b></html>");
				 }
			 }
		 }
		 else if(e.getSource() == saveButton)
		 {
			 try {
				 FileOutputStream fos = new FileOutputStream("student.obj");
				 ObjectOutputStream oos = new ObjectOutputStream(fos);
				 oos.writeObject(deStudent);
				 oos.close();
			 }
			 catch (IOException ioe) {
				 ioe.printStackTrace();
			 }
		 }
	 }

	 public void valueChanged(ListSelectionEvent e) {
		 geselecteerdeResultaat = (Resultaat)resultatenLijst.getSelectedValue();
	 }

	 public void setDeStudent(Student nweStudent) {
		 deStudent = nweStudent;
	 }

	 public Student getDeStudent() {
		 return deStudent;
	 }

	 public void refresh() {
		 if(deStudent != null) {
			 plaatsTekstInLabel("<html><b>" + deStudent.toString() + "</b></html>");
			 ArrayList<Resultaat> lijst = deStudent.getResultaten();
			 resultatenLijst.setListData(lijst.toArray());
		 }
	 }

	 public void plaatsTekstInLabel(String s) {
		 uitvoerLabel.setText(s);
	 }
}