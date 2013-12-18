/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket.client;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author Ernest
 */
@ClientEndpoint
public class WebSocketClientTry  {

    private static CountDownLatch countDownLatch;

    public WebSocketClientTry() {
    }

     @OnMessage
    public void onMessage(PongMessage message, Session session) {
        try {
            System.out.println("Message Recieved >>>>>>>>>>>>>>>>>>" + message.toString());
        } catch (Exception e) {
        }
    }
    
     @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        try {
            session.getBasicRemote().sendText("start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnError
    public void error(Session session, Throwable t)
    {
        System.out.println("Error.............."+t.toString()); 
    }

    public static void main(String[] args) {
        try {
            String serverUrl = "ws://localhost:8080/gms-tws/twsMarketFeeds";
            countDownLatch = new CountDownLatch(1);
            WebSocketClientTry webSocketClient = new WebSocketClientTry();
            WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
            webSocketContainer.connectToServer(WebSocketClientTry.class, URI.create(serverUrl));
            //clientManager.connectToServer(WebSocketClientTry.class, new URI(serverUrl)); 
            countDownLatch.wait();
        } catch (Exception ex) {
            Logger.getLogger(WebSocketClientTry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
