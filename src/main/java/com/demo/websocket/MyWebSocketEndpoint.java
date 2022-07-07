package com.demo.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Indicates that this class is a websocket endpoint with URL "/server-endpoint"
 */
@ServerEndpoint("/server-endpoint")
public class MyWebSocketEndpoint {

	Session session;
	static int counter = 0;

	/**
	 * Container calls this method when browser connects to this endpoint.
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Session Open [" + session.getId() + "]");
		this.session = session;
		onMessage("Connection request from client ",session);
		// Start a separate thread which will send messages back to browser.
//		new Thread(new ServerToBrowserMessageSender(session)).start();
	}

	/**
	 * Container calls this method when websocket connection is closed by browser or
	 * server.
	 */
	@OnClose
	public void onClose(Session session) {
		System.out.println("Session Close [" + session.getId() + "]");
	}

	/**
	 * Process message received from browser.
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message received [" + session.getId() + "] Message=" + message);
		try {
			session.getBasicRemote().sendText("message from server "+counter++);
			Thread.sleep(2000);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Container calls this method when there is an error in websocket
	 * communication.
	 */
	@OnError
	public void onError(Throwable t) {
		System.out.println("Error - " + t.getMessage());
	}
}

/**
 * This is a custom thread which sends message to browser every 2000
 * milliseconds.
 */
//class ServerToBrowserMessageSender {
//
//	private int count = 0;
//	private Session session;
//
//	public ServerToBrowserMessageSender(Session session) {
//		this.session = session;
//	}

//	@Override
//	public void run() {
//
//		while (count < 5000) {
//			count++;
//			try {
//				// Send message to browser ever 2000 milliseconds.
//				Thread.sleep(2000);
//				session.getBasicRemote()
//						.sendText("[Server -> Browser] A Message from server to browser. Count = " + count);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}

