package service;


import dao.FilaAtendenteDAO;
import model.Atendimento;
import model.FilaAtendente;



public class FilaAtendenteService {
	
	FilaAtendenteDAO dao = new FilaAtendenteDAO();
	
	public FilaAtendente insertInRow(FilaAtendente filaAtend) {
		return dao.insertInRow(filaAtend);		
	}	 
	
	public Atendimento checkAvailability() {
		return dao.checkAvailability();
	}
		
	public Atendimento startOnlineSupport(Atendimento atend) {
		return dao.startOnlineSupport(atend);
	}
	
}
