package model;

public class Mensagens {	
	private int id;
	private int id_de;
	private int id_para;
	private String mensagem;
	private int time;
	private int recebida;
	
	public int getId_de() {
		return id_de;
	}

	public void setId_de(int id_de) {
		this.id_de = id_de;
	}

	public int getId_para() {
		return id_para;
	}

	public void setId_para(int id_para) {
		this.id_para = id_para;
	}

	public Mensagens() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
