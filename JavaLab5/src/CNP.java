import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

class Persoana{
	private String name;
	
	private String CNP;
	
	public Persoana(String name, String CNP) {
		this.name = name;
		this.CNP = CNP;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCNP() {
		return CNP;
	}
	public void setCNP(String CNP) {
		this.CNP = CNP;
	}
	
	@Override
	public String toString() {
		return "Persoana [name=" + name + ", CNP=" + CNP;
	}
	
	public String CalculeazaAnNastereVarsta()
	{
		int varsta; 
		String[] data = new String[3];
		data[0] = "19"+CNP.substring(1, 3);
		data[1] = CNP.substring(3, 5);
		data[2] = CNP.substring(5, 7);
		int year= Calendar.getInstance().get(Calendar.YEAR);
		varsta = year - Integer.parseInt(data[0]);
		return "Data nastere:" + data[2] + "/" + data[1] +"/" + data[0] + " | Varsta:"+ varsta;
	}
	
	
}

class Numeric extends Exception{
	private String cnp;
	
	public Numeric(String cnp)
	{
		this.cnp=cnp;
	}
	
	public String toString()
	{
		return "Nu este un numar valid.";
	}
}

class VerificaCnp
{
	public static void verificaCnp(String cnp) throws Numeric
	{
		String numere = "0123456789";
		for (int i = 0; i < cnp.length(); i++)
		{
			if (!(numere.contains(String.valueOf(cnp.charAt(i)))))
			{
				throw new Numeric(cnp);
			}
		}
	}
}

public class CNP {

	public static void main(String[] args) throws IOException {
		BufferedReader tastatura = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Persoana> persoane = new ArrayList<Persoana>();
		int optiune;
		do {
			System.out.println("1.Adaugare");
			System.out.println("2.Afisare");
			System.out.println("3.Calculeaza data nastere.");
			optiune = Integer.parseInt(tastatura.readLine());
			switch(optiune)
			{
			case 1:
				try{
					System.out.println("Dati nume");
					String nume = tastatura.readLine();
					System.out.println("Dati CNP");
					String cnp = tastatura.readLine();
					VerificaCnp.verificaCnp(cnp);
					Persoana p = new Persoana(nume,cnp);
					persoane.add(p);
				}
				catch(Numeric n){
					System.out.println("Eroare:" + n);
				}
				break;
			case 2:
				for(Persoana p:persoane)
				{
					System.out.println(p.toString());
				}
				break;
			case 3:
				for(Persoana p:persoane)
				{
					System.out.println(p.CalculeazaAnNastereVarsta());
				}
				break;
			}
		}while(optiune!=0);
	}

}
