package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import model.Cliente;
import service.ClienteService;
import model.Atendimento;
import service.AtendimentoService;

public class relatorio implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		int data = Integer.parseInt(request.getParameter("data"));				
		
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();
		
		
		AtendimentoService serviceAtend = new AtendimentoService();
		
		System.out.println("Search all atendimento --- " + serviceAtend.searchAll().get(0));
												
		String respJSONString = null;
		Gson gson = new Gson();
		if(data != 0) {
			
		}else {
			respJSONString = gson.toJson(serviceAtend.searchAll());
		}
		
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
