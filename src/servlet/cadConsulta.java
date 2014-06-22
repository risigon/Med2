package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
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
import servlet.converterdatas;

/**
 * Servlet implementation class cadConsulta
 */
@WebServlet("/cadConsulta")
public class cadConsulta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cadConsulta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("usuario")!=null){		
			
			List<medico> med = new ArrayList<>();
			List<paciente> pac = new ArrayList<>();
			
			EntityManager conexao=JPAUtilis.criarManager();
			try{ 
			Query q1 = conexao.createQuery("select m from medico m order by nome");
			med = q1.getResultList();
			
			Query q2 = conexao.createQuery("select p from paciente p order by nome");
			pac = q2.getResultList();
			
			}catch(Exception e){
				response.getWriter().println("Erro ao Listar Médico e Paciente");
			}finally{
					conexao.close();
			}
			
			request.setAttribute("listapac", pac);
			
			request.setAttribute("listamed", med);
			
			request.getRequestDispatcher("cadConsulta.jsp").forward(request, response);
			
		}else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomepac = request.getParameter("nomepac");
		String nomemed = request.getParameter("nomemed");
		String dtcons = request.getParameter("dataconsulta");
		String horacons = request.getParameter("horaconsulta");
		String obs = request.getParameter("obs");
		String retorno = request.getParameter("retorno");
		
				
		inserirConsulta(nomepac, nomemed, dtcons, horacons, obs, retorno);
		
		response.sendRedirect("cadConsulta");		
		
	
		
	}

	private static void inserirConsulta(String nomepac, String nomemed, String dtcons, String horacons, String obs, String retorno){
		
		//criar conexao
		 EntityManager conexao=JPAUtilis.criarManager();
		
		consulta con = new consulta();
		medico med = new medico();
		paciente pac = new paciente();
		
		Query q1 = conexao.createQuery("select p from paciente p where p.nome=:nomepac");
		q1.setParameter("nomepac", nomepac);
		pac = (paciente) q1.getSingleResult();
		
		Query q2 = conexao.createQuery("select m from medico m where m.nome=:nomemed");
		q2.setParameter("nomemed", nomemed);
		med = (medico) q2.getSingleResult();
		
		con.setMed(med);
		con.setPac(pac);
		con.setEspecialidade(med.getEspecialidade());
		con.setDtcons(converterdatas.DataToDate(dtcons));
		con.setHora(horacons);
		con.setObs(obs);
		con.setRetorno(retorno);
		
		Model.Cadastro.InserirCon(con, pac, med);
		 
		 	 
		 
	}
	
	
	
	

}
