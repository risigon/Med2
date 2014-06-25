package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
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
 * Servlet implementation class cadMedico
 */
@WebServlet("/cadMedico")
public class cadMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cadMedico() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("usuario")!=null){		
			request.getRequestDispatcher("cadMedico.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String crm = request.getParameter("crm");
		String esp = request.getParameter("esp");		
		String endereco = request.getParameter("endereco");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String login = request.getParameter("login");
		String passwd = request.getParameter("senha");
		
		String senha=null;
		try {
			senha = senhamd5.stringtomd5(passwd);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		try{
		List<medico> medi = Model.Lista.listarMed(request, response);
	
		for(medico med: medi){
			if(med.getCpf().equals(cpf)||med.getCrm().equals(crm)){
				String erro = "CPF ou CRM Já Cadastrado";
				request.setAttribute("erro", erro);
				request.getRequestDispatcher("erros.jsp").forward(request, response);
			}else{
				
			}
		}
				
		inserirMedico(nome, cpf, crm, esp, endereco, bairro, cidade, estado, login, senha, request, response);
		}
		catch(Exception e){
			
		}finally{
			
		}
	}
	
	private static void inserirMedico(String nome, String cpf, String crm, String esp, String endereco, String bairro, String cidade, String estado, String login, String senha, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		medico med = new medico();
		
		 med.setNome(nome);
		 med.setCpf(cpf);
		 med.setCrm(crm);
		 med.setEspecialidade(esp);
		 med.setEndereco(endereco);
		 med.setBairro(bairro);
		 med.setCidade(cidade);
		 med.setEstado(estado);
		 med.setLogin(login);
		 med.setSenha(senha);
		 
		 if(Model.Cadastro.Inserir(med))
		 
		 request.getRequestDispatcher("cadMedico.jsp").forward(request, response);
			else{
				String erro = "Erro ao cadastrar MEDICO!!!";
				request.setAttribute("erro", erro);
				request.getRequestDispatcher("erros.jsp").forward(request, response);
			}
		 
	}
	

}


