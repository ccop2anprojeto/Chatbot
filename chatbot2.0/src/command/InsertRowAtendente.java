package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.FilaAtendenteService;
import model.FilaAtendente;

public class InsertRowAtendente implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		FilaAtendente filaAtend = new FilaAtendente();
		filaAtend.setId_atendente(Integer.parseInt(request.getParameter("id"))); 
		
		System.out.println(filaAtend.getId_atendente());
		
//	    Mensagens objMsg = gson.fromJson(jsonMsg, Mensagens.class);
		
		ArrayList list = new ArrayList();
	
		//ArrayList<Mensagens> msgs = new ArrayList<Mensagens>();
		FilaAtendenteService service = new FilaAtendenteService();
		
		list.add(service.insertInRow(filaAtend));
		System.out.println(list.get(0));
		
				
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
						
		response.getWriter().print(respJSONString);
	

	}

}
