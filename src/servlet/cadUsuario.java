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
import entidades.Login;

/**
 * Servlet implementation class cadUsuario
 */
@WebServlet("/cadUsuario")
public class cadUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cadUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("usuario")!=null && sessao.getAttribute("usuario")!=""){		
			request.getRequestDispatcher("cadUsuario.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario = request.getParameter("usuario");
		String passwd = request.getParameter("pass");
		String confpasswd = request.getParameter("passconf");
		
		if(passwd.equals(confpasswd)&&validausuario(usuario,request,response)){
		String senha=null;
		try {			
			senha = senhamd5.stringtomd5(passwd);
			inserirUsuario(usuario, senha, request, response);
		} 
		catch (NoSuchAlgorithmException e1) {
			
			e1.printStackTrace();
		} 
		finally{
			
		}
		
	
		}else{
			String erro = "Senha Diferente de Confirmar Senha";
			request.setAttribute("erro", erro);
			request.getRequestDispatcher("erros.jsp").forward(request, response);
		}
		
	}
	
	protected void inserirUsuario(String usuario, String senha, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Login log = new Login();
		
		log.setUsuario(usuario);
		log.setSenha(senha);
		
		try{
			 if(Model.Cadastro.InserirUsu(log)){
				 request.getRequestDispatcher("cadUsuario.jsp").forward(request, response);
			 }else{
					String erro = "Usuario Não Cadastrado";
					request.setAttribute("erro", erro);
					request.getRequestDispatcher("erros.jsp").forward(request, response);
				}
				 
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
		 
		 }	
				
	}
	
	protected boolean validausuario(String usuario, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Login> log = Model.Lista.listarUsu(request, response);
		
		for(Login usu: log){
			if(usu.getUsuario().equals(usuario)){
				return false;
			}
		}
		return true;
	}

}

