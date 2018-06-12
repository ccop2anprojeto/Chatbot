package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.Mensagens;


public class MensagensDAO {
	
	Mensagens mensagem = new Mensagens();
	
	
	public int enviar(Mensagens mensagem) {
		String sqlInsert = "INSERT INTO `Mensagem` (`pk_mensagem`, `id_de`, `id_para`, `mensagem`, `time`, `recebida`, `data`) VALUES (default, ?, ?, ?, ?, ?, ?);";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, mensagem.getId_de());
			stm.setInt(2, mensagem.getId_para());
			stm.setString(3, mensagem.getMensagem());
			stm.setInt(4, mensagem.getTime());
			stm.setInt(5, mensagem.getRecebida());
			stm.setDate(6, new java.sql.Date(mensagem.getData().getTime()));
			System.out.println("Data Message Dao---- " + mensagem.getData().getTime());
					
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					mensagem.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(mensagem.getId() != 0) {
			return mensagem.getId();
		}else {
			return 0;
		}
		
	}
	
	
	//BUSCA NOVAS MSG SEM ESPECIFICAR QUEM ENVIOU
	public ArrayList<Mensagens> searchFor(int id_para) {		
		ArrayList<Mensagens> msgs = new ArrayList<Mensagens>();
		Mensagens msg = new Mensagens();
		String sqlSelect = "SELECT `pk_mensagem`, `id_de`, `id_para`, `mensagem`, `time`, `recebida`, `data` FROM `mensagem` where mensagem.id_para = ? and mensagem.recebida = 0";
		
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
					msg.setData(rs.getDate("data"));
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
		String sqlSelect = "SELECT `pk_mensagem`, `id_de`, `id_para`, `mensagem`, `time`, `recebida`, `data` FROM `mensagem` where mensagem.id_de = ? and mensagem.id_para = ? and mensagem.recebida = 0";
		
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
					msg.setData(rs.getDate("data"));
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
	public boolean alterState(Mensagens msg) {
		
		msg.setRecebida(1);
		String sqlUpdate = "UPDATE mensagem SET recebida = ? WHERE mensagem.pk_mensagem = ?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, msg.getRecebida());
			stm.setInt(2, msg.getId());
						
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




