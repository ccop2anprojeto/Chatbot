package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.ChatBot;


public class chatbotDAO2 {
	
	ChatBot chatbot = new ChatBot();
	
	
	public int criar(ChatBot chatbot) {
		String sqlInsert = "INSERT INTO voluntario (pergunta) VALUES (?);";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, chatbot.getPergunta());
					
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					chatbot.setIdChatbot(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chatbot.getIdChatbot();
	}
	
	
	public void atualizar() {
		String sqlUpdate = "UPDATE voluntario SET nome = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, cep = ?, telefoneOne = ?, telefoneTwo = ?, email = ?, nascimento = ?, cpf = ?, genero = ?, estadoCivil = ?, areaOne = ?, areaTwo = ? WHERE voluntario.id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, voluntario.getNome());
			stm.setString(2, voluntario.getRua());
			stm.setString(3, voluntario.getNumero());
			stm.setString(4, voluntario.getBairro());
			stm.setString(5, voluntario.getCidade());			
			stm.setString(6, voluntario.getEstado());
			stm.setString(7, voluntario.getCep());
			stm.setString(8, voluntario.getTelefoneOne());
			stm.setString(9, voluntario.getTelefoneTwo());
			stm.setString(10, voluntario.getEmail());
			stm.setString(11, voluntario.getNascimento());
			stm.setString(12, voluntario.getCpf());
			stm.setString(13, voluntario.getGenero());
			stm.setString(14, voluntario.getEstadoCivil());	
			stm.setString(15, voluntario.getAreaOne());	
			stm.setString(16, voluntario.getAreaTwo());	
			stm.setInt(17, voluntario.getId());
			
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		String sqlDelete = "DELETE FROM voluntario WHERE voluntario.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Voluntario carregar(int id) {
		Voluntario voluntario = new Voluntario();
		voluntario.setId(id);
		String sqlSelect = "SELECT nome, rua, numero, bairro, cidade, estado, cep, telefoneOne, telefoneTwo, email, nascimento, cpf, genero, estadoCivil, areaOne, areaTwo FROM voluntario WHERE voluntario.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, voluntario.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					voluntario.setNome(rs.getString("nome"));
					voluntario.setRua(rs.getString("rua"));
					voluntario.setNumero(rs.getString("numero"));
					voluntario.setBairro(rs.getString("bairro"));
					voluntario.setCidade(rs.getString("cidade"));	
					voluntario.setEstado(rs.getString("estado"));
					voluntario.setCep(rs.getString("cep"));
					voluntario.setTelefoneOne(rs.getString("telefoneOne"));
					voluntario.setTelefoneTwo(rs.getString("telefoneTwo"));
					voluntario.setEmail(rs.getString("email"));
					voluntario.setNascimento(rs.getString("nascimento"));
					voluntario.setCpf(rs.getString("cpf"));
					voluntario.setGenero(rs.getString("genero"));
					voluntario.setEstadoCivil(rs.getString("estadoCivil"));
					voluntario.setAreaOne(rs.getString("areaOne"));
					voluntario.setAreaTwo(rs.getString("areaTwo"));
					
				} else {					
					voluntario.setNome(rs.getString("Não Informado"));
					voluntario.setRua(rs.getString("Não Informado"));
					voluntario.setNumero(rs.getString("Não Informado"));
					voluntario.setBairro(rs.getString("Não Informado"));
					voluntario.setCidade(rs.getString("Não Informado"));	
					voluntario.setEstado(rs.getString("Não Informado"));
					voluntario.setCep(rs.getString("Não Informado"));
					voluntario.setTelefoneOne(rs.getString("Não Informado"));
					voluntario.setTelefoneTwo(rs.getString("Não Informado"));
					voluntario.setEmail(rs.getString("Não Informado"));
					voluntario.setNascimento(rs.getString("Não Informado"));
					voluntario.setCpf(rs.getString("Não Informado"));
					voluntario.setGenero(rs.getString("Não Informado"));
					voluntario.setEstadoCivil(rs.getString("Não Informado"));
					voluntario.setAreaOne(rs.getString("Não Informado"));
					voluntario.setAreaTwo(rs.getString("Não Informado"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return voluntario;
	}
	
	public ArrayList<Voluntario> listarVoluntario() {
		Voluntario voluntario;
		ArrayList<Voluntario> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, cidade, estado, telefoneOne, email, areaOne, cpf FROM voluntario";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					voluntario = new Voluntario();
					voluntario.setId(rs.getInt("voluntario.id"));
					voluntario.setNome(rs.getString("nome"));
					voluntario.setCidade(rs.getString("cidade"));					
					voluntario.setEstado(rs.getString("estado"));
					voluntario.setTelefoneOne(rs.getString("telefoneOne"));
					voluntario.setEmail(rs.getString("email"));
					voluntario.setAreaOne(rs.getString("areaOne"));
					voluntario.setCpf(rs.getString("cpf"));					
					
					lista.add(voluntario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	public ArrayList<Voluntario> listarVoluntario(String chave) {
		Voluntario voluntario;
		ArrayList<Voluntario> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, cidade, estado, telefoneOne, email, areaOne, cpf FROM voluntario where upper(nome) like ? or cpf like ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, "%" + chave.toUpperCase() + "%");
			stm.setString(2, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					voluntario = new Voluntario();
					voluntario.setId(rs.getInt("voluntario.id"));
					voluntario.setNome(rs.getString("nome"));
					voluntario.setCidade(rs.getString("cidade"));					
					voluntario.setEstado(rs.getString("estado"));
					voluntario.setTelefoneOne(rs.getString("telefoneOne"));
					voluntario.setEmail(rs.getString("email"));
					voluntario.setAreaOne(rs.getString("areaOne"));
					voluntario.setCpf(rs.getString("cpf"));					
					
					lista.add(voluntario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	public ArrayList<Voluntario> listarVoluntariosPossiveis(int idProjeto){
		Voluntario voluntario;
		ArrayList<Voluntario> lista = new ArrayList<>();
		String sqlSelect = "SELECT * FROM VOLUNTARIO WHERE ID IN (SELECT DISTINCT IDVOLUNTARIO FROM DISPONILIDADEVOLUNTARIO WHERE (DIA, PERIODO) IN (SELECT DIA, PERIODO FROM disponibilidadeprojeto WHERE IDPROJETO = ? AND ID NOT IN (SELECT DISTINCT IDdisponibilidadeprojeto FROM PROJETOVOLUNTARIO)) AND ID NOT IN (SELECT IDDISPONIBILIDADEVOLUNTARIO FROM PROJETOVOLUNTARIO));";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, idProjeto);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					voluntario = new Voluntario();
					voluntario.setId(rs.getInt("id"));
					voluntario.setNome(rs.getString("nome"));
					voluntario.setCidade(rs.getString("cidade"));					
					voluntario.setEstado(rs.getString("estado"));
					voluntario.setTelefoneOne(rs.getString("telefoneOne"));
					voluntario.setEmail(rs.getString("email"));
					voluntario.setAreaOne(rs.getString("areaOne"));
					voluntario.setCpf(rs.getString("cpf"));					
					
					lista.add(voluntario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	
	public ArrayList<Voluntario> listarMeusVoluntarios(int idProjeto){
		Voluntario voluntario;
		ArrayList<Voluntario> lista = new ArrayList<>();
		String sqlSelect = "select * from voluntario where id in (SELECT DISTINCT DV.IDVOLUNTARIO FROM PROJETOVOLUNTARIO P INNER JOIN DISPONILIDADEVOLUNTARIO DV ON P.idDisponibilidadeVoluntario = DV.ID INNER JOIN DISPONIBILIDADEPROJETO DP ON P.idDisponibilidadeprojeto = DP.ID where dp.idProjeto = ?);";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, idProjeto);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					voluntario = new Voluntario();
					voluntario.setId(rs.getInt("id"));
					voluntario.setNome(rs.getString("nome"));
					voluntario.setCidade(rs.getString("cidade"));					
					voluntario.setEstado(rs.getString("estado"));
					voluntario.setTelefoneOne(rs.getString("telefoneOne"));
					voluntario.setEmail(rs.getString("email"));
					voluntario.setAreaOne(rs.getString("areaOne"));
					voluntario.setCpf(rs.getString("cpf"));					
					
					lista.add(voluntario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}

}



