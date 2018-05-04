package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.Resposta;


public class RespostaDAO {
	
		public ArrayList<Resposta> listarResp() {
		ArrayList<Resposta> db_resp = new ArrayList<Resposta>();
		Resposta resp = new Resposta();
		
		String sqlSelect = "SELECT `pk_resposta`, resposta FROM resposta";
		// usando o try with resources do Java 7, que fecha o que abriu
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					resp = new Resposta();
					resp.setIdResp(rs.getInt("pk_resposta"));
					resp.setResp(rs.getString("resposta"));
					
					db_resp.add(resp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return db_resp;
	}	
	
}



