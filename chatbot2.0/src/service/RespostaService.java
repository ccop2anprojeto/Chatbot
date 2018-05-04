package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.RespostaDAO;
import model.Resposta;

public class RespostaService {
	
	RespostaDAO dao = new RespostaDAO();
		
	public ArrayList<Resposta> listarResp(){
		return dao.listarResp();
	}
	
}
