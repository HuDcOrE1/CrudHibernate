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
				+ "<td>Eliminar</td>"
				+ "<td>Editar</td>"
				+ "</tr>");
		for (Carreras carr:carreras) {
			out.println("<tr>"
					+ "<td>"+carr.getId()+"</td>"
					+ "<td>"+carr.getNombre()+"</td>"
					+ "<td>"+carr.getCreditos()+"</td>"
					+ "<td><a onclick='eliminar("+carr.getId()+")' >Eliminar</a></td>"
					+ "<td><a onclick='editar("+carr.getId()+")' >Editar</a></td>"
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
		case "eliminar":
			eliminar(request,response,Integer.parseInt(request.getParameter("id")));
			break;
		case "formulario":
			editar(request,response,Integer.parseInt(request.getParameter("id")));
			break;
		case "editar":
			editar2(request,response,Integer.parseInt(request.getParameter("id")));
			break;

		default:
			break;
		}
	}
	
	public void eliminar(HttpServletRequest request,HttpServletResponse response, int idCarrera){
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			tx.begin();
			Carreras carr = session.get(Carreras.class, idCarrera);
			session.delete(carr);
			tx.commit();
			
		}catch(Exception e){
			tx.rollback();
		}
	}
	public void editar2(HttpServletRequest request,HttpServletResponse response, int idCarrera){
		String nombre = request.getParameter("nombre");
		int creditos = Integer.parseInt(request.getParameter("creditos"));
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			tx.begin();
			Carreras car = session.get(Carreras.class, idCarrera);
			car.setCreditos(creditos);
			car.setNombre(nombre);
			session.update(car);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}
	}
	public void editar(HttpServletRequest request,HttpServletResponse response, int idCarrera){
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			tx.begin();
			Carreras car = session.get(Carreras.class, idCarrera);
			tx.commit();
		
			PrintWriter out = response.getWriter();
			
			out.println("<form>");
				out.println("<input type='hidden' id='idCarrera' value='"+car.getId()+"'>");
				out.println("<input type='text' class='form-control' id='nombreCarrera' value='"+car.getNombre()+"'>");
				out.println("<input type='text' class='form-control' id='creditosCarrera' value='"+car.getCreditos()+"'>");
				out.println("<button class='btn btn-primary' onclick='return editar2()'>Editar</button>");
			out.println("<form>");
			
		}catch(Exception e){
			tx.rollback();
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
