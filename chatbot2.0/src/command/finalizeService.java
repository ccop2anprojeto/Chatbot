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

public class finalizeService implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		
		int idAtendimento = Integer.parseInt(request.getParameter("id"));
		
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();
		
		
		Atendimento atend = new Atendimento();
		atend.setId(idAtendimento);
		AtendimentoService serviceAtend = new AtendimentoService();
		boolean finalized = serviceAtend.finalizeService(atend);
		
		System.out.println("Service finalizado --- " + finalized);
		
		list.add(finalized);			
												
				
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
