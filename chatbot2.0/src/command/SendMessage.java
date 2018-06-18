package command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import model.Mensagens;
import service.MensagensService;

public class SendMessage implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int id_de = Integer.parseInt(request.getParameter("id_de"));
		int id_para = Integer.parseInt(request.getParameter("id_para"));
		String initAtend = request.getParameter("msg");
		
		
	
//	    Mensagens objMsg = gson.fromJson(jsonMsg, Mensagens.class);
//	    Mensagens objMsg = gson.fromJson(jsonMsg, Mensagens.class);
		
		ArrayList list = new ArrayList();
		Mensagens msg = new Mensagens();
		
		msg.setId_de(id_de);
		msg.setId_para(id_para);
		msg.setMensagem(initAtend);
		msg.setRecebida(0);
		msg.setTime(0);
		Date date = msg.instanceData();
		msg.setData(date);
		System.out.println("Data Message ---- " + msg.getData());
		//ArrayList<Mensagens> msgs = new ArrayList<Mensagens>();
		MensagensService service = new MensagensService();
		
		list.add(service.sendMessage(msg));
		System.out.println(list.get(0));
		
		
		//@SuppressWarnings("rawtypes")
		
												
				
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
