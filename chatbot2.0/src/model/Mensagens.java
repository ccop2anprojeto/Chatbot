package model;

public class Mensagens {	
	private int id;
	private int id_funcionario;
	private int id_cliente;
	private String mensagem;
	private int time;
	private int recebida;
	
	public Mensagens() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getRecebida() {
		return recebida;
	}

	public void setRecebida(int recebida) {
		this.recebida = recebida;
	}
		
}
