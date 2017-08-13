package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Model.Carreras;
import hibernate.HibernateUtil;

@WebServlet("/CarrerasController")

public class CarrerasController extends HttpServlet{
	private SessionFactory factory;
	private Session session;
	
	public CarrerasController(){
		super();
		factory = HibernateUtil.getSessionFactory();
		session = factory.openSession();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Carreras> carreras = session.createCriteria(Carreras.class).list();
		
		PrintWriter out = response.getWriter();
		
		out.println("<table class='table table-hover table-inverse'>"
				+ "<tr>"
				+ "<td>id</td>"
				+ "<td>Nombre</td>"
				+ "<td>Creditos</td>"
				+ "</tr>");
		for (Carreras carr:carreras) {
			out.println("<tr>"
					+ "<td>"+carr.getId()+"</td>"
					+ "<td>"+carr.getNombre()+"</td>"
					+ "<td>"+carr.getCreditos()+"</td>"
					+ "</tr>");
		}
		out.print("</table>");
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String operacion = request.getParameter("operacion");
		switch (operacion) {
		case "insertar":
			insertar(request,response);
			break;

		default:
			break;
		}
	}
	
	public void insertar(HttpServletRequest request,HttpServletResponse response){
		String nombre = request.getParameter("nombre");
		int creditos = Integer.parseInt(request.getParameter("creditos"));
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			tx.begin();
			Carreras carr = new Carreras();
			carr.setCreditos(creditos);
			carr.setNombre(nombre);
			session.save(carr);
			tx.commit();
			
		}catch(Exception e){
			tx.rollback();
		}
		
	}
}
