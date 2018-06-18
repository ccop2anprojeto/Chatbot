package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.ChatBot;
//import service.OrganizacaoSocialService;
import model.Resposta;

public class NewChatBot implements Command {

	@Override
	
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("application/json;charset=iso-8859-1");
		String perg = request.getParameter("perg");			
		System.out.println("Pergunta: --- " + perg);
		
		ChatBot bot = new ChatBot();
				
		ArrayList<Resposta> resp = new ArrayList<Resposta>();
		HttpSession session = request.getSession();
						
		
		if (perg != null && perg.length() > 0) {
			resp.addAll(bot.searchPchave(perg));
			//System.out.println("ID Resp: --- " + resp.get(0).getResp());
		} else {
			resp = null;
		}
		/*if(session.isNew() == false) {
			session.invalidate();
			session = request.getSession(true);
		}*///removeValue
				
		if(session.getAttribute(perg) != null) {
			session.invalidate();
			System.out.println("Sessão invalida");
			
		}else {
			session.setAttribute("resp", resp);
			session.setAttribute("perg", perg);
		}		
		    
		//código que faz o trabalho ;-)
		Gson gson = new Gson();	

		String respJSONString = gson.toJson(resp);
		System.out.println(respJSONString);
	//	String json = "{\"pergunta\" : reafw, }";
		
		
		
		
		response.getWriter().print(respJSONString);
		//RequestDispatcher dispatcher = request.getRequestDispatcher(respJSONString);
		//dispatcher.forward(request, response);	

	}

}
