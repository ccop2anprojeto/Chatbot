package command;

import java.io.IOException;
import java.util.ArrayList;

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
import service.FilaAtendenteService;

public class alterService implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		
		int idAtend = Integer.parseInt(request.getParameter("id"));		
		
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();		
		
		//buscar atendente disponivel-----
		
		FilaAtendenteService serviceA = new FilaAtendenteService();
		Atendimento atend = serviceA.checkAvailability();
		
		//buscar atendimento iniciado pelo bot
		//Se já tiver encontrado um atendente disponivel 
		list.add(atend);
		if(atend != null) {
			AtendimentoService serviceAtend = new AtendimentoService();
			//metodo retorna o atendimento iniciado pelo bot
			Atendimento atendimento = serviceAtend.searchAtend(idAtend);
			if(atendimento != null) {
				atendimento.setIdFuncionario(atend.getIdFuncionario());
				
				//inserir o id do funcionario no atendimento iniciado pelo bot
				//registrando o atendimento com funcionario;
				Atendimento startAtend = serviceAtend.startOnlineSupport(atendimento);
				if(startAtend != null) {
					list.add(atendimento);
				}
				System.out.println("Atendimento iniciado e inserido com sucesso: ---" + atendimento.getId());
				System.out.println("Id funcionario ---" + atendimento.getIdFuncionario());
			}
		}
		
					
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
						
		response.getWriter().print(respJSONString);
	

	}

}
