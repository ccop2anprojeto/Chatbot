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

public class InsertRowCliente implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		FilaCliente filaCliente = new FilaCliente();		
		filaCliente.setId_cliente(Integer.parseInt(request.getParameter("id"))); 		
		
		ArrayList list = new ArrayList();
	
		FilaClienteService service = new FilaClienteService();
		FilaCliente fila = service.searchIntoRow(filaCliente.getId_cliente());	
		System.out.println("id FilaCliente --- "+ filaCliente.getId());
		System.out.println("id cliente --- "+ filaCliente.getId_cliente());
				
		if(fila == null) {
			fila = service.insertInRow(filaCliente);
			System.out.println("Insert fila cliente --- "+ fila.getId());
			list.add(fila);
			System.out.println(list.get(0));
		}
		
		
		list.add(filaCliente);
		
		
		/*FilaAtendenteService serviceA = new FilaAtendenteService();
		Atendimento atend = serviceA.checkAvailability();
		if(atend != null) {
			list.add(atend);
			atend.setIdCliente(fila.getId_cliente());
			atend.setIdFilaCliente(fila.getId());
			System.out.println("Id fila: ---" + fila.getId());
			atend.setStatus(0);
			atend = serviceA.startOnlineSupport(atend);
			System.out.println("Id atend: ---" + atend.getId());
			System.out.println("Id func: ---" + atend.getIdFuncionario());
			System.out.println("Id cliente: ---" + atend.getIdCliente());
			
			System.out.println("Atendimento iniciado e inserido com sucesso: ---" + atend.getId());
			//registrando o atendimento;
		}*/
		
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
						
		response.getWriter().print(respJSONString);
	

	}



	

}
