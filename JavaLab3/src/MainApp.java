import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;



class Melodie{
	private String nume_melodie;
	
	public int getNumar_viz() {
		return numar_viz;
	}

	public void setNumar_viz(int numar_viz) {
		this.numar_viz = numar_viz;
	}

	private String nume_artist;
	private int an_lansare;
	private int numar_viz;
	
	public Melodie(String nume_melodie,String nume_artist,int an_lansare,int numar_viz) {
		this.nume_melodie = nume_melodie;
		this.nume_artist = nume_artist;
		this.an_lansare = an_lansare;
		this.numar_viz = numar_viz;
	}
	
	public String toString()
	{
		return "Nume:"+nume_artist+"| Artist:" + nume_artist+"| An:" + an_lansare+"| Viz:"+ numar_viz;
	}
	
}

public class MainApp {

	public static void main(String[] args) throws IOException {
		String file_in = "in.txt";
		BufferedReader flux_in = new BufferedReader(new FileReader(file_in));
		Melodie[] melodii = new Melodie[2];
		String line;
		String[] cleanLine;
		int i=0;
		while((line = flux_in.readLine())!= null )
		{
			cleanLine = line.split("\\s");
			melodii[i] = new Melodie(cleanLine[0],cleanLine[1],Integer.parseInt(cleanLine[2]),Integer.parseInt(cleanLine[3]));
			i++;
		}
		for(int index=0 ;index<melodii.length;index++)
		{
			System.out.println(melodii[index].toString());
		}
		
		System.out.println(Arrays.asList(melodii));
	}

}
