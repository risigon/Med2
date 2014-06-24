package servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.JPAUtilis;
import entidades.consulta;
import entidades.medico;
import entidades.paciente;

/**
 * Servlet implementation class listar
 */
@WebServlet("/listar")
public class listar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		int id = Integer.parseInt(request.getParameter("id"));
		String opc = request.getParameter("opc");
		
		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("usuario")!=null){
		
		switch(opc){
		
		case("listarmednome"):{
			listarMedNome(nome, request, response);
			break;
		}
		
		case("listarmedid"):{
			listarMedId(id, request, response);
			break;
		}
		
		case("listarpacnome"):{
			listarPacNome(nome, request, response);
			break;
		}
		case("listarpac"):{
			listarPac(request, response);
			break;
		}
		
		case("listarpacid"):{
			listarPacId(id, request, response);
			break;
		}
		
		case("listarmed"):{
			listarMed(request, response);
			break;
		}
		case("listarcon"):{
			listarCon(request, response);
			break;
		}
		case("listarconpacnome"):{
			listarConPacNome(nome, request, response);
			break;
		}
		case("listarconmednome"):{
			listarConMedNome(nome, request, response);
			break;
		}
		
		}
		
		}else{
			response.sendRedirect("logindb");
		}
	
		
	}
	
	protected void listarMedNome(String nome, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		List<medico> medicos = Model.Lista.listarMedNome(nome, request, response);
		
		request.setAttribute("medlista", medicos);
		request.getRequestDispatcher("listarMedico.jsp").forward(request, response);
		
	}
	
	protected void listarMedId(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		List<medico> medicos = Model.Lista.listarMedId(id, request, response);

		request.setAttribute("medlista", medicos);
		request.getRequestDispatcher("atualizaMed.jsp").forward(request, response);
		
	}	
	
protected void listarPacNome(String nome, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
			
		List<paciente> pacientes = Model.Lista.listarPacNome(nome, request, response);
	
		request.setAttribute("paclista", pacientes);
		request.getRequestDispatcher("listarPaciente.jsp").forward(request, response);
		
	}

protected void listarPacId(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	
	List<paciente> pacientes = Model.Lista.listarPacId(id, request, response);

	request.setAttribute("paclista", pacientes);
	request.getRequestDispatcher("atualizaPac.jsp").forward(request, response);
	
}


protected void listarPac(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	List<paciente> pacientes = Model.Lista.listarPac(request, response);
	
	request.setAttribute("paclista", pacientes);
	request.getRequestDispatcher("listarPaciente.jsp").forward(request, response);
	
}

protected void listarMed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	List<medico> medicos = Model.Lista.listarMed(request, response);
	
	request.setAttribute("medlista", medicos);
	request.getRequestDispatcher("listarMedico.jsp").forward(request, response);
	
}

protected void listarCon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	List<consulta> consultas = Model.Lista.listarCon(request, response);
	
	request.setAttribute("conlista", consultas);
	request.getRequestDispatcher("listarConsulta.jsp").forward(request, response);
	
}

protected void listarConPacNome(String nome, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	List<consulta> consultas = Model.Lista.listarConPacNome(nome, request, response);
	
	request.setAttribute("conlista", consultas);
	request.getRequestDispatcher("listarConsulta.jsp").forward(request, response);
	
}

protected void listarConMedNome(String nome, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	List<consulta> consultas = Model.Lista.listarConMedNome(nome, request, response);
	
	request.setAttribute("conlista", consultas);
	request.getRequestDispatcher("listarConsulta.jsp").forward(request, response);
	
}



}
