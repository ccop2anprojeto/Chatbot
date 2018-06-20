package command;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
			//configuração para o total de acesso por semana
			//----------------------------------------------
			Calendar todayC = new GregorianCalendar();
			Calendar cal = Calendar.getInstance();
			Calendar calFormat = Calendar.getInstance();
			
			Date date = new Date();
			todayC.setTime(date);	
			int day = todayC.get(Calendar.DAY_OF_WEEK);
			int sunday = 1;
			int betweenDay = (day - sunday);
			cal.add(todayC.DATE, -betweenDay);
			calFormat = cal;
			
			//formatando today e sunday para requisitar ao banco
			DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
			String todayFormat = formato.format(todayC.getTime());
			String sundayFormat = formato.format(calFormat.getTime());
			
			
			System.out.println("Today -------->"+ todayFormat);
			System.out.println("Sunday -------->"+ sundayFormat);
			System.out.println("Dia da semana -------->"+ day);
			System.out.println("Domingo -------->"+ cal.getTime());
			System.out.println("--------------------------------------");
			
			//----------------------------------------------
			//configuração para mostrar total de acessos por mês
			//-----------------------------------------------
			
			
			Calendar calMonth = Calendar.getInstance();
			Calendar calMonthFormat = Calendar.getInstance();
			Calendar currentDay = new GregorianCalendar();
			
			currentDay.setTime(date);
			int dayOfMonth = todayC.get(Calendar.DAY_OF_MONTH);
			int firstDayOfMonth = 1;
			int teste = (dayOfMonth - firstDayOfMonth);
			calMonth.add(currentDay.DATE, -5);
			calMonthFormat = cal;
			
			//formatando today e sunday para requisitar ao banco
			String firstDay = formato.format(calMonthFormat.getTime());

			System.out.println("FirstDayOfMonth -------->"+ firstDay);
			System.out.println("dayOfMonth -------->"+ dayOfMonth);			
			System.out.println("Dias -------->"+ teste);
			System.out.println("Primeiro dia do mês -------->"+ calMonthFormat.getTime());
			System.out.println("--------------------------------------");
			
			
			list.add(serviceAtend.DailyConsolidator(todayFormat));
			list.add(serviceAtend.WeekConsolidator(todayFormat, sundayFormat));
			list.add(serviceAtend.MonthConsolidator(firstDay, todayFormat));
			list.add(serviceAtend.searchAll());
		}
		respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
