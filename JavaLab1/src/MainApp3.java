import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApp3 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader ( new InputStreamReader(System.in));
		String input;
		System.out.println("Give a number: ");
		input = reader.readLine();
		
		int number = Integer.parseInt(input);
		System.out.println("Divizorii sunt: ");
		for(int i = 1 ; i <= number;i++)
		{
			if(number%i == 0)
				System.out.println(i);
		}
		for ( int i =2 ; i <= number /2  ; i++)
		{
			if(number%i != 0)
				{
					System.out.println("Numarul " + number + " este prim.");
					break;
				}
		}
	}

}
