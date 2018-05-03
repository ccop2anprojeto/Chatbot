package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.PchaveHasResposta;


public class PchaveHasRespDAO {
	
	PchaveHasResposta pChaveHasResp = new PchaveHasResposta();
	

	public ArrayList<PchaveHasResposta> listarPchaveHasResp() {
		ArrayList<PchaveHasResposta> db_pChaveHasResp = new ArrayList<PchaveHasResposta>();
		
		//PchaveHasResposta pChaveHasResp = new PchaveHasResposta();
		
		String sqlSelect = "SELECT `palavra-chave_id`, `resposta_id` FROM `palavra-chave_has_resposta`";
		// usando o try with resources do Java 7, que fecha o que abriu
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					pChaveHasResp = new PchaveHasResposta();
					pChaveHasResp.setIdPchave(rs.getInt("palavra-chave_id"));
					pChaveHasResp.setIdResp(rs.getInt("resposta_id"));
					
					db_pChaveHasResp.add(pChaveHasResp);								
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return db_pChaveHasResp;

	}
	
	
}



