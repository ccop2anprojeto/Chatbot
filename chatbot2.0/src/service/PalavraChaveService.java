package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.PalavraChaveDAO;
import model.PalavraChave;

public class PalavraChaveService {
	
	PalavraChaveDAO dao = new PalavraChaveDAO();
	
	public int criar(PalavraChave pchave) {
		return dao.criar(pchave);		
	}	
	
	public ArrayList<PalavraChave> listarPchave(){
		return dao.listarPchave();
	}
	
}
