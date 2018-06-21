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
		
		System.out.println("Search all atendimento --- " + serviceAtend.searchAll());
												
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
			
			DateFormat formatoM = new SimpleDateFormat("yyyy/MM/dd");
			Calendar calMonth = Calendar.getInstance();
			Calendar calMonthFormat = Calendar.getInstance();
			Calendar currentDay = new GregorianCalendar();
			Date dateM = new Date();
			currentDay.setTime(dateM);
			int dayOfMonth = currentDay.get(Calendar.DAY_OF_MONTH);
			int firstDayOfMonth = 1;
			int teste = (dayOfMonth - firstDayOfMonth);
			calMonth.add(currentDay.DATE, -teste);
			calMonthFormat = calMonth;
			
			//formatando today e sunday para requisitar ao banco
			String firstDay = formatoM.format(calMonthFormat.getTime());

			System.out.println("FirstDayOfMonth -------->"+ firstDay);
			System.out.println("dayOfMonth -------->"+ dayOfMonth);			
			System.out.println("Dias -------->"+ teste);
			System.out.println("Primeiro dia do mês -------->"+ calMonthFormat.getTime());
			System.out.println("--------------------------------------");
			
			//---------------------------------------------------------
			//Criando json com String para mandar pro front somente dados que serão utilizados
			//-----------------------------------------------------------
			
			//ArrayList<Atendimento> atendsAll = serviceAtend.searchAll();
			
			
			
			
			
			
			String jsonTotals = "{\"daily\":"+ serviceAtend.DailyConsolidator(todayFormat)+","+ "\"week\":"+ serviceAtend.WeekConsolidator(todayFormat, sundayFormat)+"," + "\"month\":"+ serviceAtend.MonthConsolidator(firstDay, todayFormat)+"}";
			//String jsonWeek = "{\"week\":"+ serviceAtend.WeekConsolidator(todayFormat, sundayFormat)+"}";
			//String jsonMonth = "{\"month\":"+ serviceAtend.MonthConsolidator(firstDay, todayFormat)+"}";
			
			
			list.add(jsonTotals);			
			list.add(serviceAtend.searchAll());
		}
		respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
