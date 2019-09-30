

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



class Destinatie
{
	String denumire;
	Calendar data_vizita;
	public Destinatie(String denumire,Calendar data_vizita) {
		super();
		this.denumire = denumire;
		this.data_vizita = data_vizita;
	}
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public Calendar getData_vizita() {
		return data_vizita;
	}
	public void setData_vizita(Calendar data_vizita) {
		this.data_vizita = data_vizita;
	}
	public String getData()
	{
		return data_vizita.get(Calendar.DAY_OF_MONTH) + "/" + data_vizita.get(Calendar.MONTH) +"/" + data_vizita.get(Calendar.YEAR);
	}
}

class ComparaDest implements Comparator<Destinatie>
{

	@Override
	public int compare(Destinatie d1, Destinatie d2) {
		return d1.getDenumire().compareToIgnoreCase(d2.denumire);
	}

}
/**
 * Servlet implementation class Turism
 */
@WebServlet("/Turism")
public class Turism extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ArrayList<Destinatie> destinatii=new ArrayList<Destinatie>();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Turism() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		if(request.getParameter("btnAdaugare")!=null)
		{
			String destinatie=request.getParameter("denumire");
			String zi=request.getParameter("zi");
			String luna=request.getParameter("luna");
			String an=request.getParameter("an");

			Calendar data= Calendar.getInstance();

			data.set(Integer.parseInt(an),Integer.parseInt(luna), Integer.parseInt(zi));
			destinatii.add(new Destinatie(destinatie,data));



			out.println("<html><head></head><body>");
			out.println("<table border='2' align='center'><tr><th>Denumire</th><th>Data</th></tr>");
			for(Destinatie d : destinatii)
			{
				out.println("<tr><td>"+ d.getDenumire()+"</td><td>"+ d.getData()+"</td></tr>");		
			}
			out.println("</table>");
			out.println("<form method='get' action='Turism'>");
			out.println("<input type='submit' name='btnSort' value='Sorteaza'>");
			out.println("</form>");
		}

		if(request.getParameter("btnSort") != null)
		{
			out.println("<table border='2' align='center'><tr><th>Denumire</th><th>Data</th></tr>");
			System.out.println("Buton de sortare-------------------------!");
			Collections.sort(destinatii,new ComparaDest());
			for(Destinatie d : destinatii)
			{
				System.out.println(d.getDenumire());
				out.println("<tr><td>"+ d.getDenumire() +"</td><td>" + d.getData()+"</td></tr>");		
			}
			out.println("</table>");
		}
		out.println("</body></html>");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{
			doGet(request,response);
		}
	}
}
