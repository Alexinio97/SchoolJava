import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.omg.CORBA.SystemException;

public class MainApp2 {
	public static void main(String[] args) throws IOException {
	String file = "in.txt"; //always use "file.txt" for text files
	BufferedReader file_in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	//declaring variables 
	String line = null;
	float number;
	int index = 0;
	float media_arm = 0;
	float sum = 0;
	float max = 0;
	float min;
	
	line = file_in.readLine();
	min =Float.parseFloat(line);
	//storing each line in the file in the string line
	while((line = file_in.readLine()) != null)
		{
			number = Float.parseFloat(line);
			if(number < min)
				min = number;
			if( number > max)
				max = number;
			sum += number;
			index++;
		}
	media_arm = sum / index;
	
	System.out.println("Minimul este: " + min);
	System.out.println("Suma lor este egala: " + sum);
	System.out.println("Media aritmetica: " + media_arm);
	System.out.println("Maximul din lista e: " + max);
	
	PrintWriter writer = new PrintWriter("out.txt","UTF-8");
	writer.println("Minimum is:" + min);
	writer.println("The sum is: " +sum);
	writer.println("Media aritmetica: " +media_arm);
	writer.println("Maximum: " +max);
	writer.close();
	}
	
}
