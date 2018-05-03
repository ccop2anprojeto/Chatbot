package model;

import java.util.ArrayList;


public class Consulta {
	
	public Consulta() {
		
	}
	
	public ArrayList<PalavraChave> getPchave() {
		PalavraChave pChave0 = new PalavraChave(1, "céu");
		PalavraChave pChave1 = new PalavraChave(2, "azul");
		PalavraChave pChave2 = new PalavraChave(3, "funcionamento");
		PalavraChave pChave3 = new PalavraChave(4, "data");
		PalavraChave pChave4 = new PalavraChave(5, "casamento");
		/*PalavraChave pChave5 = new PalavraChave(6, "civil");
		PalavraChave pChave6 = new PalavraChave(7, "horário");
		PalavraChave pChave7 = new PalavraChave(8, "funcionamento");
		PalavraChave pChave8 = new PalavraChave(9, "marcar");
		PalavraChave pChave9 = new PalavraChave(10, "ajuda");*/
		ArrayList<PalavraChave> db_pChave = new ArrayList<PalavraChave>();
		
		db_pChave.add(pChave0);
		db_pChave.add(pChave1);
		db_pChave.add(pChave2);
		db_pChave.add(pChave3);
		db_pChave.add(pChave4);
/*		db_pChave.add(pChave5);
		db_pChave.add(pChave6);
		db_pChave.add(pChave7);
		db_pChave.add(pChave8);
		db_pChave.add(pChave9);
	*/	
		
	//	System.out.println(db_pChave.get(0).getIdPchave());
		return db_pChave;
	}
	
	public ArrayList<PchaveHasResposta> searchRespInScore(){
		
		//essse metodo vai ter uma query, que vai olhar apenas paras o idResp, n deixando nenhum id repetido e depois verificando qual resp com maior score
		
		PchaveHasResposta db_Resp0 = new PchaveHasResposta(1, 1);
		PchaveHasResposta db_Resp1 = new PchaveHasResposta(1, 2);
		PchaveHasResposta db_Resp2 = new PchaveHasResposta(1, 3);		
				
		ArrayList<PchaveHasResposta> db_Resp = new ArrayList<PchaveHasResposta>();
		
		db_Resp.add(db_Resp0);
		db_Resp.add(db_Resp1);
		db_Resp.add(db_Resp2);
		
	
		return db_Resp;		
	}
	
	public ArrayList<PchaveHasResposta> getPchaveHasResp(){
		
		PchaveHasResposta pChaveHasResp0 = new PchaveHasResposta(1, 1);
		PchaveHasResposta pChaveHasResp1 = new PchaveHasResposta(1, 2);
		PchaveHasResposta pChaveHasResp2 = new PchaveHasResposta(1, 3);
		PchaveHasResposta pChaveHasResp3 = new PchaveHasResposta(2, 1);
		PchaveHasResposta pChaveHasResp4 = new PchaveHasResposta(2, 2);
		PchaveHasResposta pChaveHasResp5 = new PchaveHasResposta(2, 3);
		
		
		
		ArrayList<PchaveHasResposta> db_pChaveHasResp = new ArrayList<PchaveHasResposta>();
		
		db_pChaveHasResp.add(pChaveHasResp0);
		db_pChaveHasResp.add(pChaveHasResp1);
		db_pChaveHasResp.add(pChaveHasResp2);
		db_pChaveHasResp.add(pChaveHasResp3);
		db_pChaveHasResp.add(pChaveHasResp4);
		db_pChaveHasResp.add(pChaveHasResp5);		
	
		return db_pChaveHasResp;		
	}
	
	public ArrayList<Resposta> getRespostas(){
		
		Resposta resp0 = new Resposta(1, "A atmosfera atua como um prisma");
		Resposta resp1 = new Resposta(2, "É a cor preferida do criador");
		Resposta resp2 = new Resposta(3, "É um reflexo do mar");				
				
		ArrayList<Resposta> db_resp = new ArrayList<Resposta>();
		
		db_resp.add(resp0);
		db_resp.add(resp1);
		db_resp.add(resp2);
		
		return db_resp;
		
	}
	
}
