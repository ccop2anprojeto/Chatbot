package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.Funcionario;


public class FuncionarioDAO {
	
	Funcionario funcionario = new Funcionario();
	
	
	public int criar(Funcionario funcionario) {
		String sqlInsert = "INSERT INTO `funcionario` (`pk_funcionario`, `Nome`, `Sobrenome`, `Cargo`, `Telefone`, `Email`, `login`, `password`) VALUES (default, ?, ?, ?, ?, ?, ?, ?);";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, funcionario.getNome());
			stm.setString(2, funcionario.getSobrenome());
			stm.setString(3, funcionario.getCargo());
			stm.setString(4, funcionario.getTelefone());
			stm.setString(5, funcionario.getEmail());
			stm.setString(6, funcionario.getLogin());
			stm.setString(7, funcionario.getSenha());
					
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					funcionario.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario.getId();
	}

	public Funcionario buscar(String login, String senha) {		
		Funcionario funcionario = new Funcionario();
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		
		String sqlSelect = "SELECT `pk_funcionario`, `Nome`, `Sobrenome`, `Cargo`, `Telefone`, `Email`, `login`, `password` FROM `funcionario` where funcionario.login = ? and funcionario.password = ?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, funcionario.getLogin());
			stm.setString(2, funcionario.getSenha());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					funcionario.setId(rs.getInt("pk_funcionario"));
					funcionario.setNome(rs.getString("Nome"));
					funcionario.setSobrenome(rs.getString("Sobrenome"));
					funcionario.setCargo(rs.getString("Cargo"));
					funcionario.setTelefone(rs.getString("Telefone"));
					funcionario.setEmail(rs.getString("Email"));
					funcionario.setLogin(rs.getString("login"));
					funcionario.setSenha(rs.getString("password"));
					
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		if(funcionario.getId() != 0)
			return funcionario;
		else
			return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PalavraChave pChave = new PalavraChave(61, "teste2");
		
		//PalavraChaveDAO dao = new PalavraChaveDAO();
		//dao.criar(pChave);
		//System.out.println("teste : " + dao.listarPchave().size());

	}
	
}




