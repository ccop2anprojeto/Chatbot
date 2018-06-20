package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.AtendimentoDAO;
import dao.ClienteDAO;
import model.Atendimento;
import model.Cliente;

public class AtendimentoService {
	
	AtendimentoDAO dao = new AtendimentoDAO();
	
	public Atendimento searchAtend(int idC, int idFunc){
		return dao.searchAtend(idC, idFunc);
	}
	public boolean finalizeService(Atendimento atend){
		return dao.finalizeService(atend);
	}
	public Atendimento startOnlineSupport(Atendimento atend) {
		return dao.startOnlineSupport(atend);
	}
	public Atendimento searchAtend(int idAtend) {
		return dao.searchAtend(idAtend);
	}
	public ArrayList searchAll() {
		return dao.searchAll();
	}
	public Object WeekConsolidator(String todayFormat, String sundayFormat) {
		return dao.WeekConsolidator(todayFormat, sundayFormat);
	}
	public Object DailyConsolidator(String todayFormat) {
		return dao.DailyConsolidator(todayFormat);
	}
	public Object MonthConsolidator(String firstDay, String currentDay) {
		return dao.WeekConsolidator(firstDay, currentDay);
	}
	
}
