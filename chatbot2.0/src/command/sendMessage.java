package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import model.Mensagens;
import service.MensagensService;

public class sendMessage implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String jsonMsg = request.getParameter("msg");	
		
		Gson gson = new Gson();
	    Mensagens objMsg = gson.fromJson(jsonMsg, Mensagens.class);
		
		ArrayList list = new ArrayList();
		Mensagens msg = new Mensagens();

		//ArrayList<Mensagens> msgs = new ArrayList<Mensagens>();
		MensagensService service = new MensagensService();
		
		list.add(service.sendMessage(objMsg));
		//System.out.println(msgs.get(0).getMensagem());
		
		
		//@SuppressWarnings("rawtypes")
		
												
				
		
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
