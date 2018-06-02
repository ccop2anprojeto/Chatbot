package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.FilaCliente;
import model.FilaAtendente;
import model.Atendimento;


public class FilaClienteDAO {	
	
	public FilaCliente insertInRow(FilaCliente filaCliente) {
		String sqlInsert = "INSERT INTO `FilaCliente` (`pk_filaCliente`, `fk_cliente`) VALUES (default, ?);";
		
		FilaCliente rowCliente = new FilaCliente();
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, filaCliente.getId_cliente());			
					
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					rowCliente.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCliente;
		
	}
	
	

	public FilaCliente searchIntoRow(int id_cliente) {
		FilaCliente fila = new FilaCliente();
		String sqlSelect = " select `pk_filacliente`, `fk_cliente` from filacliente where fk_cliente = ?;";		
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id_cliente);									
			
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					
					fila.setId(rs.getInt("pk_filacliente"));
					fila.setId_cliente(rs.getInt("pk_filacliente"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		if(fila.getId() != 0) 
			return fila;
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




