package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.Cliente;


public class ClienteDAO {
	
	Cliente cliente = new Cliente();
	
	
	public int criar(Cliente cliente) {
		String sqlInsert = "INSERT INTO `cliente` (`pk_cliente`, `Nome`, `Sobrenome`, `Telefone`, `Email`, `cpf`) VALUES (default, ?, ?, ?, ?, ?);";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getSobrenome());
			stm.setString(3, cliente.getTelefone());
			stm.setString(4, cliente.getEmail());
			stm.setString(5, cliente.getCpf());
					
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente.getId();
	}

	public Cliente buscar(String cpf) {		
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		
		String sqlSelect = "SELECT `pk_cliente`, `Nome`, `Sobrenome`, `Telefone`, `Email`, `cpf` FROM `cliente` where cliente.cpf = ?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, cliente.getCpf());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt("pk_cliente"));
					cliente.setNome(rs.getString("Nome"));
					cliente.setSobrenome(rs.getString("Sobrenome"));
					cliente.setTelefone(rs.getString("Telefone"));
					cliente.setEmail(rs.getString("Email"));
					cliente.setCpf(rs.getString("cpf"));
					
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		if(cliente.getId() != 0)
			return cliente;
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




