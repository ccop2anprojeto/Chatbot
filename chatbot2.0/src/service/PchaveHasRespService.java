package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.PchaveHasRespDAO;
import model.PchaveHasResposta;

public class PchaveHasRespService {
	
	PchaveHasRespDAO dao = new PchaveHasRespDAO();
	
	public ArrayList<PchaveHasResposta> listarPchaveHasResp(){
		return dao.listarPchaveHasResp();
	}
	
}
