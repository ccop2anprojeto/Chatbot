package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.ClienteDAO;
import model.Cliente;

public class ClienteService {
	
	ClienteDAO dao = new ClienteDAO();
	
	public int criar(Cliente cliente) {
		return dao.criar(cliente);		
	}	
	
	public Cliente buscar(String cpf){
		return dao.buscar(cpf);
	}
	public Cliente buscarId(int idC){
		return dao.buscarId(idC);
	}
	
}
