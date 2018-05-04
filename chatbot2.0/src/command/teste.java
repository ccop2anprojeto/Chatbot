package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ChatBot;
//import service.OrganizacaoSocialService;
import model.Resposta;

public class teste implements Command {

	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	/*	String perg = request.getParameter("pergunta");	
		
		
		ChatBot bot = new ChatBot();
		bot.setPergunta(perg);
		
		ArrayList<Resposta> resp = new ArrayList<Resposta>();
			
		resp.addAll(bot.searchPchave(bot.getPergunta()));
		System.out.println("ID Resp: --- " + resp.get(1).getResp());*/
		
		
		RequestDispatcher view = null;

//		@SuppressWarnings("unchecked")
		request.setAttribute("resp", resp.get(0).getResp());
		view = request.getRequestDispatcher("index.jsp");

		view.forward(request, response);

	}


}
