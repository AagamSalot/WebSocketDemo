package com.demo.websocket;


import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.json.JsonObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/abc")
public class WebSocketDemo {


    private HttpClient client;
    private RequestOptions requestOptions;

    @OnOpen
    public void onOpen(Session session){
        System.out.println("session open="+session.getId());
        onMessage("Connection request from client 2",session);
        HttpClientOptions httpClientOptions = new HttpClientOptions();

    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("Message received from client"+ message);
        System.out.println("sessions=");
//        session.getOpenSessions().stream().forEach(x->{
//            System.out.println(x.getId());
//        });
//        System.out.println("message=");
//        session.getMessageHandlers().forEach(x->{
//            System.out.println(x.toString());
//        });
        try {
           session.getBasicRemote().sendText("Message 2 from server "+Math.random());
           Thread.sleep(2000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @OnClose
    public void onClose(Session session){
        System.out.println("session got closed....");
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error - " + t.getMessage());

    }

    public void httpConfigStore(Vertx vertx, JsonObject configuration) {
        String host = configuration.getString("host");
        int port = configuration.getInteger("port", 80);
        String path = configuration.getString("path", "/");
        long timeout = configuration.getLong("timeout", 3000L);
        boolean followRedirects = configuration.getBoolean("followRedirects", false);

        HttpClientOptions httpClientOptions = new HttpClientOptions();
        httpClientOptions.setDefaultHost(host);
        httpClientOptions.setDefaultPort(port);
        httpClientOptions.setSsl(true);


    }


}
