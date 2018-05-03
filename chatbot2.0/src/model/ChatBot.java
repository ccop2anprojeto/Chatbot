package model;

import java.util.Scanner;

import service.PalavraChaveService;
import service.PchaveHasRespService;
import service.RespostaService;
import java.util.ArrayList;
	

public class ChatBot {	
	int idChatbot;
	String pergunta;
	
	//--------------- metodos
	public String getPergunta() {
		return pergunta;
	}


	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	
	public int getIdChatbot() {
		return idChatbot;
	}

	public void setIdChatbot(int idChatbot) {
		this.idChatbot = idChatbot;
	}

	public ArrayList<Resposta> searchPchave(String pergunta) {	  	    	  	  
	    String quest;
	    ArrayList<PalavraChave> relatedKey = new ArrayList<PalavraChave>();
	    //frase de não entendi
		    Resposta respException = new Resposta();
		    respException.setIdResp(1);
		    respException.setResp("Desculpe, mas não entendi, pode por favor dizer com outras palavras qual é a sua dúvida?");
		    
		    ArrayList<Resposta> exception = new ArrayList<Resposta>();
		    exception.add(respException);
		    
	    System.out.println(pergunta);
	    
	    //tratando a pergunta
	    quest = pergunta.toLowerCase();
	    
	    //pegando dados da tabela palavra chave
	    PalavraChaveService service = new PalavraChaveService();
	    
		ArrayList<PalavraChave> db_Pchave = new ArrayList<PalavraChave>();
		db_Pchave.addAll(service.listarPchave());
			    
    
		for(int j = 0; j < db_Pchave.size(); j++) {
			// System.out.println(db_Pchave.get(j).getpChave());
			 
			if(quest.contains(db_Pchave.get(j).getpChave())) {
				relatedKey.add(db_Pchave.get(j));
			}
		}
		
		if(relatedKey.size() > 0)		
			return searchPchave_has_resp(relatedKey);
		else
			return exception;
	} 
	
	
	
	//Busca os id das respostas relacionadas com palavras chaves
	public ArrayList<Resposta> searchPchave_has_resp(ArrayList<PalavraChave> relatedKey){
		System.out.println("aquii--------" + relatedKey.size());
		ArrayList<PchaveHasResposta> keyHasResp = new ArrayList<PchaveHasResposta>();
		
		//pegando dados da tabela palavra chave
	    PchaveHasRespService service = new PchaveHasRespService();
	    
		ArrayList<PchaveHasResposta> db_PchaveHasResp = new ArrayList<PchaveHasResposta>();
		db_PchaveHasResp.addAll(service.listarPchaveHasResp());
		
	       	   
	    
	    
	    for(int i = 0; i < relatedKey.size(); i++) {
			for(int j = 0; j < db_PchaveHasResp.size(); j++) {
				// System.out.println(c.getPchaveHasResp().get(j).getIdResp());
				 
				if(relatedKey.get(i).getIdPchave() == db_PchaveHasResp.get(j).getIdPchave()) {
					keyHasResp.add(db_PchaveHasResp.get(j));
					
				}
			}
	    }
	    return searchResp(keyHasResp);
				
	}
	
	public ArrayList<Resposta> searchResp(ArrayList<PchaveHasResposta> keyHasResp){	
		System.out.println("aquii--------" + keyHasResp.size());
		ArrayList<Resposta> keyResp = new ArrayList<Resposta>();
	    	    	    
		//pegando dados da tabela palavra chave
	    RespostaService service = new RespostaService();
	    
		ArrayList<Resposta> db_resp = new ArrayList<Resposta>();
		db_resp.addAll(service.listarResp());
		
		System.out.println("size-------" +  keyHasResp.size());
	    for(int i = 0; i < keyHasResp.size(); i++) {
			for(int j = 0; j < db_resp.size(); j++) {	
				
				if(db_resp.get(j).getIdResp() == keyHasResp.get(i).getIdResp()) {
					if(keyResp.size() < 3)
						keyResp.add(db_resp.get(j));
					
					System.out.println("entrou no if" + j);
					System.out.println(db_resp.get(j).getResp());
				}
			}
	    }
		return keyResp;
	}
		
}
