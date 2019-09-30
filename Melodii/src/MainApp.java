import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

class Melodie
{
	private String melodie;
	private String artist;
	private int an;
	public Melodie(String melodie, String artist, int an) {
		super();
		this.melodie = melodie;
		this.artist = artist;
		this.an = an;
	}
	public String getMelodie() {
		return melodie;
	}
	public void setMelodie(String melodie) {
		this.melodie = melodie;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getAn() {
		return an;
	}
	public void setAn(int an) {
		this.an = an;
	}
	@Override
	public String toString() {
		return "Melodie [melodie=" + melodie + ", artist=" + artist + ", an=" + an + "]";
	}
	
	
}
public class MainApp {

	public static void main(String[] args) throws SQLException, NumberFormatException, IOException {
		ArrayList<Melodie> melodii=new ArrayList<Melodie>();
		
		String url="jdbc:mysql://localhost:3306/test";
		Connection con = DriverManager.getConnection(url, "root", "root");
		Statement stmt = (Statement)con.createStatement();
		ResultSet rs = stmt.executeQuery("Select *from melodii");
		
		rs.beforeFirst();
		while(rs.next())
		{
			melodii.add(new Melodie(rs.getString(1),rs.getString(2),rs.getInt((3))));
		}
		System.out.println("Meniu");
		int option;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do
		{
			
			System.out.println("1.Afisare");
			System.out.println("2.Stergere");
			System.out.println("3.Dati optiunea");
			option=Integer.parseInt(br.readLine());
			
			switch(option)
			{
			case 1:
				for(Melodie m : melodii)
				{
					System.out.println(m.toString());
				}
				break;
			case 2:
				String melodie=JOptionPane.showInputDialog(null, "Numele melodiei","Adauga nume melodie",1);
				for(Melodie m : melodii)
				{
					if(m.getMelodie().equals(melodie))
					{
						melodii.remove(m);
					}
				}
				break;
			}
		}while(option!=0);
	}

}
