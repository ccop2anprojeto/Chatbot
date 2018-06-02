package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.FilaClienteService;
import model.FilaCliente;
import service.FilaAtendenteService;
import model.Atendimento;
import model.FilaAtendente;

public class insertRowCliente implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		FilaCliente filaCliente = new FilaCliente();
		filaCliente.setId_cliente(Integer.parseInt(request.getParameter("id"))); 		
		
		ArrayList list = new ArrayList();
	
		FilaClienteService service = new FilaClienteService();
		FilaCliente fila = service.searchIntoRow(filaCliente.getId_cliente());
				
		if(fila == null) {			
			list.add(service.insertInRow(filaCliente));
			System.out.println(list.get(0));
		}
		
		FilaAtendenteService serviceA = new FilaAtendenteService();
		Atendimento atend = serviceA.checkAvailability();
		if(atend != null) {
			list.add(atend);
		}
		
				
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
						
		response.getWriter().print(respJSONString);
	

	}

}
