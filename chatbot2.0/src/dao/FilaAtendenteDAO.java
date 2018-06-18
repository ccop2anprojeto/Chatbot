package dao;

import java.sql.Connection;
import java.sql.Date;
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
		String sqlSelect = "select fk_atendente from filaatendente where fk_atendente not in (select fk_funcionario from atendimento where status = 0 group by fk_funcionario having count(1) >= 3);";
		Atendimento atend = new Atendimento();
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					//melhorar método de filtrar atendentes dosponíveis. 
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
		
		String sqlUpdate = "INSERT INTO `Atendimento` (`pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`, `fk_filaCliente`, `status`, `data`) VALUES (default, ?, ?, ?, ?, ?, ?);";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, 1);
			stm.setInt(2, atend.getIdFuncionario());
			stm.setInt(3, atend.getIdCliente());
			stm.setInt(4, atend.getIdFilaCliente());
			stm.setInt(5, atend.getStatus());
			
			stm.setDate(6, atend.getData());
			
			System.out.println("DAO --------->"+ atend.getData().toString());
			//status 0 é atendimento aberto, 1 é atendimento fechado
						
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					
					atend.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			atend.setId(0);

		}
		return atend;
					
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PalavraChave pChave = new PalavraChave(61, "teste2");
		
		//PalavraChaveDAO dao = new PalavraChaveDAO();
		//dao.criar(pChave);
		//System.out.println("teste : " + dao.listarPchave().size());

	}
	
}




