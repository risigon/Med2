package servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import util.JPAUtilis;
import entidades.consulta;
import entidades.medico;
import entidades.paciente;

/**
 * Servlet implementation class excluir
 */
@WebServlet("/excluir")
public class excluir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public excluir() {
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
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		String opc = request.getParameter("opc");
		
		switch(opc){
		case ("excluirpac"):{
			if(Model.Exclusao.excluirPaciente(id))
			response.sendRedirect("listarPaciente");
			else{
				String erro = "Erro ao excluir o PACIENTE!!! Verificar se o Id: "+id+" esta correto ou se há consulta para o mesmo";
				request.setAttribute("erro", erro);
				request.getRequestDispatcher("erros.jsp").forward(request, response);
			}
			break;
		}
		case ("excluirmed"):{
			if(Model.Exclusao.excluirMedico(id))
			response.sendRedirect("listarMedicos");
			else{
				String erro = "Erro ao excluir MEDICO!!! Verificar se o Id: "+id+" esta correto ou se há consulta para o mesmo";
				request.setAttribute("erro", erro);
				request.getRequestDispatcher("erros.jsp").forward(request, response);
			}
			break;
		}
		case ("excluircon"):{
			if(Model.Exclusao.excluirConsulta(id))
			response.sendRedirect("listarConsulta");
			else{
				String erro = "Erro ao excluir a CONSULTA, verificar se existe o Id: "+id;
				request.setAttribute("erro", erro);
				request.getRequestDispatcher("erros.jsp").forward(request, response);
			}
			break;
		}
		}
		
		
	}
	
	private static void excluirPaciente(int id){
					
		//criar conexao
		 EntityManager conexao=JPAUtilis.criarManager();
		 
		 paciente pac = conexao.find(paciente.class, id);
		 try{
		 conexao.getTransaction().begin();
		 conexao.remove(pac);
		 conexao.getTransaction().commit();
		 } catch(Exception e){
		 conexao.getTransaction().rollback();	 
		 }finally{
		 conexao.close();	 
		 }
		 System.out.println("Id: "+ pac.getIdpac()+"Nome: "+pac.getNome()+"Excluido");
		
	}
	
	private static void excluirMedico(int id){
		
		//criar conexao
		 EntityManager conexao=JPAUtilis.criarManager();
		 
		 medico med = conexao.find(medico.class, id);
		 try{
		 conexao.getTransaction().begin();
		 conexao.remove(med);
		 conexao.getTransaction().commit();
		 } catch(Exception e){
		 conexao.getTransaction().rollback();	 
		 }finally{
		 conexao.close();	 
		 }
		 System.out.println("Id: "+ med.getIdmed()+"Nome: "+med.getNome()+"Excluido");
		
	}
	
private static void excluirConsulta(int id){
		
		//criar conexao
		 EntityManager conexao=JPAUtilis.criarManager();
		 
		 consulta con = conexao.find(consulta.class, id);
		 try{
		 conexao.getTransaction().begin();
		 conexao.remove(con);
		 conexao.getTransaction().commit();
		 } catch(Exception e){
		 conexao.getTransaction().rollback();	 
		 }finally{
		 conexao.close();	 
		 }
		 System.out.println("Id Consulta: "+ con.getId()+"Nome: "+con.getPac().getNome()+"Excluido");
		
	}
}
