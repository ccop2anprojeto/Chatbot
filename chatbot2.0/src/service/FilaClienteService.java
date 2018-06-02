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
	
	public FilaCliente insertInRow(FilaCliente filaCliente) {
		return dao.insertInRow(filaCliente);		
	}	                     

	public FilaCliente searchIntoRow(int id_cliente) {
		// TODO Auto-generated method stub
		return dao.searchIntoRow(id_cliente);
	}
	
}
