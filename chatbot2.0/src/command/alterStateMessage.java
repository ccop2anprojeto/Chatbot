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

public class alterStateMessage implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int idMsg = Integer.parseInt(request.getParameter("idMsg"));		
		ArrayList list = new ArrayList();
		Mensagens msg = new Mensagens();
		msg.setId(idMsg);
		//ArrayList<Mensagens> msgs = new ArrayList<Mensagens>();
		MensagensService service = new MensagensService();
		
		list.add(service.alterState(msg));
		//System.out.println(msgs.get(0).getMensagem());
		
		
		//@SuppressWarnings("rawtypes")
		
												
				
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
