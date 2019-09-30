import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Compara extends Exception{
	
	public static void compara(int number1,int number2) throws Compara
	{
		if(number1<number2)
			System.out.println("Succes");
		else
			throw new Compara();
	}
	public String toString()
	{
		return "Primul numar e mai mare ca primul";
	}
}

public class Exceptii {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader tastatura = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Number1:");
		int number1 = Integer.parseInt(tastatura.readLine());
		System.out.println("Number2:");
		int number2 = Integer.parseInt(tastatura.readLine());
		try {
			Compara.compara(number1,number2);
		}
		catch(Compara cmp)
		{
			System.out.println("Eroare:" + cmp);
		}
		//program to divide by 0
		
		boolean done = true;
		do {
			System.out.println("Number1:");
			float number12 = Integer.parseInt(tastatura.readLine());
			System.out.println("Number2:");
			float number21 = Integer.parseInt(tastatura.readLine());
			if(number21==0)
			{
				System.out.println("Tried to divide by 0,try again");
			}				
			else
			{
				System.out.println("Result"+number12/number21);
				done=false;	
			}
		}while(done);
	}
}
