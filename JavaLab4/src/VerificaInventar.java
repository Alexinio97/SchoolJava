
public class VerificaInventar {
	public static void Verificainv(String nr_inv) throws NotNumeric
	{
		String numere="123456789";
		for(int i=0;i < nr_inv.length();i++)
		{
			if(!(numere.contains(String.valueOf(nr_inv.charAt(i)))))
			{
				throw new NotNumeric(nr_inv);
			}
		}
	}
}
