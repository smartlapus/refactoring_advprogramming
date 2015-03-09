package opdracht11_3;

import javax.swing.UIManager;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {

	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	FileInputStream fis	= new FileInputStream("student.obj");
    ObjectInputStream ois =	new ObjectInputStream(fis);
	
	Student s = (Student)ois.readObject();
	ois.close();		

	StudentFrame sf = new StudentFrame();
	sf.setDeStudent(s);
	sf.refresh();
	}
}
