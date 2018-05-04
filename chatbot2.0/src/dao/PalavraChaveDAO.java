package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.PalavraChave;


public class PalavraChaveDAO {
	
	PalavraChave pChave = new PalavraChave();
	
	
	public int criar(PalavraChave pChave) {
		String sqlInsert = "INSERT INTO `palavra-chave` (`pk_palavra-chave`, `palavra-chave`) VALUES (?, ?);";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setLong(1, pChave.getIdPchave());
			stm.setString(2, pChave.getpChave());
					
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					pChave.setIdPchave(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pChave.getIdPchave();
	}

	public ArrayList<PalavraChave> listarPchave() {
		PalavraChave pChave;
		ArrayList<PalavraChave> db_pChave = new ArrayList<PalavraChave>();
		
		String sqlSelect = "SELECT `pk_palavra-chave`, `palavra-chave` FROM `palavra-chave`";
		// usando o try with resources do Java 7, que fecha o que abriu
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					pChave = new PalavraChave();
					pChave.setIdPchave(rs.getInt("pk_palavra-chave"));
					pChave.setpChave(rs.getString("palavra-chave"));								
					
					db_pChave.add(pChave);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return db_pChave;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PalavraChave pChave = new PalavraChave(61, "teste2");
		
		//PalavraChaveDAO dao = new PalavraChaveDAO();
		//dao.criar(pChave);
		//System.out.println("teste : " + dao.listarPchave().size());

	}
	
}




