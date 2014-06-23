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
import entidades.medico;
import entidades.paciente;

/**
 * Servlet implementation class listarMedicos
 */
@WebServlet("/listarMedicos")
public class listarMedicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listarMedicos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("usuario")!=null){
		
		EntityManager conexao=JPAUtilis.criarManager();
		
		/*Query q1 = conexao.createQuery("select count(*) from medico");
		List<Integer> qconta = q1.getResultList();
		request.setAttribute("contador", qconta);
		
		Query query = conexao.createQuery("select p From medico p order by p.nome");
		List<medico> medicos = query.getResultList();*/
		
		List<medico> medicos = Model.Lista.listarMed(request, response);
		
		request.setAttribute("medlista", medicos);
		request.getRequestDispatcher("listarMedico.jsp").forward(request, response);
	}
		else{
			response.sendRedirect("logindb");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
