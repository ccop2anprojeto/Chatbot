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

public class insertRowCliente implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int id_cliente = Integer.parseInt(request.getParameter("id"));		
		
//	    Mensagens objMsg = gson.fromJson(jsonMsg, Mensagens.class);
		
		ArrayList list = new ArrayList();
	
		//ArrayList<Mensagens> msgs = new ArrayList<Mensagens>();
		FilaClienteService service = new FilaClienteService();
		
		list.add(service.insertInRow(id_cliente));
		System.out.println(list.get(0));
		
				
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
						
		response.getWriter().print(respJSONString);
	

	}

}
