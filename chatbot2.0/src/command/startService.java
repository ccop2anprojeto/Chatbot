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
import service.FilaAtendenteService;
import model.Atendimento;
import service.AtendimentoService;

public class startService implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		
		int idC = Integer.parseInt(request.getParameter("idC"));
		
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();		
		
		Atendimento atend = new Atendimento();
		atend.setIdCliente(idC);
		atend.setIdPergunta(0);
		atend.setIdFuncionario(1);
		atend.setIdFilaCliente(1);
		atend.setStatus(0);
		
		//AtendimentoService serviceAtend = new AtendimentoService();
		//Atendimento startAtend = serviceAtend.startOnlineSupport(atend);
		
		FilaAtendenteService serviceA = new FilaAtendenteService();		
		Atendimento startAtend = serviceA.startOnlineSupport(atend);
		
		System.out.println("Atendimento iniciado --- " + startAtend.getId());
		
		list.add(startAtend);			
												
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
						
		response.getWriter().print(respJSONString);
	

	}

}
