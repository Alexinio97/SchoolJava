

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//clasa persoane
class Persoana
{
	String nume;
	String[] data_nasterii;
	String adresa;
	String telefon;

	public Persoana(String nume, String[] data_nasterii, String adresa,
			String telefon) {
		super();
		this.nume = nume;
		this.data_nasterii = data_nasterii;
		this.adresa = adresa;
		this.telefon = telefon;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}



	public String getData_nasterii() {
		return data_nasterii[0]+"-"+data_nasterii[1]+"-"+data_nasterii[2];
	}
	//get function to get only month
	public String getMonth()
	{
		return data_nasterii[1];
	}

	public void setData_nasterii(String[] data_nasterii) {
		this.data_nasterii = data_nasterii;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Override
	public String toString() {
		return "Persoana [nume=" + nume + ", data_nasterii=" + data_nasterii[0]+"-"+ data_nasterii[1]+"-"+
				data_nasterii[2]+"-"+
				", adresa=" + adresa + ", telefon=" + telefon + "]";
	}


}
/**
 * Servlet implementation class Persoane
 */
@WebServlet("/Persoane")
public class Persoane extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArrayList<Persoana> persoane=new ArrayList<Persoana>();
	public Persoane() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nume=request.getParameter("nume");
		String zi=request.getParameter("zi");
		String luna=request.getParameter("luna");
		String an=request.getParameter("an");
		String adresa=request.getParameter("adresa");
		String telefon= request.getParameter("tel");
		//adding our three components to birthday
		String[] data_nasterii=new String[3];
		data_nasterii[0]=zi;
		data_nasterii[1]=luna;
		data_nasterii[2]=an;

		persoane.add(new Persoana(nume,data_nasterii,adresa,telefon));
		PrintWriter out = response.getWriter();
		//creating table for introduced data
		out.println("<html><head><title>Rezultatul</title></head><body>");
		out.println("<h1 align='center'>Perosane introduse</h1>");
		out.println("<form method='POST' action='Persoane'>");
		out.println("<select name='month'>");
		out.println("<option value='1'>Ianuarie</option>");
		out.println("<option value='2'>Februarie</option>");
		out.println("<option value='3'>Martie</option>");
		out.println("<option value='4'>Aprilie</option>");
		out.println("<option value='5'>Mai</option>");
		out.println("<option value='6'>Iunie</option></select>");
		out.println("<input type='submit' name='btnFiltreaza' value='Filtreaza' >");
		out.println("</form>");
		if(request.getParameter("btnFiltreaza") != null)
		{
			out.println("<table border='2' bgcolor='silver' align='center'><tr><th>Nume si prenume</th><th>Data Nasterii</th>");
			out.println("<th>Adresa</th><th>Telefon</th></tr>");

			String selected_month=request.getParameter("month");

			System.out.println("buton apasat.");
			for(Persoana p:persoane)
			{
				if(p.getMonth().equals(selected_month))
				{
					out.println("<tr><td>"+p.getNume()+"</td>"+"<td>"+p.getData_nasterii()+"</td>" +
							"<td>"+p.getAdresa()+"</td>" +"<td>"+ p.getTelefon()+"</td></tr>");
				}
			}
		}
		else
		{
			out.println("<table border='2' bgcolor='silver' align='center'><tr><th>Nume si prenume</th><th>Data Nasterii</th>");
			out.println("<th>Adresa</th><th>Telefon</th></tr>");
			for(Persoana p:persoane)
			{
				//System.out.println("Foreach loop");
				out.println("<tr><td>"+p.getNume()+"</td>"+"<td>"+p.getData_nasterii()+"</td>" +
						"<td>"+p.getAdresa()+"</td>" +"<td>"+ p.getTelefon()+"</td></tr>");
			}
		}
		out.println("</table>");
	
		out.println("</body></html>");
	}

}
