package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.FilaCliente;
import model.FilaAtendente;
import model.Funcionario;
import model.Atendimento;



public class FilaAtendenteDAO {	
	
	public FilaAtendente insertInRow(FilaAtendente filaAtend) {
		String sqlInsert = "INSERT INTO `FilaAtendente` (`pk_filaAtendente`, `fk_atendente`) VALUES (default, ?);";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, filaAtend.getId_atendente());			
					
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					filaAtend.setId(rs.getInt(1));					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filaAtend;
		
	}
	
	
	//BUSCA Atendentes disponiveis
	public Atendimento checkAvailability() {				
		String sqlSelect = "select `pk_filaatendente`, `fk_atendente`, count(atend.fk_funcionario) from `filaatendente` fila left join atendimento atend on fila.fk_atendente = atend.fk_funcionario where atend.status = 0 group by fk_funcionario having count(atend.fk_funcionario) < 3";
		Atendimento atend = new Atendimento();
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					atend.setIdFuncionario(rs.getInt("fk_atendente"));
					
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		if(atend.getIdFuncionario() != 0)
			return atend;
		else
			return null;
	}
	
	
	public Atendimento startOnlineSupport(Atendimento atend) {
		Atendimento atendimento = new Atendimento();
		String sqlUpdate = "INSERT INTO `Atendimento` (`pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`) VALUES (default, ?, ?, ?);";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, 0);
			stm.setInt(2, atend.getIdFuncionario());
			stm.setInt(1, atend.getIdCliente());
						
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					
					atendimento.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			atendimento.setId(0);

		}
		return atendimento;
					
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PalavraChave pChave = new PalavraChave(61, "teste2");
		
		//PalavraChaveDAO dao = new PalavraChaveDAO();
		//dao.criar(pChave);
		//System.out.println("teste : " + dao.listarPchave().size());

	}
	
}




