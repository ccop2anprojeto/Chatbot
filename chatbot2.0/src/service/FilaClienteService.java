package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.FilaClienteDAO;


public class FilaClienteService {
	
	FilaClienteDAO dao = new FilaClienteDAO();
	
	public boolean insertInRow(int id_cliente) {
		return dao.insertInRow(id_cliente);		
	}	                     
	
	/*public ArrayList<Mensagens> buscar(int id_de, int id_para){
		return dao.buscar(id_de, id_para);
	}
	public ArrayList<Mensagens> searchFor(int id_para){
		return dao.searchFor(id_para);
	}*/
	
	public boolean deleteInRow(int id_cliente) {
		return dao.deleteInRow(id_cliente);
	}
	
}
