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

public class searchMessage implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		int id_para = Integer.parseInt(request.getParameter("id_para"));
		
		ArrayList<Mensagens> msgs = new ArrayList<Mensagens>();
		MensagensService service = new MensagensService();
		
		msgs = service.searchFor(id_para);
		//System.out.println(msgs.get(0).getMensagem());
		
		
		//@SuppressWarnings("rawtypes")
		//ArrayList list = new ArrayList();
												
				
		Gson gson = new Gson();
		String respJSONString = gson.toJson(msgs);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
