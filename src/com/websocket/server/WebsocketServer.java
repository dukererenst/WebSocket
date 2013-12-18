/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.websocket.server;

import java.io.Serializable;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.glassfish.tyrus.container.grizzly.GrizzlyEngine;
import org.glassfish.tyrus.server.Server;
import org.glassfish.tyrus.spi.TyrusContainer;

/**
 *
 * @author Ernest
 */
@ServerEndpoint(value = "/trysample")
public class WebsocketServer  implements Serializable{

    public WebsocketServer() {
    }
    
    @OnOpen
    public void onOpen(Session session)
    {
        System.out.println("Connection Recieved >>>>>>>>>>>>>>>>>>>>>>>>"+session.getId());   
    }
    
    @OnMessage
    public String onMessage(String message, Session session)
    {
        System.out.println("Message Received >>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+message); 
        return message;
    }
    
    
    public static void main(String[] args) {
       runServer();
    }
    
    public static void runServer()
    {
   
        Server server ;
       
        try {
            server = new Server(WebsocketServer.class);
             TyrusContainer container = new GrizzlyEngine();
             //server.getServerContainer().
            // TyrusEndpoint endpoint = 
            // server.getServerContainer().connectToServer(WebsocketServer.class, new URI("/trysample"));
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
           //server.stop();
        }
    }

   
}
