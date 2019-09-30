import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

class Produs
{
	private String name;
	private double price;
	private float quantity;
	
	public Produs(String name,double price,float quantity)
	{
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	// get functions
	public String getN() { return name;}
	public double getPrice() { return price;}
	public float getQ() { return quantity;}
	
	public String ToString()
	{
		return (name + "---- "+ price +"----" + quantity);
	}
}

public class MagazinApp {

	public static void main(String[] args) throws IOException {
		String file = "Magazin.txt";
		//for reading the file
		BufferedReader file_in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		//an array of produse
		Produs[] produse = new Produs[5];
		//local variables
		String line = null;
		//storing the separeted values in clean line
		String[] cleanLine = null;
		int i = 0;
		while((line = file_in.readLine()) != null)
		{
			//delimitations set by delimitator ';' stored in clean line
			cleanLine = line.split(";");
			// the separated strings are converted and put inside our array of Produse
			produse[i] = new Produs(cleanLine[0],Double.parseDouble(cleanLine[1]),Float.parseFloat(cleanLine[2]));
			i++;
		}
		file_in.close();
		Produs ProdusMax = new Produs(null,0,0);
		Produs ProdusMin = produse[0];
		//Getting the min and max of the function
		for(int index = 1;index<produse.length;index++)
		{
			if(ProdusMin.getPrice() > produse[index].getPrice())
			{
				ProdusMin = produse[index];		
			}
			if(ProdusMax.getPrice() < produse[index].getPrice())
			{
				ProdusMax = produse[index];
			}
		}
		//Writes the min and max to an output file
		PrintWriter writer = new PrintWriter("destinatie.txt","UTF-8");
		writer.println(ProdusMin.ToString());
		writer.println(ProdusMax.ToString());
		writer.close();
		//getting input from keyboard
		BufferedReader reader = new BufferedReader ( new InputStreamReader(System.in));
		System.out.println("Dati o cantitate:");
		String input;
		input = reader.readLine();
		//converting to float
		float quantity = Float.parseFloat(input);
		//like a table head
		System.out.println("Denumire  Pret  Cantitate");
		//looping through our produse array and print the lowest
		for(int index = 0;index<produse.length;index++)
		{
			if(produse[index].getQ() < quantity)
				System.out.println(produse[index].ToString());
		}
	}

}
