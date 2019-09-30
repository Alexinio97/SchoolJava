import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.mysql.cj.jdbc.*;

class Window extends JFrame 
{
	//static variables
	static JLabel id,nume,varsta;
	static JTextField txtId,txtNume,txtVarsta,txtNav;
	//buttons for navbar
	static JButton btnFirst,btnPrevious,btnNext,btnLast;
	static JButton btnEdit,btnSave,btnUndo,btnDelete,btnAdd,btnFind;
	static int totalRows;
	static int currentRow;

	public Window(String title) throws HeadlessException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,350);
		getContentPane().setLayout(new BorderLayout());
		JPanel center = new JPanel(new FlowLayout());
		JPanel center2 = new JPanel(new FlowLayout());
		JToolBar nav = new JToolBar();

		//database settings
		String url = "jdbc:mysql://localhost:3306/test";
		Statement sql=null;
		ResultSet rs=null;
		Connection con;
		con = DriverManager.getConnection (url, "root", "root");
		sql = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rs = sql.executeQuery("select * from Persoane ");
		rs.last();
		
		//storing the number of last row in 'totalRows'
		Window.totalRows=rs.getRow();

		//positioning our cursor back to our first row
		rs.first();
		Window.currentRow = rs.getRow();
		//instantiating buttons
		btnFirst = new JButton();
		btnPrevious = new JButton();
		btnNext = new JButton(); 
		btnLast = new JButton();
		btnEdit = new JButton();
		btnSave = new JButton();
		btnUndo = new JButton();
		btnAdd = new JButton();
		btnDelete = new JButton();
		btnFind = new JButton();

		txtNav = new JTextField();
		//adding buttons to navigation bar
		nav.add(btnFirst);
		nav.add(btnPrevious);
		nav.add(txtNav);
		nav.add(btnNext);
		nav.add(btnLast);
		nav.add(btnEdit);
		nav.add(btnSave);
		nav.add(btnUndo);
		nav.add(btnAdd);
		nav.add(btnDelete);
		nav.add(btnFind);


		//settings for textfields and adding them to a second panel
		txtId = new JTextField();
		txtNume = new JTextField();
		txtVarsta = new JTextField();
		id = new JLabel("Id:");
		id.setPreferredSize(new Dimension(100,20));
		nume = new JLabel("Nume:");
		nume.setPreferredSize(new Dimension(100,20));
		varsta = new JLabel("Varsta:");
		varsta.setPreferredSize(new Dimension(100,20));

		txtId.setPreferredSize(new Dimension(240,30));
		txtNume.setPreferredSize(new Dimension(240,30));
		txtVarsta.setPreferredSize(new Dimension(240,30));
		center.add(id);
		center.add(txtId);
		center.add(nume);
		center.add(txtNume);
		center.add(varsta);
		center.add(txtVarsta);

		getContentPane().add(nav,BorderLayout.NORTH);
		getContentPane().add(center,BorderLayout.CENTER);

		//deactivating text fields
		txtNav.setEditable(false);
		txtId.setEditable(false);
		txtNume.setEditable(false);
		txtVarsta.setEditable(false);

		//positioning our cursor back to our first row
		rs.first();
		//getting our first row into textfields
		if(rs.first())
		{
			txtId.setText(rs.getString(1));
			txtNume.setText(rs.getString(2));
			txtVarsta.setText(rs.getString(3));
		}
		//could improve that to update total rows
		txtNav.setText(currentRow + "/" +totalRows);
		//adding images to buttons
		btnFirst.setIcon(new ImageIcon("C:\\JavaDev\\Database\\Imagini\\MoveFirst.png"));
		btnPrevious.setIcon(new ImageIcon("C:\\JavaDev\\Database\\Imagini\\MovePrevious.png"));
		btnNext.setIcon(new ImageIcon("C:\\JavaDev\\Database\\Imagini\\MoveNext.png"));
		btnLast.setIcon(new ImageIcon("C:\\JavaDev\\Database\\Imagini\\MoveLast.png"));
		btnEdit.setIcon(new ImageIcon("C:\\JavaDev\\Database\\Imagini\\Edit.png"));
		btnSave.setIcon(new ImageIcon("C:\\JavaDev\\Database\\Imagini\\save.JPG"));
		btnUndo.setIcon(new ImageIcon("C:\\JavaDev\\Database\\Imagini\\undo.JPG"));
		btnAdd.setIcon(new ImageIcon("C:\\JavaDev\\Database\\Imagini\\Add.png"));
		btnDelete.setIcon(new ImageIcon("C:\\JavaDev\\Database\\Imagini\\Delete.png"));
		btnFind.setIcon(new ImageIcon("C:\\JavaDev\\Database\\Imagini\\find.JPG"));

		//listeners for buttons
		btnNext.addActionListener(new Clicked(rs));
		btnPrevious.addActionListener(new Clicked(rs));
		btnLast.addActionListener(new Clicked(rs));
		btnFirst.addActionListener(new Clicked(rs));
		btnEdit.addActionListener(new Clicked());
		btnSave.addActionListener(new Clicked(con));
		btnUndo.addActionListener(new Clicked());
		btnDelete.addActionListener(new Clicked(con,rs));
		btnAdd.addActionListener(new Clicked(rs));
		btnFind.addActionListener(new Clicked(rs));


		//function to refresh our data after Modifications

	}

}


class Clicked implements ActionListener
{
	//database variables
	ResultSet rs;
	Connection con;
	PreparedStatement pr;
	Statement sql;
	//state of edit
	EditState edit;
	//constructor for updating the database
	public Clicked(Connection con) {
		super();
		this.con = con;
	}

	public Clicked(ResultSet rs) {
		super();
		this.rs = rs;
	}
	
	//constructor for save
	public Clicked(ResultSet rs,Connection con,Statement sql) {
		super();
		this.rs = rs;
		this.con=con;
		this.sql=sql;
	}

	//empty Constructor
	public Clicked()
	{
		super();
	}

	//constructor for deletion
	public Clicked(Connection con,ResultSet rs) {
		super();
		this.con = con;
		this.rs = rs;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(Window.btnNext))
		{
			try {
				if(rs.next())
				{
					Window.txtId.setText(rs.getString(1));
					Window.txtNume.setText(rs.getString(2));
					Window.txtVarsta.setText(rs.getString(3));
					Window.txtNav.setText(rs.getRow() + "/" + Window.totalRows);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(arg0.getSource().equals(Window.btnPrevious))
		{

			try {
				if(rs.previous())
				{
					Window.txtId.setText(rs.getString(1));
					Window.txtNume.setText(rs.getString(2));
					Window.txtVarsta.setText(rs.getString(3));
					Window.txtNav.setText(rs.getRow() + "/" + Window.totalRows );
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(arg0.getSource().equals(Window.btnLast))
		{
			try {
				if(rs.last())
				{
					Window.txtId.setText(rs.getString(1));
					Window.txtNume.setText(rs.getString(2));
					Window.txtVarsta.setText(rs.getString(3));
					Window.txtNav.setText(rs.getRow() + "/" + Window.totalRows);
				}
			}
			catch(SQLException s)
			{
				s.printStackTrace();
			}
		}
		if(arg0.getSource().equals(Window.btnFirst))
		{
			try {
				if(rs.first())
				{
					Window.txtId.setText(rs.getString(1));
					Window.txtNume.setText(rs.getString(2));
					Window.txtVarsta.setText(rs.getString(3));
					Window.txtNav.setText(rs.getRow() + "/" + Window.totalRows);
				}
			}
			catch(SQLException s)
			{
				s.printStackTrace();
			}
		}
		//go into edit state
		if(arg0.getSource().equals(Window.btnEdit))
		{
			//activating textboxes
			edit = new EditState(true);

		}
		//save our data and go back to original state
		if(arg0.getSource().equals(Window.btnSave))
		{
			try {
				pr = con.prepareStatement("Update Persoane set nume=?,varsta=? where id=?");
				pr.setString(1, Window.txtNume.getText());
				pr.setString(2, Window.txtVarsta.getText());
				pr.setString(3, Window.txtId.getText());
				pr.executeUpdate();

				edit = new EditState(false);

				//con.close(); 
				pr.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//refreshing our date in GUI after modifications
			try {
				sql = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs = sql.executeQuery("select * from Persoane ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//setting our buttons back to enable
		if(arg0.getSource().equals(Window.btnUndo))
		{
			edit = new EditState(false);
		}

		//for deleting a row
		if(arg0.getSource().equals(Window.btnDelete))
		{
			int dialogButton = 0;
			int dialogResult = JOptionPane.showConfirmDialog(null,"Sunteti sigur ca doriti sa stergeti aceasta persoana?","Waring",dialogButton,2);

			//make sure that the user wants to delete that person
			if(dialogResult == JOptionPane.YES_OPTION){
				try {
					pr = con.prepareStatement("Delete from  persoane where id=?");
					pr.setInt(1, Integer.parseInt(Window.txtId.getText()));
					pr.execute();

					//after deletion we should set the textfields to our first row in table
					//set first row in textfields
					if(rs.first())
					{
						Window.txtId.setText(rs.getString(1));
						Window.txtNume.setText(rs.getString(2));
						Window.txtVarsta.setText(rs.getString(3));
						Window.currentRow = rs.getRow();
						Window.txtNav.setText(Window.currentRow +"/" + Window.totalRows);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//refreshing our date in GUI after modifications
				try {
					sql = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
					rs = sql.executeQuery("select * from Persoane ");
					rs.last();
					Window.totalRows= rs.getRow();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		//adding button with a new window
		if(arg0.getSource().equals(Window.btnAdd))
		{
			//settings for our add window
			JFrame add = new JFrame("Adauga persoana");
			add.setSize(150, 150);
			String nume = JOptionPane.showInputDialog(add,"Nume","Adauga nume",1);
			String varsta = JOptionPane.showInputDialog(add, "Varsta","Adauga Varsta",1);
			try {
				//storing last Id from last row added
				rs.last();
				int lastId = rs.getInt(1);
				rs.moveToInsertRow();
				rs.updateInt(1,lastId+1);
				rs.updateString(2,nume);
				rs.updateInt("Varsta", Integer.parseInt(varsta));
				rs.insertRow();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//refreshing our date in GUI after modifications
			try {
				sql = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs = sql.executeQuery("select * from Persoane ");
				rs.last();
				Window.totalRows= rs.getRow();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//finding button
		if(arg0.getSource().equals(Window.btnFind))
		{
			String nume = JOptionPane.showInputDialog(null,"Nume","Adauga",1);
			try {
				while(rs.next())
				{
					if(rs.getString(2).equals(nume))
					{
						Window.txtId.setText(rs.getString(1));
						Window.txtNume.setText(rs.getString(2));
						Window.txtVarsta.setText(rs.getString(3));
						Window.currentRow = rs.getRow();
						Window.txtNav.setText(Window.currentRow +"/" + Window.totalRows);
						break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}



//class for edit state
class EditState{
	boolean state;

	public EditState(boolean state)
	{
		this.state= state;
		//edit disabled
		if(state == false)
		{
			Window.txtNume.setEditable(false);
			Window.txtVarsta.setEditable(false);

			//deactivating other buttons
			Window.btnFirst.setEnabled(true);
			Window.btnLast.setEnabled(true);
			Window.btnNext.setEnabled(true);
			Window.btnPrevious.setEnabled(true);
			Window.btnAdd.setEnabled(true);
			Window.btnDelete.setEnabled(true);
			Window.btnEdit.setEnabled(true);
			Window.btnFind.setEnabled(true);
		}
		else
		{
			Window.txtNume.setEditable(true);
			Window.txtVarsta.setEditable(true);

			//deactivating other buttons
			Window.btnFirst.setEnabled(false);
			Window.btnLast.setEnabled(false);
			Window.btnNext.setEnabled(false);
			Window.btnPrevious.setEnabled(false);
			Window.btnEdit.setEnabled(false);
			Window.btnAdd.setEnabled(false);
			Window.btnDelete.setEnabled(false);
			Window.btnFind.setEnabled(false);
		}
	}
}



public class  MainApp {
	public static void main(String[] args) throws HeadlessException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Window baza = new Window("Persoane");
		baza.setVisible(true);
	}
}
