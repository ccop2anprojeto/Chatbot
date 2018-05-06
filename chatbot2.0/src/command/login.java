package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.ChatBot;
import service.RespostaService;
import service.FuncionarioService;
import model.Resposta;
import model.Funcionario;

public class login implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		System.out.println("login: --- " + login);
		
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();
		boolean loginAuthorized;
		
		Funcionario func = new Funcionario();
		
		FuncionarioService service = new FuncionarioService();
		func = service.buscar(login, senha);
		if(func != null) {
			loginAuthorized = true;
			list.add(loginAuthorized);
			list.add(func);
		}else {
			loginAuthorized = false;
			list.add(loginAuthorized);
		}
			
		
				
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
