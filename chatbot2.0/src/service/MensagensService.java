package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.MensagensDAO;
import model.Mensagens;

public class MensagensService {
	
	MensagensDAO dao = new MensagensDAO();
	
	public int sendMessage(Mensagens mensagens) {
		return dao.enviar(mensagens);		
	}	                     
	
	public ArrayList<Mensagens> buscar(int id_de, int id_para){
		return dao.buscar(id_de, id_para);
	}
	public ArrayList<Mensagens> searchFor(int id_para){
		return dao.searchFor(id_para);
	}
	
	public boolean alterState(Mensagens msg) {
		return dao.alterState(msg);
	}
	
}
