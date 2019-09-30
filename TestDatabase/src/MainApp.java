import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


class Restaurant
{
	String restaurant,specific,zona;

	public Restaurant(String restaurant, String specific, String zona) {
		super();
		this.restaurant = restaurant;
		this.specific = specific;
		this.zona = zona;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getSpecific() {
		return specific;
	}

	public void setSpecific(String specific) {
		this.specific = specific;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@Override
	public String toString() {
		return "Restaurant=" + restaurant + ", specific=" + specific + ", zona=" + zona;
	}
	
	
}

class Window1 extends JFrame
{
	static Vector restaurante=new Vector();
	static JList lstRestaurante;
	static DefaultListModel listM;
	public Window1(String title) throws HeadlessException, SQLException {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setSize(400,400);
		listM=new DefaultListModel<>();
		lstRestaurante= new JList(listM);
		
		String url = "jdbc:mysql://localhost:3306/test";
		Connection con=DriverManager.getConnection(url, "root", "root");
		Statement stmt = (Statement)con.createStatement();
		ResultSet rs=stmt.executeQuery("Select *from restaurante");
		JLabel titlu=new JLabel("Lista Restaurante");
		getContentPane().add(titlu, BorderLayout.NORTH);
		getContentPane().add(lstRestaurante,BorderLayout.CENTER);
		
		rs.first();
		while(rs.next())
		{
			Restaurant res=new Restaurant(rs.getString(1),rs.getString(2),rs.getString(3));
			restaurante.add(res);
		}
		
		for(int i=0;i<restaurante.size();i++)
		{
			Restaurant r = (Restaurant) restaurante.elementAt(i);
			listM.addElement(r.toString());
		}
	}
	
}

class Window2 extends JFrame
{
	static Vector melodii=new Vector();
	static JButton btnSterge= new JButton("Sterge");
	static JLabel  melodie=new JLabel("Melodie");
	static JTextField txtMelodie= new JTextField(15);
	static DefaultListModel listMod=new DefaultListModel<>();
	static JList lstMelodii=new JList(listMod);
	static Connection con;
	static Statement stmt;
	static ResultSet rs;
	public Window2(String title) throws HeadlessException, SQLException {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2,1));
		setSize(400,400);
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.add(melodie);
		panel1.add(txtMelodie);
		panel1.add(btnSterge);
		
		String url = "jdbc:mysql://localhost:3306/test";
		con=DriverManager.getConnection(url, "root", "root");
		stmt = (Statement)con.createStatement();
		rs=stmt.executeQuery("Select *from melodii");
		
		getContentPane().add(panel1);
		getContentPane().add(lstMelodii);
		rs.beforeFirst();
		while(rs.next()) {
			melodii.add(rs.getString(1));
			
		}
		for(int i=0;i<melodii.size();i++)
		{
			listMod.addElement(melodii.elementAt(i));
		}
		btnSterge.addActionListener(new Clicked());
	}
}

class Clicked implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		String melodieS=Window2.txtMelodie.getText();
		for(int i=0;i<Window2.melodii.size();i++)
		{
			if(Window2.melodii.elementAt(i).equals(melodieS))
			{
				Window2.melodii.remove(i);
				Window2.listMod.removeElementAt(i);
				try {
					PreparedStatement deleteStmt=Window2.con.prepareStatement("Delete from melodii where melodie=?");
					deleteStmt.setString(1, melodieS);
					deleteStmt.execute();
					Window2.rs=Window2.stmt.executeQuery("Select *from melodii");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
}

public class MainApp {

	public static void main(String[] args) throws HeadlessException, SQLException {
		//JFrame f = new Window1("Restaurante");
		JFrame f=new Window2("Melodii");
		f.setVisible(true);
	}

}
