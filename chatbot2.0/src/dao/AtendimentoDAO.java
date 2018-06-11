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
import model.Mensagens;
import model.Atendimento;
import model.Cliente;



public class AtendimentoDAO {	
	
	public Atendimento startOnlineSupport(Atendimento atend) {
		
		String sqlUpdate = "INSERT INTO `Atendimento` (`pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`, `fk_filaCliente`, `status`) VALUES (default, ?, ?, ?, ?, ?);";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, 1);
			stm.setInt(2, atend.getIdFuncionario());
			stm.setInt(3, atend.getIdCliente());
			stm.setInt(4, atend.getIdFilaCliente());
			stm.setInt(5, atend.getStatus());
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
	
	public Atendimento searchAtend(int idC, int idFunc) {				
		Atendimento atend = new Atendimento();
		String sqlSelect = "SELECT `pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`, `fk_filaCliente`, `status` FROM `atendimento` where fk_funcionario = ? and fk_cliente = ? and status = ? order by pk_atendimento DESC limit 1";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, idFunc);
			stm.setInt(2, idC);
			stm.setInt(3, 0);
			
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					atend.setId(rs.getInt("pk_atendimento"));
					atend.setIdFuncionario(rs.getInt("fk_funcionario"));
					atend.setIdCliente(rs.getInt("fk_cliente"));
					atend.setIdFilaCliente(rs.getInt("fk_filaCliente"));
					atend.setStatus(rs.getInt("status"));
					
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		if(atend.getId() != 0)
			return atend;
		else
			return null;
	}
	public boolean finalizeService(Atendimento atend) {
		
		atend.setStatus(1);
		String sqlUpdate = "UPDATE atendimento SET status = ? WHERE atendimento.pk_atendimento = ?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, atend.getStatus());
			stm.setInt(2, atend.getId());
						
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




