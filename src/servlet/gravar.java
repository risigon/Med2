package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JPAUtilis;
import entidades.medico;
import entidades.paciente;

/**
 * Servlet implementation class gravar
 */
@WebServlet("/gravar")
public class gravar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gravar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opc = request.getParameter("opc");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String crm = request.getParameter("crm");
		String esp = request.getParameter("esp");
		String endereco = request.getParameter("endereco");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		
		
		switch(opc){
		case ("atualizapac"):{
			if(Model.Atualizar.atualizarPac(id, nome, cpf, endereco, bairro, cidade, estado)){
				response.sendRedirect("listarPaciente");	
			}else{
				String erro = "Erro ao Atualizar cadastro do Paciente "+nome;
				request.setAttribute("erro", erro);
				request.getRequestDispatcher("erros.jsp").forward(request, response);
			}
			break;
				
		}
		case ("atualizamed"):{
			if(Model.Atualizar.atualizarMed(id, nome, cpf, crm, esp, endereco, bairro, cidade, estado)){
				response.sendRedirect("listarMedicos");	
			}else{
				String erro = "Erro ao Atualizar cadastro do Médico "+nome;
				request.setAttribute("erro", erro);
				request.getRequestDispatcher("erros.jsp").forward(request, response);
			}
			
			
			break;
		}
		}
		
	}
	
	private static void atualizarMedico(int id, String nome, String cpf, String crm, String esp, String endereco, String bairro, String cidade, String estado){
												 
				 //criar conexao
				 EntityManager conexao=JPAUtilis.criarManager();
				 
				 
				 try{
				 conexao.getTransaction().begin();
				 Query q1 = conexao.createQuery("update medico set nome=:nome, cpf=:cpf, crm=:crm , especialidade=:esp, endereco=:endereco, bairro=:bairro, cidade=:cidade, estado=:estado where id=:id");
				 q1.setParameter("id", id);
				 q1.setParameter("nome", nome);
				 q1.setParameter("cpf", cpf);
				 q1.setParameter("crm", crm);
				 q1.setParameter("esp", esp);
				 q1.setParameter("endereco", endereco);
				 q1.setParameter("bairro", bairro);
				 q1.setParameter("cidade", cidade);
				 q1.setParameter("estado", estado);
				 q1.executeUpdate();
				 //conexao.merge(med);
				 conexao.getTransaction().commit();
				 }catch(Exception e){
					conexao.getTransaction().rollback(); //volta ao estado anterior 
				 }finally{	 
				 conexao.close();
				 }
	 
		 
	}

	private static void atualizarPaciente(int id, String nome, String cpf, String endereco, String bairro, String cidade, String estado){
		 
		 //criar conexao
		 EntityManager conexao=JPAUtilis.criarManager();
		 
		 		 
		 try{
		 conexao.getTransaction().begin();
		 Query q2 = conexao.createQuery("update paciente p set p.nome=:nome, p.cpf=:cpf, p.endereco=:endereco, p.bairro=:bairro, p.cidade=:cidade, p.estado=:estado where p.id=:id");
		 q2.setParameter("id", id);
		 q2.setParameter("nome", nome);
		 q2.setParameter("cpf", cpf);
		 q2.setParameter("endereco", endereco);
		 q2.setParameter("bairro", bairro);
		 q2.setParameter("cidade", cidade);
		 q2.setParameter("estado", estado);
		 q2.executeUpdate();
		 conexao.getTransaction().commit();
		 }catch(Exception e){
			conexao.getTransaction().rollback(); //volta ao estado anterior 
		 }finally{	 
		 conexao.close();
		 }
		 
	}
	
}
