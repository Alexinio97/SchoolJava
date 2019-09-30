import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


interface Operatiuni{
	public float calculeaza_dobanda();
	public float actualizare_suma();
	public void depunere(float suma);
	public void extragere(float suma);
}

class ContBancar implements Operatiuni{
	private String numarCont;
	private float suma;
	private String moneda;
	private Calendar data_deschiderii;
	private Calendar data_ultimei_operatiuni;
	//Constructor
	public ContBancar(String numarCont, float suma, String moneda,
			Calendar data_deschiderii, Calendar data_ultimei_operatiuni) {
		this.numarCont = numarCont;
		this.suma = suma;
		this.moneda = moneda;
		this.data_deschiderii = data_deschiderii;
		this.data_ultimei_operatiuni = data_ultimei_operatiuni;
	}
	
	//getters and setters
	public String getNumarCont() {
		return numarCont;
	}
	public void setNumarCont(String numarCont) {
		this.numarCont = numarCont;
	}
	public float getSuma() {
		return suma;
	}
	public void setSuma(float suma) {
		this.suma = suma;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Calendar getData_deschiderii() {
		return data_deschiderii;
	}
	public void setData_deschiderii(Calendar data_deschiderii) {
		this.data_deschiderii = data_deschiderii;
	}
	public Calendar getData_ultimei_operatiuni() {
		return data_ultimei_operatiuni;
	}
	public void setData_ultimei_operatiuni(Calendar data_ultimei_operatiuni) {
		this.data_ultimei_operatiuni = data_ultimei_operatiuni;
	}
	
	//toString method
	@Override
	public String toString() {
		return "ContBancar numarCont=" + numarCont + ", suma=" + suma
				+ ", moneda=" + moneda + ", data_deschiderii="
				+ data_deschiderii.get(Calendar.DAY_OF_MONTH) +"/"+ data_deschiderii.get(Calendar.MONTH ) +"/"
				+ data_deschiderii.get(Calendar.YEAR)
				+ ", data_ultimei_operatiuni="
				+ data_ultimei_operatiuni.get(Calendar.DAY_OF_MONTH) +"/"+ data_ultimei_operatiuni.get(Calendar.MONTH)+"/"
				+ data_ultimei_operatiuni.get(Calendar.YEAR);
	}

	@Override
	public float calculeaza_dobanda() {
		Calendar today = Calendar.getInstance();
		float  milis = today.getTimeInMillis() - data_ultimei_operatiuni.getTimeInMillis();
		float zile = milis/(24*60*60*1000);
		float dobanda = 0;
		if(moneda.equalsIgnoreCase("RON"))
		{
			if(suma < 500)
			
			{
				dobanda = (float) (zile* 0.3);
			}
			else
			{
				dobanda = (float) (zile*0.8);
			}
		}
		if(moneda.equalsIgnoreCase("EURO"))
		{
			dobanda=(float)(zile*0.1);
		}
		return dobanda;
	}

	@Override
	public float actualizare_suma() {
		this.suma += calculeaza_dobanda();
		//get todays date
		data_ultimei_operatiuni = Calendar.getInstance();
		return suma;
	}

	@Override
	public void depunere(float suma) {
		this.suma +=suma;
	}

	@Override
	public void extragere(float suma) {
		if(this.suma<0)
		{
			System.out.println("Fonduri insuficiente");
		}
		else
		{
			this.suma -= suma;
		}
		
	}
}

class Client{
	private String name;
	private String adresa;
	private ArrayList<ContBancar> conturi;
	
	//constructor
	public Client(String name, String adresa, ArrayList<ContBancar> conturi) {
		this.name = name;
		this.adresa = adresa;
		this.conturi = conturi;
	}
	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public ArrayList<ContBancar> getConturi() {
		return conturi;
	}

	public void setConturi(ArrayList<ContBancar> conturi) {
		this.conturi = conturi;
	}

	
	//toString method
	@Override
	public String toString() {
		return "Client [name=" + name + ", adresa=" + adresa;
	}

}


class Banca{
	private String denumire_banca;
	private ArrayList<Client> clienti;
	
	//constructor
	public Banca(String denumire_banca, ArrayList<Client> clienti) {
		this.denumire_banca = denumire_banca;
		this.clienti = clienti;
	}
	//getters and setters
	public String getDenumire_banca() {
		return denumire_banca;
	}
	public void setDenumire_banca(String denumire_banca) {
		this.denumire_banca = denumire_banca;
	}
	
	public void setClienti(ArrayList<Client> clienti) {
		this.clienti = clienti;
	}
	
	public ArrayList<Client> getClienti() {
		return clienti;
	}

	//toString Method
	@Override
	public String toString() {
		return "Banca [denumire_banca=" + denumire_banca;
	}

}


public class MainApp {

	@SuppressWarnings("null")
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
		int opt;
		//tastatura
		BufferedReader tastatura =new BufferedReader(new InputStreamReader(System.in));
		
		//fisier
		String file_in = "Clienti.txt";
		BufferedReader flux_in = new BufferedReader(new InputStreamReader(new FileInputStream(file_in)));
		String line = null;
		//oArray list of objects
		ArrayList<Client> clienti = new ArrayList<Client>();
		ArrayList<Banca> banci = new ArrayList<Banca>();
		ArrayList<ContBancar> conturi =new ArrayList<ContBancar>();
		
		//add objects
		while((line = flux_in.readLine())!= null)
		{
			String[] cleanLine = line.split(";");
			//splitting the dates from cleanline[6]
			String[] partsData = cleanLine[6].split("/");
			//storing dates
			Calendar data = Calendar.getInstance();
			data.set(Integer.parseInt(partsData[2]), Integer.parseInt(partsData[1]), Integer.parseInt(partsData[0]));
			
			String[] partsData1 = cleanLine[7].split("/");
			Calendar data1 = Calendar.getInstance();
			data1.set(Integer.parseInt(partsData1[2]), Integer.parseInt(partsData1[1]), Integer.parseInt(partsData1[0]));
			
			//adding cont,clients to arraylist after instantianting each one
			ContBancar cont = new ContBancar(cleanLine[3], Float.parseFloat(cleanLine[4]), cleanLine[5],data, data1);
			conturi.add(cont);
			Client client = new Client(cleanLine[1], cleanLine[2],conturi);
			clienti.add(client);
			Banca banca = new Banca(cleanLine[0], clienti);
			banci.add(banca);
		}
		do
		{
			System.out.println("1.Afisare date introduse.");
			System.out.println("2.Depunerea unei sume intr-un cont.");
			System.out.println("3.Extragerea unei sume.");
			System.out.println("4.Transfer de bani intre 2 conturi.");
			System.out.println("5.Calculeaza dobanda.");
			System.out.println("Dati optiunea:(0 pentru exit)");
			opt = Integer.parseInt(tastatura.readLine());
			switch(opt)
			{
			case 1:
				for(Banca b : banci)
					System.out.println(b.toString());
				for(Client c :clienti)
					System.out.println(c.toString());
				for(ContBancar cb : conturi)
							System.out.println(cb.toString());
				break;
			case 2:
				System.out.println("Dati numarul contului");
				String numar = tastatura.readLine();
				System.out.println("Dati suma depusa");
				float suma = Float.parseFloat(tastatura.readLine());
				for(ContBancar cont:conturi)
				{
					if(cont.getNumarCont().equalsIgnoreCase(numar))
					{
						cont.depunere(suma);
					}
				}
				break;
			case 3:
				System.out.println("Dati numarul contului");
				String numarC = tastatura.readLine();
				System.out.println("Dati suma depusa");
				float suma_extrasa = Float.parseFloat(tastatura.readLine());
				for(ContBancar cont:conturi)
				{
					if(cont.getNumarCont().equalsIgnoreCase(numarC))
					{
						cont.extragere(suma_extrasa);
					}
				}
				break;
			case 4:
				System.out.println("Dati numarul contului sursa.");
				String numar1 = tastatura.readLine();
				System.out.println("Dati suma depusa");
				float suma_trimisa = Float.parseFloat(tastatura.readLine());
				System.out.println("Dati numarul contului destinatie");
				String numar2 = tastatura.readLine();
				
				for(ContBancar cont:conturi)
				{
					if(cont.getNumarCont().equalsIgnoreCase(numar1))
					{
						cont.extragere(suma_trimisa);
						for(ContBancar cont2:conturi)
						{
							if(cont2.getNumarCont().equalsIgnoreCase(numar2))
							{
								cont2.depunere(suma_trimisa);
							}
						}
					}
					
				}
				break;
			case 5:
				System.out.println("Dati numarul contului pe care vreti sa il actualizati");
				String contA = tastatura.readLine();
				for(ContBancar cont:conturi)
				{
					if(cont.getNumarCont().equalsIgnoreCase(contA))
					{
						cont.actualizare_suma();
					}
				}
			break;
		}
	}while(opt > 0 && opt<6);
	}
}
