import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ChatBot;
import model.Consulta;
import model.PalavraChave;
import model.PchaveHasResposta;
import model.Resposta;

@WebServlet("/ChatBotServelet.do")

public class ChatBotServelet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {
		String perg = request.getParameter("pergunta");		
	
		//instanciar o javabean
		ChatBot bot = new ChatBot();
		bot.setPergunta(perg);
		
		ArrayList<Resposta> resp = new ArrayList<Resposta>();
		
		resp.addAll(bot.searchPchave(bot.getPergunta()));
		
		System.out.println("ID Resp: --- " + resp.get(1).getResp());
		//instanciar o service
	
		//enviar para o jsp
		request.setAttribute("resp", resp.get(0));
	
		RequestDispatcher view =
		request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}
	
}
