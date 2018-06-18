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

public class HistoryRegister implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Cliente cliente = new Cliente();
		cliente.setCpf(request.getParameter("Cpf"));
		cliente.setNome(request.getParameter("Nome"));
		cliente.setSobrenome(request.getParameter("Nome"));
		
		cliente.setTelefone(request.getParameter("Telefone"));
		cliente.setEmail(request.getParameter("Email"));
		
		System.out.println("cliente: --- " + cliente.getNome());
		Resposta resp = new Resposta();
		
		ClienteService service = new ClienteService();
		ArrayList<Resposta> listResp = new ArrayList<Resposta>();
		ArrayList list = new ArrayList();
		Resposta resp1 = new Resposta();
		boolean notIdentified;
	    
		cliente.setId(service.criar(cliente)); 
		
		if(cliente.getId() != 0) {
			resp.setResp("Cadastro efetuado com sucesso!");	
			resp1.setResp("Vamos lá! Em que posso te ajudar " + cliente.getNome() + "?");
			listResp.add(resp);
			listResp.add(resp1);
			notIdentified = false;
			
			list.add(cliente);
		}			
		else {
			resp.setResp("Ouve um erro no sistema e não foi possível efetuar o cadastro");
			listResp.add(resp);
			notIdentified = true;
		}
			
					
		list.addAll(listResp);
		list.add(notIdentified);
													
		Gson gson = new Gson();
		String respJSONString = gson.toJson(list);
		System.out.println(respJSONString);		
		
				
		response.getWriter().print(respJSONString);
	

	}

}
