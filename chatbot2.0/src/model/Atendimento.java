package model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Atendimento {	
	private int id;
	private int idPergunta;
	private int idFuncionario;
	private int idCliente;
	private int idFilaCliente;
	private int status;
	private Date data;

	public Atendimento() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdFilaCliente() {
		return idFilaCliente;
	}

	public void setIdFilaCliente(int idFilaCliente) {
		this.idFilaCliente = idFilaCliente;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Date instanceData() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		return date;
	}
	
		
}
