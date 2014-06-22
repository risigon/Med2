package Model;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JPAUtilis;
import entidades.consulta;
import entidades.medico;
import entidades.paciente;

/**
 * Servlet implementation class Cadastro
 */
@WebServlet("/Cadastro")
public class Cadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cadastro() {
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
		// TODO Auto-generated method stub
	}
	
	public static boolean Inserir(medico med) {
		EntityManager conexao=JPAUtilis.criarManager();
		 
		 conexao.getTransaction().begin();
		 conexao.persist(med);
		 conexao.getTransaction().commit();
		  
		 conexao.close();
		 
		return(true); 
		}
	
	public static boolean Inserir(paciente pac) {
		EntityManager conexao=JPAUtilis.criarManager();
		 
		 conexao.getTransaction().begin();
		 conexao.persist(pac);
		 conexao.getTransaction().commit();
		  
		 conexao.close();
		 
		return(true); 
		}

	public static boolean InserirCon(consulta con, paciente pac, medico med) {
		EntityManager conexao=JPAUtilis.criarManager();
		 
		conexao.getTransaction().begin();
		med.setCons(con);
		pac.setCons(con);
		conexao.persist(con);
		conexao.getTransaction().commit();
		  
		conexao.close();
		
		return(true); 
		}

}
