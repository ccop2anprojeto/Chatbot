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
import model.PchaveHasResposta;
import model.Atendimento;
import model.Cliente;



public class AtendimentoDAO {	
	
	public Atendimento startOnlineSupport(Atendimento atend) {
		
		//String sqlUpdate = "INSERT INTO `Atendimento` (`pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`, `fk_filaCliente`, `status`) VALUES (default, ?, ?, ?, ?, ?);";
		String sqlUpdate = "UPDATE atendimento SET fk_funcionario = ? WHERE atendimento.pk_atendimento = ?;";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, atend.getIdFuncionario());
			stm.setInt(2, atend.getId());			
			//status 0 é atendimento aberto, 1 é atendimento fechado
						
			stm.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		if(atend.getId() != 0)
			return atend;
		else
			return null;
					
	}
	public Atendimento consolidateBotI(Atendimento atend) {
		
		//String sqlUpdate = "INSERT INTO `Atendimento` (`pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`, `fk_filaCliente`, `status`) VALUES (default, ?, ?, ?, ?, ?);";
		String sqlUpdate = "UPDATE atendimento SET botInteraction = ? WHERE atendimento.pk_atendimento = ?;";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, atend.getBotInteraction());
			stm.setInt(2, atend.getId());			
			//status 0 é atendimento aberto, 1 é atendimento fechado
						
			stm.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		if(atend.getId() != 0)
			return atend;
		else
			return null;
					
	}
	public Atendimento consolidateHumanI(Atendimento atend) {
		
		//String sqlUpdate = "INSERT INTO `Atendimento` (`pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`, `fk_filaCliente`, `status`) VALUES (default, ?, ?, ?, ?, ?);";
		String sqlUpdate = "UPDATE atendimento SET humanInteraction = ? WHERE atendimento.pk_atendimento = ?;";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, atend.getHumanInteraction());
			stm.setInt(2, atend.getId());			
			//status 0 é atendimento aberto, 1 é atendimento fechado
						
			stm.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		if(atend.getId() != 0)
			return atend;
		else
			return null;
					
	}
	public Atendimento searchAtend(int idC, int idFunc) {				
		Atendimento atend = new Atendimento();
		String sqlSelect = "SELECT `pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`, `fk_filaCliente`, `status`, `data`, `humanInteraction`, `botInteraction` FROM `atendimento` where fk_funcionario = ? and fk_cliente = ? and status = ? order by pk_atendimento DESC limit 1";
		
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
					atend.setData(rs.getDate("data"));
					atend.setHumanInteraction(rs.getInt("humanInteraction"));
					atend.setBotInteraction(rs.getInt("botInteraction"));
					
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
	public Object WeekConsolidator(String today, String sunday) {				
		
		String sqlSelect = "SELECT count(pk_atendimento) weekTotal FROM `atendimento` where data BETWEEN ? and ? having count(pk_atendimento);";
		Object weekTotal = null;
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, sunday);
			stm.setString(2, today);
			
			
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					
					weekTotal = rs.getInt("weekTotal");
					if(weekTotal != null) {
						 weekTotal = 0; 
					 }
					
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		
		return weekTotal;
	}
	public Object DailyConsolidator(String today) {				
		
		String sqlSelect = "SELECT count(pk_atendimento) dailyTotal FROM `atendimento` where data = ? having count(pk_atendimento);";
		Object dailyTotal = null;
		Object dayTotal = null;
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, today);
			
			
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					
					 dailyTotal = rs.getInt("dailyTotal");
					 
					 if(dailyTotal != null) {
						 dayTotal = dailyTotal; 
					 }else {
						 dayTotal = 0; 
					 }
					
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		
		return dayTotal;
	}
public Object MonthConsolidator(String firstDay, String currentDay) {				
		
		String sqlSelect = "SELECT count(pk_atendimento) monthTotal FROM `atendimento` where data BETWEEN ? and ? having count(pk_atendimento);";
		Object monthTotal = null;
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, firstDay);
			stm.setString(2, currentDay);
			
			
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					
					 monthTotal = rs.getInt("monthTotal");
					 
					 if(monthTotal != null) {
						 monthTotal = 0; 
					 }
					
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		
		return monthTotal;
	}
	public ArrayList<Atendimento> searchAll() {				
		
		ArrayList<Atendimento> list = new ArrayList<Atendimento>();
		
		String sqlSelect = "SELECT `pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`, `fk_filaCliente`, `status`, `data`, `humanInteraction`, `botInteraction` FROM `atendimento`;";
		// usando o try with resources do Java 7, que fecha o que abriu
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Atendimento atend = new Atendimento();
					atend.setId(rs.getInt("pk_atendimento"));
					atend.setIdFuncionario(rs.getInt("fk_funcionario"));
					atend.setIdCliente(rs.getInt("fk_cliente"));
					atend.setIdFilaCliente(rs.getInt("fk_filaCliente"));
					atend.setStatus(rs.getInt("status"));
					atend.setData(rs.getDate("data"));
					atend.setHumanInteraction(rs.getInt("humanInteraction"));
					atend.setBotInteraction(rs.getInt("botInteraction"));			
					list.add(atend);
				}
				
									
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return list;

	}
		
		//---------------------------------------------------
		
	/*	String sqlSelect = "SELECT `pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`, `fk_filaCliente`, `status`, `data`, `humanInteraction`, `botInteraction` FROM `atendimento`";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					atend.setId(rs.getInt("pk_atendimento"));
					atend.setIdFuncionario(rs.getInt("fk_funcionario"));
					atend.setIdCliente(rs.getInt("fk_cliente"));
					atend.setIdFilaCliente(rs.getInt("fk_filaCliente"));
					atend.setStatus(rs.getInt("status"));
					atend.setData(rs.getDate("data"));
					atend.setHumanInteraction(rs.getInt("humanInteraction"));
					atend.setBotInteraction(rs.getInt("botInteraction"));
					list.add(atend);
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return list;
	}*/
	public Atendimento searchAtend(int id) {				
		Atendimento atend = new Atendimento();
		atend.setId(id);
		String sqlSelect = "SELECT `pk_atendimento`, `fk_pergunta`, `fk_funcionario`, `fk_cliente`, `fk_filaCliente`, `status`, `data`, `humanInteraction`, `botInteraction` FROM `atendimento` where pk_atendimento = ?;";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);			
			
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					atend.setIdPergunta(rs.getInt("fk_pergunta"));
					atend.setIdFuncionario(rs.getInt("fk_funcionario"));
					atend.setIdCliente(rs.getInt("fk_cliente"));
					atend.setIdFilaCliente(rs.getInt("fk_filaCliente"));
					atend.setStatus(rs.getInt("status"));
					atend.setData(rs.getDate("data"));
					atend.setHumanInteraction(rs.getInt("humanInteraction"));
					atend.setBotInteraction(rs.getInt("botInteraction"));
					
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
		String sqlUpdate = "UPDATE atendimento SET status = ?, humanInteraction = ?, botInteraction = ?  WHERE atendimento.pk_atendimento = ?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, atend.getStatus());
			stm.setInt(2, atend.getHumanInteraction());
			stm.setInt(3, atend.getBotInteraction());
			stm.setInt(4, atend.getId());
						
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




