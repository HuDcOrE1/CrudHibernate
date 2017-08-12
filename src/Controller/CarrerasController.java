package Controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		response.getWriter().println("HOLA MUNDO");
	}
}
