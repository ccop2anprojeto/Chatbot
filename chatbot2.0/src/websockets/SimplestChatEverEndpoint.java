package websockets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Andrew Ribeiro
 */
@ServerEndpoint(value = "/talk")
public class SimplestChatEverEndpoint {

    /*� necess�rio salvar todas as sess�es em algo que possa ser enxergado
      por toda a aplica��o.
      Como este � um exemplo minimalista, vamos usar uma static global variable.*/
    public final static Set sessions = new HashSet();

    /**
     * Este m�todo � executado quando um novo usu�rio abre uma conex�o websocket
     * com este endpoint ("/talk").
     *
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session) {
        /*Adicionando a nova sess�o o conjunto de sess�es.*/
        sessions.add(session);
    }

    /**
     * Este m�todo � executado quando o usu�rio fecha a conex�o com o websocket
     * neste endpoint ("/talk") Isso pode ocorrer por diversas formas: Usu�rio
     * fechou ou atualizou a p�gina contendo o script conectado no endpoint;
     * Usu�rio fechou explicitamente a conex�o com o m�todo JS close().
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        /*J� que o usu�rio est� fechando conex�o,
          vamos retira-lo do conjunto de sess�es ativas.*/
        sessions.remove(session);
    }

    /**
     * Este m�todo � executado quando o endpoint recebe uma mensagem vindo do
     * cliente; O m�todo ent�o replica a mensagem para todas as demais sess�es.
     *
     * @param message
     * @param session
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        /*Isso � uma mensagem vindo diretamente do
          cliente atrav�s de um canal TCP.
          N�o h� nenhuma http request comum e sim
          uma completa mensagem atrav�s do socket.
            
          Vamos replicar essa mensagem para todas
          as sess�es ativas.*/
        for (Object s : sessions) {
            Session sn = (Session) s;
            sn.getBasicRemote().sendText(message);
        }
    }
}