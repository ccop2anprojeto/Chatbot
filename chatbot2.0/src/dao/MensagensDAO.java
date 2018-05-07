package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.Mensagens;


public class MensagensDAO {
	
	Mensagens cliente = new Mensagens();
	
	
	public int enviar(Mensagens mensagem) {
		String sqlInsert = "INSERT INTO `Mensagens` (`pk_mensagem`, `fk_funcionario`, `fk_cliente`, `mensagem`, `time`, `recebida`) VALUES (default, ?, ?, ?, ?, ?);";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, mensagem.getId_funcionario());
			stm.setInt(2, mensagem.getId_cliente());
			stm.setString(3, mensagem.getMensagem());
			stm.setInt(4, mensagem.getTime());
			stm.setInt(5, mensagem.getRecebida());
					
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

	/*public Mensagens buscar(String cpf) {		
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
	}*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PalavraChave pChave = new PalavraChave(61, "teste2");
		
		//PalavraChaveDAO dao = new PalavraChaveDAO();
		//dao.criar(pChave);
		//System.out.println("teste : " + dao.listarPchave().size());

	}
	
}




