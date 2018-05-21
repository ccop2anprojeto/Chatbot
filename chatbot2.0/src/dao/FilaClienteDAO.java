package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.Mensagens;


public class FilaClienteDAO {	
	
	public boolean insertInRow(int id_cliente) {
		String sqlInsert = "INSERT INTO `FilaCliente` (`pk_cliente`) VALUES (?);";
		
		boolean status = false;
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, id_cliente);			
					
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					status = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	
/*	//BUSCA NOVAS MSG SEM ESPECIFICAR QUEM ENVIOU
	public ArrayList<Mensagens> searchFor(int id_para) {		
		ArrayList<Mensagens> msgs = new ArrayList<Mensagens>();
		Mensagens msg = new Mensagens();
		String sqlSelect = "SELECT `pk_mensagem`, `id_de`, `id_para`, `mensagem`, `time`, `recebida` FROM `mensagem` where mensagem.id_para = ? and mensagem.recebida = 0";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			
			stm.setInt(1, id_para);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					msg.setId(rs.getInt("pk_mensagem"));
					msg.setId_de(rs.getInt("id_de"));
					msg.setId_para(rs.getInt("id_para"));
					msg.setMensagem(rs.getString("mensagem"));
					msg.setTime(rs.getInt("time"));
					msg.setRecebida(rs.getInt("recebida"));
					msgs.add(msg);
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		if(msg.getId() != 0)
			return msgs;
		else
			return null;
	}
	
	
	//BUSCA TOTAL
	public ArrayList<Mensagens> buscar(int id_de, int id_para) {		
		ArrayList<Mensagens> msgs = new ArrayList<Mensagens>();
		Mensagens msg = new Mensagens();
		String sqlSelect = "SELECT `pk_mensagem`, `id_de`, `id_para`, `mensagem`, `time`, `recebida` FROM `mensagem` where mensagem.id_de = ? and mensagem.id_para = ? and mensagem.recebida = 0";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id_de);
			stm.setInt(2, id_para);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					msg.setId(rs.getInt("pk_mensagem"));
					msg.setId_de(rs.getInt("id_de"));
					msg.setId_para(rs.getInt("id_para"));
					msg.setMensagem(rs.getString("mensagem"));
					msg.setTime(rs.getInt("time"));
					msg.setRecebida(rs.getInt("recebida"));
					msgs.add(msg);
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		if(msg.getId() != 0)
			return msgs;
		else
			return null;
	}*/
	public boolean deleteInRow(int id_cliente) {
		
		String sqlUpdate = "DELETE FROM FilaCliente WHERE pk_cliente = ?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, id_cliente);			
						
			stm.execute();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
					
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PalavraChave pChave = new PalavraChave(61, "teste2");
		
		//PalavraChaveDAO dao = new PalavraChaveDAO();
		//dao.criar(pChave);
		//System.out.println("teste : " + dao.listarPchave().size());

	}
	
}




