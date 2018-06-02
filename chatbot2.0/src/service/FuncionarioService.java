package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.FuncionarioDAO;
import model.Funcionario;

public class FuncionarioService {
	
	FuncionarioDAO dao = new FuncionarioDAO();
	
	public int criar(Funcionario funcionario) {
		return dao.criar(funcionario);		
	}	
	
	public Funcionario buscar(String login, String senha){
		return dao.buscar(login, senha);
	}
	
}
