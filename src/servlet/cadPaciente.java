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
import entidades.paciente;

/**
 * Servlet implementation class cadPaciente
 */
@WebServlet("/cadPaciente")
public class cadPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cadPaciente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("usuario")!=null){		
			request.getRequestDispatcher("cadPaciente.jsp").forward(request, response);
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
		List <paciente> paci = Model.Lista.listarPac(request, response);
		for(paciente pac: paci){
			if(pac.getCpf().equals(cpf)){
				String erro = "CPF Já Cadastrado";
				request.setAttribute("erro", erro);
				request.getRequestDispatcher("erros.jsp").forward(request, response);
			}
			
		}
		inserirPaciente(nome, cpf, endereco, bairro, cidade, estado, login, senha, request, response);
		}
		catch(Exception e){
			
		}finally{
			
		}
		
	}
	
	private static void inserirPaciente(String nome, String cpf, String endereco, String bairro, String cidade, String estado, String login, String senha,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		paciente pac = new paciente();
		
		 pac.setNome(nome);
		 pac.setCpf(cpf);
		 pac.setEndereco(endereco);
		 pac.setBairro(bairro);
		 pac.setCidade(cidade);
		 pac.setEstado(estado);
		 pac.setLogin(login);
		 pac.setSenha(senha);

		 if(Model.Cadastro.Inserir(pac)){
			 request.getRequestDispatcher("cadPaciente.jsp").forward(request, response); 
		 }
		 
	}
	

}
