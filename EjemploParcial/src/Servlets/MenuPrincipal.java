package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.usal.negocio.dao.factory.ClienteFactory;
import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dominio.Cliente;

public class MenuPrincipal extends HttpServlet{


	private static final long serialVersionUID = 1L;
	private static edu.usal.negocio.dominio.Cliente c1;
	private static String botCli;
	private static int dniMod;
	
	 public MenuPrincipal() {
	        super();
	      
	    }

		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			botCli =request.getParameter("botCliente");
				
	if(botCli.equals("Ingresar")) {
					try {altaCliente(request, response);
					} catch (SQLException e) {e.printStackTrace();}
					
	}else{if (botCli.equals("Eliminar")){
					try {bajaCliente(request, response);
					} catch (SQLException e) {e.printStackTrace();}
					
	}else{if (botCli.equals("Modificar")){
					try {modificarCliente(request, response);
					} catch (NumberFormatException e) {e.printStackTrace();
					} catch (SQLException e) {e.printStackTrace();}
					
	}else{if(botCli.equals("Ver Listado Completo")) {
					try {consultarCliente1(request, response);
					} catch (SQLException e) {e.printStackTrace();}
				
	}else{if(botCli.equals("Buscar")) {
					try {consultarCliente2(request, response);
					} catch (SQLException e) {e.printStackTrace();}
					
	}else {if(botCli.equals( "Editar")) {
					try {editarCliente(request, response);
					} catch (SQLException e) {e.printStackTrace();}
					
				}}}}}}}
			
		
		
		
		private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException,  IOException, ServletException {
		
		
			
			edu.usal.negocio.dominio.Cliente cl= new edu.usal.negocio.dominio.Cliente();
			cl.setID(Integer.parseInt(request.getParameter("id1")));
			cl.setSaldo(Float.parseFloat(request.getParameter("saldo1")));
			cl.setCredito(Integer.parseInt(request.getParameter("credito1")));
			cl.setDescuento(request.getParameter("descuento1"));
			cl.setDni(dniMod);
			cl.setDireccion(request.getParameter("direccion1"));
			ClienteDAO ca= ClienteFactory.getClienteDAO("SQL");
			
			if(ca.updateCliente(cl)) {
				
				RequestDispatcher ds=request.getRequestDispatcher("menuClientes.html");
				ds.forward(request, response);	
			}else {
				PrintWriter out2=response.getWriter();
				out2.println("<html>");
				out2.println("<head></head>");
				out2.println("<body BGCOLOR=#AABBCC>");
				out2.println("<a href=\"menuClientes.html\">< Volver</a>");
				out2.println("<CENTER>");
				out2.println("ERROR AL EDITAR CLIENTE.");
				out2.println("</CENTER>");
				out2.println("</body>");
				out2.println("</html>");
			}
			
		
		}


		protected void altaCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	
				edu.usal.negocio.dominio.Cliente c=new edu.usal.negocio.dominio.Cliente();
			c.setID(Integer.parseInt(request.getParameter("id")));
			c.setSaldo(Float.parseFloat(request.getParameter("saldo")));
			c.setCredito(Integer.parseInt(request.getParameter("credito")));
			c.setDescuento(request.getParameter("descuento"));
			c.setDni(Integer.parseInt(request.getParameter("dni")));
			c.setDireccion(request.getParameter("direccion"));
			
			
			ClienteDAO ca= ClienteFactory.getClienteDAO("SQL");
			if(ca.addCliente(c)==true) {
			
			RequestDispatcher ds=request.getRequestDispatcher("menuClientes.html");
			ds.forward(request, response);
			
			}else {
				PrintWriter out=response.getWriter();
				out.println("<html>");
				out.println("<head></head>");
				out.println("<body BGCOLOR=#AABBCC>");
				out.println("<a href=\"menuClientes.html\">< Volver</a>");
				out.println("<CENTER>");
				out.println("ERROR AL AGREGAR CLIENTE.");
				out.println("</CENTER>");
				out.println("</body>");
				out.println("</html>");
			}
			}
				
		protected void bajaCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			edu.usal.negocio.dominio.Cliente c=new edu.usal.negocio.dominio.Cliente();
			c.setDni(Integer.parseInt(request.getParameter("dni")));
			ClienteDAO cli=ClienteFactory.getClienteDAO("SQL");
			if(cli.deleteCliente(c)==true) {
				
				RequestDispatcher ds=request.getRequestDispatcher("menuClientes.html");
				ds.forward(request, response);
				
				}else {
					PrintWriter out=response.getWriter();
					out.println("<html>");
					out.println("<head></head>");
					out.println("<body BGCOLOR=#AABBCC>");
					out.println("<a href=\"menuClientes.html\">< Volver</a>");
					out.println("<CENTER>");
					out.println("ERROR AL ELIMINAR CLIENTE.");
					out.println("</CENTER>");
					out.println("</body>");
					out.println("</html>");
				}
		
		}
		
		protected void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, IOException {
			ClienteDAO cli=ClienteFactory.getClienteDAO("SQL");
			edu.usal.negocio.dominio.Cliente c2=cli.readCliente(Integer.parseInt(request.getParameter("dni")));
			
			if(c2==null) {
				PrintWriter out2=response.getWriter();
				out2.println("<html>");
				out2.println("<head></head>");
				out2.println("<body BGCOLOR=#AABBCC>");
				out2.println("<a href=\"menuClientes.html\">< Volver</a>");
				out2.println("<CENTER>");
				out2.println("CLIENTE NO ENCONTRADO.");
				out2.println("</CENTER>");
				out2.println("</body>");
				out2.println("</html>");
			}else {
				PrintWriter out=response.getWriter();
				out.println("<html>");
				out.println("<head></head>");
				out.println("<body BGCOLOR=#AABBCC>");
				out.println("<a href=\"menuClientes.html\">< Volver</a>");
				out.println("<CENTER>");
				out.println("<h1>Modifique los campos que desea: </h1>");
				out.println("<form action=\"MenuPrincipal\" method=\"post\">");
				out.println("<br><h2>ID: </h2>");
				out.println("<input type=\"number\" name=\"id1\" value=\""+c2.getID()+"\"");
				out.println("<br>");
				out.println("<br><h2>Saldo: </h2>");
				out.println("<input type=\"number\" name=\"saldo1\" value=\""+c2.getSaldo()+"\"");
				out.println("<br>");
				out.println("<br><h2>Credito: </h2>");
				out.println("<input type=\"number\" name=\"credito1\" value=\""+c2.getCredito()+"\"");
				out.println("<br>");
				out.println("<br><h2>Descuento: </h2>");
				out.println("<input type=\"text\" name=\"descuento1\" value=\""+c2.getDescuento()+"\"");
				out.println("<br>");
				out.println("<br><h2>DNI: </h2>");
				out.println("<input type=\"text\" name=\"dni1\" disabled = \\\"disabled\\\" value=\""+c2.getDni()+"\"");
				out.println("<br>");
				dniMod=c2.getDni();
				out.println("<br><h2>Direccion: </h2>");
				out.println("<input type=\"text\" name=\"direccion1\" value=\""+c2.getDireccion()+"\"");
				out.println("<br>");
				out.println("<br><input type=\"submit\" name=\"botCliente\" value=\"Editar\">");
				out.println("<br>");
				out.println("<br>---------------------***---------------------");
				out.println("</CENTER>");
				out.println("</body>");
				out.println("</html>");
				
			}
	
		}
		
		protected void consultarCliente1(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			PrintWriter out=response.getWriter();
			ClienteDAO cli=ClienteFactory.getClienteDAO("SQL");
			List<edu.usal.negocio.dominio.Cliente> C=cli.getAllCliente();
			if(C.isEmpty()) {
				PrintWriter out2=response.getWriter();
				out2.println("<html>");
				out2.println("<head></head>");
				out2.println("<body BGCOLOR=#AABBCC>");
				out2.println("<a href=\"conCliente.html\">< Volver</a>");
				out2.println("<CENTER>");
				out2.println("NO SE ENCONTRARON CLIENTES.");
				out2.println("</CENTER>");
				out2.println("</body>");
				out2.println("</html>");
			}else {
			
			out.println("<html>");
			out.println("<head></head>");
			out.println("<body BGCOLOR=#AABBCC>");
			out.println("<a href=\"conCliente.html\">< Volver</a>");
			out.println("<CENTER>");
			out.println("<h1>Lista de Clientes</h1>");
			for(edu.usal.negocio.dominio.Cliente a: C) {
				out.println("<br>ID: "+a.getID()+"<br>");
				out.println("<br>Saldo:"+a.getSaldo()+"<br>");
				out.println("Credito:"+a.getCredito()+"<br>");
				out.println("Descuento: "+a.getDescuento()+"<br>");
				out.println("DNI: "+a.getDni()+"<br>");
				out.println("Direccion: "+a.getDireccion()+"<br>");
				out.println("<br>---------------------***---------------------");
			}
			out.println("</CENTER>");
			out.println("</body>");
			out.println("</html>");
		}}
		
		protected void consultarCliente2(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			ClienteDAO cli=ClienteFactory.getClienteDAO("SQL");
			edu.usal.negocio.dominio.Cliente c1=cli.readCliente(Integer.parseInt(request.getParameter("dni")));
			if(c1==null) {
				PrintWriter out2=response.getWriter();
				out2.println("<html>");
				out2.println("<head></head>");
				out2.println("<body BGCOLOR=#AABBCC>");
				out2.println("<a href=\"conCliente.html\">< Volver</a>");
				out2.println("<CENTER>");
				out2.println("CLIENTE NO ENCONTRADO.");
				out2.println("</CENTER>");
				out2.println("</body>");
				out2.println("</html>");
			}else {
				PrintWriter out=response.getWriter();
			out.println("<html>");
			out.println("<head></head>");
			out.println("<body BGCOLOR=#AABBCC>");
			out.println("<a href=\"conCliente.html\">< Volver</a>");
			out.println("<CENTER>");
			out.println("<h1>Cliente Encontrado</h1>");
			
				out.println("<br>ID: "+c1.getID()+"<br>");
				out.println("<br>Saldo:"+c1.getSaldo()+"<br>");
				out.println("Credito:"+c1.getCredito()+"<br>");
				out.println("Descuento: "+c1.getDescuento()+"<br>");
				out.println("DNI: "+c1.getDni()+"<br>");
				out.println("Direccion: "+c1.getDireccion()+"<br>");
			
			out.println("</CENTER>");
			out.println("</body>");
			out.println("</html>");
		}}
}
