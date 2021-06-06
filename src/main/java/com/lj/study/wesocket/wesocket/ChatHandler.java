package com.lj.study.wesocket.wesocket;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ChatHandler extends TextWebSocketHandler {
  
  List<WebSocketSession> connections = new ArrayList<>();
  
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    super.afterConnectionEstablished(session);
    System.out.println(session.getRemoteAddress()+" 连接了..");
    connections.add(session);
  }
  
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    super.afterConnectionClosed(session, status);
    System.out.println(session.getRemoteAddress()+" 断开了..");
    connections.remove(session);
  }
  
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    super.handleTextMessage(session, message);
  
    System.out.println(message.getPayloadLength());
    System.out.println(message.getPayload());
    
    connections.forEach( c->{
      try {
        if( c!=null ){
          c.sendMessage( message );
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } );
    
  }
  
  public void send(Map params) {
    
    WebSocketMessage<String> webSocketMessage = new TextMessage(JSONObject.toJSONString(params));
    connections.forEach( c->{
      try {
        c.sendMessage( webSocketMessage );
      } catch (IOException e) {
        e.printStackTrace();
      }
    } );
    
  }
}
