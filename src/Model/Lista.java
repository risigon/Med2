package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
 * Servlet implementation class Lista
 */
@WebServlet("/Lista")
public class Lista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lista() {
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

	public static List listarMedNome(String nome, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		EntityManager conexao=JPAUtilis.criarManager();
		
		List <medico> medicos = new ArrayList<>();
		try{
		Query q1 = conexao.createQuery("select count(*) from medico p where p.nome LIKE:nomebusca");
		q1.setParameter("nomebusca", "%"+nome+"%");
		List<Integer> qconta = q1.getResultList();
		request.setAttribute("contador", qconta);
		
		Query query = conexao.createQuery("select p From medico p where p.nome LIKE:nomebusca order by p.nome");
		query.setParameter("nomebusca", "%"+nome+"%");
		medicos = query.getResultList();
		}
		catch(Exception e){
			
		}
		finally{
			conexao.close();
		}
	
		return medicos;
		 
		
	}
	
	public static List listarMedId(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		EntityManager conexao=JPAUtilis.criarManager();
		
		List <medico> medicos = null;
		
		try{
		Query query = conexao.createQuery("select p From medico p where p.idmed=:idbusca");
		query.setParameter("idbusca", id);
		
		medicos = query.getResultList();
		}
		catch(Exception e){
			
		}
		finally{
			conexao.close();
		}
		
		return medicos;

		
	}


public static List listarPacNome(String nome, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		EntityManager conexao=JPAUtilis.criarManager();
		
		Query q1 = conexao.createQuery("select count(*) from paciente p where p.nome LIKE:nomebusca");
		q1.setParameter("nomebusca", "%"+nome+"%");
		List<Integer> qconta = q1.getResultList();
		request.setAttribute("contador", qconta);
		
		Query query = conexao.createQuery("select p From paciente p where p.nome LIKE:nomebusca order by p.nome");
		query.setParameter("nomebusca", "%"+nome+"%");
		List<paciente> pacientes = query.getResultList();

	
		return pacientes;
		 
		
	}

public static List listarPacId(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	EntityManager conexao=JPAUtilis.criarManager();
	
	Query query = conexao.createQuery("select p From paciente p where p.idpac=:idbusca");
	query.setParameter("idbusca", id);
	
	List<paciente> pacientes = query.getResultList();


	return pacientes;
	 
	
}


public static List listarPac(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	EntityManager conexao=JPAUtilis.criarManager();
	
	Query q1 = conexao.createQuery("select count(*) from paciente");
	List<Integer> qconta = q1.getResultList();
	request.setAttribute("contador", qconta);
	
	Query query = conexao.createQuery("select p From paciente p order by p.nome");
	List<paciente> pacientes = query.getResultList();
	
	return pacientes;
	 
}

public static List listarMed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	EntityManager conexao=JPAUtilis.criarManager();
	
	List <medico> medicos = null;
	try{
	Query q1 = conexao.createQuery("select count(*) from medico");
	List<Integer> qconta = q1.getResultList();
	request.setAttribute("contador", qconta);
	
	Query query = conexao.createQuery("select p From medico p order by p.nome");
	medicos = query.getResultList();
	}
	catch(Exception e){
		
	}
	finally{
		conexao.close();
	}
	return medicos;
	 
}

public static List listarCon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	EntityManager conexao=JPAUtilis.criarManager();
	
	Query q1 = conexao.createQuery("select count(*) from consulta");
	List<Integer> qconta = q1.getResultList();
	request.setAttribute("contador", qconta);
	
	Query query = conexao.createQuery("select c From consulta c order by c.id");
	List<consulta> consultas = query.getResultList();
	
	return consultas;
	 
}

public static List listarConPacNome(String nome, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	EntityManager conexao=JPAUtilis.criarManager();
	
	Query q1 = conexao.createQuery("select count(*) from consulta c where c.pac.nome LIKE:nomebusca");
	q1.setParameter("nomebusca", "%"+nome+"%");
	List<Integer> qconta = q1.getResultList();
	request.setAttribute("contador", qconta);
	
	Query query = conexao.createQuery("select c From consulta c where c.pac.nome LIKE:nomebusca order by c.pac.nome");
	query.setParameter("nomebusca", "%"+nome+"%");
	List<consulta> consultas = query.getResultList();

	
	return consultas;
	 
}

public static List listarConMedNome(String nome, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	EntityManager conexao=JPAUtilis.criarManager();
	
	Query q1 = conexao.createQuery("select count(*) from consulta c where c.med.nome LIKE:nomebusca");
	q1.setParameter("nomebusca", "%"+nome+"%");
	List<Integer> qconta = q1.getResultList();
	request.setAttribute("contador", qconta);
	
	Query query = conexao.createQuery("select c From consulta c where c.med.nome LIKE:nomebusca order by c.med.nome");
	query.setParameter("nomebusca", "%"+nome+"%");
	List<consulta> consultas = query.getResultList();
	
	
	return consultas;
	 
}
}
