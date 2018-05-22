package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.FilaClienteDAO;
import model.FilaCliente;
import model.Atendimento;
import model.FilaAtendente;


public class FilaClienteService {
	
	FilaClienteDAO dao = new FilaClienteDAO();
	
	public boolean insertInRow(FilaCliente filaCliente) {
		return dao.insertInRow(filaCliente);		
	}	                     
	
	/*public ArrayList<Mensagens> buscar(int id_de, int id_para){
		return dao.buscar(id_de, id_para);
	}
	public ArrayList<Mensagens> searchFor(int id_para){
		return dao.searchFor(id_para);
	}*/
	
	public Atendimento startOnlineSupport(FilaCliente filaCliente, FilaAtendente filaAtendente) {
		return dao.startOnlineSupport(filaCliente, filaAtendente);
	}
	
}
