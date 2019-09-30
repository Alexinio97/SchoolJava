

public class NotNumeric extends Exception {
	
	private String nr_inv;

	public NotNumeric(String nr_inv) {
		this.nr_inv = nr_inv;
	}
	
	public String toString()
	{
		return "Nu este un numar!";
	}
	
}
