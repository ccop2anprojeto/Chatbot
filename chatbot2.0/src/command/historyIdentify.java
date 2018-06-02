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
import service.RespostaService;
import service.ClienteService;
import model.Resposta;
import model.Cliente;

public class historyIdentify implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String init = request.getParameter("init");			
		System.out.println("init: --- " + init);
		Resposta resp = new Resposta();
		
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();
		boolean notIdentified;

		
		if(init.contains("começar")) {
			resp.setResp("Olá! Tudo bem? Preciso que por favor informe o seu CPF para poder te identificar");
			list.add(resp);
		}
			
		else {
			ClienteService service = new ClienteService();
			Cliente cliente = new Cliente();
			
			cliente = service.buscar(init);
			System.out.println("-------- busca cpf: " + init);
			
			if(cliente != null) { 			
				resp.setResp("Olá " + cliente.getNome() + ", em que posso te ajudar?");
				notIdentified = false;
				list.add(resp);
				list.add(notIdentified);
			}
			else {
				ArrayList<Resposta> listResp = new ArrayList<Resposta>();
				resp.setResp("Verifiquei em nossa base de dados e você ainda não está cadastrado. Para continuarmos com o atendimento preciso que me informe alguns dados para efetuar seu cadastro em nossa base, vamos lá?");
				Resposta resp1 = new Resposta();
				Resposta resp2 = new Resposta();
				Resposta resp3 = new Resposta();

				resp1.setResp("Por favor, informe seu <b>nome completo<b>");
				resp2.setResp("Agora precisamos de um <b>Número para contato</b>");
				resp3.setResp("Para finalizar por gentileza informe seu <b>Email</b>");
						
				listResp.add(resp);
				listResp.add(resp1);
				listResp.add(resp2);
				listResp.add(resp3);
				
				notIdentified = true;
				
				
				list.addAll(listResp);
				list.add(notIdentified);
														
			}
				
				
		}
									
				
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
