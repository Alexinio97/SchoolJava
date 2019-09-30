package testlab;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

class Carte
{
	String titlu,autor,an;

	public Carte(String titlu, String autor, String an) {
		super();
		this.titlu = titlu;
		this.autor = autor;
		this.an = an;
	}

	@Override
	public String toString() {
		return titlu + ", " + autor + ", " + an;
	}

	
	//getters and setters
	public String getTitlu() {
		return titlu;
	}

	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAn() {
		return an;
	}

	public void setAn(String an) {
		this.an = an;
	}

}

class Window extends JFrame
{
	static JTextField txttitlu,txtautor,txtan;
	static JLabel lblTitlu,lblAutor,lblAn;
	static JButton btnAdaugare,btnAfisare,btnAfisareOrd;
	static JList lista;
	static ArrayList<Carte> carti = new ArrayList<Carte>();
	static DefaultListModel listM = new DefaultListModel<>();
	public Window(String title) throws HeadlessException {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new GridLayout(5, 1));
		setSize(400,400);
		
		//instantiate
		txttitlu = new JTextField(20);
		txtautor = new JTextField(20);
		txtan = new JTextField(20);
		
		lblTitlu= new JLabel("Titlu");
		lblAutor= new JLabel("Autor");
		lblAn = new JLabel("An");
		
		//subpanel
		JPanel firstRow = new JPanel(new FlowLayout());
		firstRow.add(lblTitlu);
		firstRow.add(txttitlu);
		JPanel secondRow = new JPanel(new FlowLayout());
		secondRow.add(lblAutor);
		secondRow.add(txtautor);
		JPanel thirdRow = new JPanel(new FlowLayout());
		thirdRow.add(lblAn);
		thirdRow.add(txtan);
		
		getContentPane().add(firstRow);
		getContentPane().add(secondRow);
		getContentPane().add(thirdRow);
		
		//buttons
		btnAdaugare = new JButton("Adaugare");
		btnAfisare = new JButton("Afisare");
		btnAfisareOrd = new JButton("Afisare orodnata");
		
		JPanel fourthRow = new JPanel(new FlowLayout());
		fourthRow.add(btnAdaugare);
		fourthRow.add(btnAfisare);
		fourthRow.add(btnAfisareOrd);
		getContentPane().add(fourthRow);
		
		
		
		lista = new JList(listM);
		lista.setPreferredSize(new Dimension(380,100));
		
		//adding listeners
		btnAdaugare.addActionListener(new Clicked());
		btnAfisare.addActionListener(new Clicked());
		btnAfisareOrd.addActionListener(new Clicked());
		//adding fifthRow
		JPanel fifthRow = new JPanel(new FlowLayout());
		fifthRow.add(lista);
		getContentPane().add(fifthRow);
		
	}
	
}

class Clicked implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(Window.btnAdaugare))
		{
			Window.carti.add(new Carte(Window.txttitlu.getText(),Window.txtautor.getText(),Window.txtan.getText()));
		}
		if(arg0.getSource().equals(Window.btnAfisare))
		{
			Window.listM.clear();
			for(Carte c : Window.carti)
			{
				Window.listM.addElement(c.toString());
				System.out.println(c.toString());
			}
		}
		if(arg0.getSource().equals(Window.btnAfisareOrd))
		{
			Window.listM.clear();
			Collections.sort(Window.carti, new Compare());
			for(Carte c : Window.carti)
			{
				Window.listM.addElement(c.toString());
				System.out.println(c.toString());
			}
		}
	}
	
}

class Compare implements Comparator<Carte>
{

	@Override
	public int compare(Carte c1, Carte c2) {
		return c1.titlu.compareToIgnoreCase(c2.titlu);
	}
	
}

public class MainApp {

	public static void main(String[] args) {
		JFrame f = new Window("Carti");
		f.setVisible(true);
	}

}
