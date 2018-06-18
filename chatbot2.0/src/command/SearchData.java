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

public class SearchData implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		int idCliente = Integer.parseInt(request.getParameter("idC"));
		int idAtendente = Integer.parseInt(request.getParameter("idA"));		
		
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();
		
		ClienteService serviceC = new ClienteService();
		Cliente cliente = serviceC.buscarId(idCliente);
		System.out.println("Search data Cnome--- " + cliente.getNome());
		
		AtendimentoService serviceAtend = new AtendimentoService();
		Atendimento atend = serviceAtend.searchAtend(idCliente, idAtendente);
		
		System.out.println("Search data idAtend --- " + atend.getId());
		
		list.add(cliente);
		list.add(atend);
		
		
		
		//@SuppressWarnings("rawtypes")
		//ArrayList list = new ArrayList();
												
				
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
