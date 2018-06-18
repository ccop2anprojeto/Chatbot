package command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import model.Cliente;
import service.ClienteService;
import model.Atendimento;
import service.AtendimentoService;

public class Relatorio implements Command {

	@SuppressWarnings("unchecked")
	@Override
	
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		int data = Integer.parseInt(request.getParameter("data"));				
		
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();
		
		
		AtendimentoService serviceAtend = new AtendimentoService();
		
		System.out.println("Search all atendimento --- " + serviceAtend.searchAll().get(0));
												
		String respJSONString = null;
		Gson gson = new Gson();
		if(data != 0) {
			
		}else {
			Calendar todayC = new GregorianCalendar();
			Calendar cal = Calendar.getInstance();
			
			Date date = new Date();
			todayC.setTime(date);	
			int day = todayC.get(Calendar.DAY_OF_WEEK);
			int sunday = 1;
			int betweenDay = (day - sunday);
			cal.add(todayC.DATE, -betweenDay);
			
			System.out.println("Dia da semana -------->"+ day);
			System.out.println("Domingo -------->"+ cal.getTime());
			
			
		//	list.add(serviceAtend.WeekConsolidator(today, sunday));	
			
			list.add(serviceAtend.searchAll());
		}
		respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
