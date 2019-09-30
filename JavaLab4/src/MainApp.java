import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



class Echipament{
	//variables
	private String denumire;
	private int nr_inv;
	private float pret;
	private String zona_mag;
	private Stare s;
	
	//setters and getters
	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public int getNr_inv() {
		return nr_inv;
	}

	public void setNr_inv(int nr_inv) {
		this.nr_inv = nr_inv;
	}

	public float getPret() {
		return pret;
	}

	public void setPret(float pret) {
		this.pret = pret;
	}

	public String getZona_mag() {
		return zona_mag;
	}

	public void setZona_mag(String zona_mag) {
		this.zona_mag = zona_mag;
	}

	public Stare getS() {
		return s;
	}

	public void setS(Stare s) {
		this.s = s;
	}
	
	//constructor
	public Echipament(String denumire,int nr_inv,float pret,String zona_mag,Stare s)
	{
		this.denumire=denumire;
		this.nr_inv=nr_inv;
		this.pret=pret;
		this.zona_mag=zona_mag;
		this.s=s;
	}
	
	@Override
	public String toString() {
		return "Echipament denumire=" + denumire + ", nr_inv=" + nr_inv
				+ ", pret=" + pret + ", zona_mag=" + zona_mag + ", s=" + s;
	}
}

class Imprimanta extends Echipament{
	private int ppm;
	private String rezolutie;
	private int p_car;
	private ModScriere ms;
	//getter and setter for MS enum
	public ModScriere getMs() {
		return ms;
	}
	public void setMs(ModScriere ms) {
		this.ms = ms;
	}
	public Imprimanta(String denumire, int nr_inv, float pret, String zona_mag,
			Stare s, int ppm, String rezolutie, int p_car, ModScriere ms) {
		super(denumire, nr_inv, pret, zona_mag, s);
		this.ppm = ppm;
		this.rezolutie = rezolutie;
		this.p_car = p_car;
		this.ms = ms;
	}
	@Override
	public String toString() {
		return "Imprimanta ppm=" + ppm + ", rezolutie=" + rezolutie
				+ ", p_car=" + p_car + ", ms=" + ms + ", Baza="
				+ super.toString();
	}
}

class Copiator extends Echipament{
	private int p_ton;
	private FormatCopiere fc;
	
	public Copiator(String denumire, int nr_inv, float pret, String zona_mag,
			Stare s, int p_ton, FormatCopiere fc) {
		super(denumire, nr_inv, pret, zona_mag, s);
		this.p_ton = p_ton;
		this.fc = fc;
	}
	@Override
	public String toString() {
		return "Copiator p_ton=" + p_ton + ", fc=" + fc + ", Baza="
				+ super.toString();
	}

}

class SistemCalcul extends Echipament{
	private String tip_mon;
	private double vit_proc;
	private int  c_hdd;
	private OS sistem;
	public SistemCalcul(String denumire, int nr_inv, float pret,
			String zona_mag, Stare s, String tip_mon, double vit_proc,
			int c_hdd, OS sistem) {
		super(denumire, nr_inv, pret, zona_mag, s);
		this.tip_mon = tip_mon;
		this.vit_proc = vit_proc;
		this.c_hdd = c_hdd;
		this.sistem = sistem;
	}
	@Override
	public String toString() {
		return "SistemCalcul tip_mon=" + tip_mon + ", vit_proc=" + vit_proc
				+ ", c_hdd=" + c_hdd + ", sistem=" + sistem + ", Baza="
				+ super.toString();
	}
}

//enum pentru echipament

enum Stare{
	achizitionat,
	expus,
	vandut
}

//enum pentru imprimanta
enum ModScriere{
	color,
	AlbNegru
}

//enum pentru copiator
enum FormatCopiere{
	A4,
	A3
}

//enum pentru sistemele de operare
enum OS{
	windows,
	linux
}
	
	

public class MainApp {

	public static void main(String[] args) throws IOException {
		//reading from file
		String file = "echipamente.txt";
		BufferedReader flux_in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		String line = null;
		String[] cleanLine = null;
		int index = 0;
		//array for each subclass
		List<Echipament> echipamente = new ArrayList<Echipament>();
		Echipament e = null;
		while((line = flux_in.readLine())!=null)
		{
			cleanLine = line.split(";");
			if(cleanLine[5].equals("imprimanta"))
			{
				e = new Imprimanta(cleanLine[0],Integer.parseInt(cleanLine[1]),Float.parseFloat(cleanLine[2]), 
						cleanLine[3],Stare.valueOf(cleanLine[4]), Integer.parseInt(cleanLine[6]),cleanLine[7], 
						Integer.parseInt(cleanLine[8]), ModScriere.valueOf(cleanLine[9]));
			}
			if(cleanLine[5].equals("copiator"))
			{
				e = new Copiator(cleanLine[0],Integer.parseInt(cleanLine[1]),Float.parseFloat(cleanLine[2]), 
						cleanLine[3],Stare.valueOf(cleanLine[4]),Integer.parseInt(cleanLine[6]),FormatCopiere.valueOf(cleanLine[8]));
			}
			if(cleanLine[5].equals("sistem de calcul"))
			{
				e = new SistemCalcul(cleanLine[0],Integer.parseInt(cleanLine[1]),Float.parseFloat(cleanLine[2]), 
						cleanLine[3],Stare.valueOf(cleanLine[4]),cleanLine[6], Double.parseDouble(cleanLine[7]),
						Integer.parseInt(cleanLine[8]), OS.valueOf(cleanLine[9]));
			}
			echipamente.add(e);
		}
		
		
		
		//afisare filtrata
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int opt;
		do
		{
			System.out.println();
			System.out.println("1.Afisare imprimante.");
			System.out.println("2.Afisare copiatoare.");
			System.out.println("3.Afisare sisteme de calcul.");
			System.out.println("4.Modificati starea unui echipament.");
			System.out.println("5.Afisarea echipamentelor vandute.");
			System.out.println("6.Setarea unui anumit mod de scriere pentru imprimanta.");
			System.out.println("Dati optiunea.");
			opt = Integer.parseInt(read.readLine());
			switch(opt)
			{
			case 1:
				for(Echipament ech:echipamente)
				{
					if(ech.getClass()  == Imprimanta.class)
					{
						System.out.println(ech.toString());
					}
				}
				break;
			case 2:
				for(Echipament ech:echipamente)
				{
					if(ech.getClass()  == Copiator.class)
					{
						System.out.println(ech.toString());
					}
				}
				break;
			case 3:
				for(Echipament ech:echipamente)
				{
					if(ech.getClass()  == SistemCalcul.class)
					{
						System.out.println(ech.toString());
					}
				}
				break;
			case 4:
				boolean done=false;
				do {
					System.out.println("Dati numarul inventarului al echipamentului pe care il modificati.");
					String nr_inventar = read.readLine();
					try {
						VerificaInventar.Verificainv(nr_inventar);
						int nrinv= Integer.parseInt(nr_inventar);
						System.out.println("Dati starea noua?");
						Stare stare = Stare.valueOf(read.readLine());
			
						for(Echipament ech:echipamente)
						{
							if(ech.getNr_inv() == nrinv)
							{
								ech.setS(stare);
							}
						}
					}
					catch(NotNumeric n)
					{
						System.out.println("Eroare:" + n + "!");
					}
				}while(done);
				break;
			case 5:
				for(Echipament ech:echipamente)
				{
					if(ech.getS() == Stare.vandut)
					{
						System.out.println(ech.toString());
					}
				}
				break;
			case 6:
				System.out.println("Dati numarul inventarului al imprimantei pe care il modificati.");
				int nr_inventar1 = Integer.parseInt(read.readLine());
				System.out.println("Dati modul de scriere noua?");
				ModScriere scriere = ModScriere.valueOf(read.readLine());
				
				for(Echipament ech:echipamente)
				{
					if(nr_inventar1 == ech.getNr_inv())
					{
						if(ech.getClass() != Imprimanta.class)
						{
							System.out.println("Echipamentul nu este o imprimanta.");
						}
						else
						{
							((Imprimanta) ech).setMs(scriere);
						}
					}
			
				}
				break;
				default:System.out.println("Optiune invalida.");
			}
		}while(opt>0 && opt<=6);
	}

}
