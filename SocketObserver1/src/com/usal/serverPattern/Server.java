package com.usal.serverPattern;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Vector;

import java.io.IOException;
import java.io.*;

public class Server implements Observer {
	private Socket socket;

	private Vector clients;
	 ServerSocket ssocket; // Server Socket
	private StartServerThread sst; // inner class

	private ClientThread clientThread;

	/** Port number of Server. */
	private int port;
	private boolean listening; // status for listening
	public static void main(String[] args) {
		 Server s = new Server();
		 s.startServer();
		
	}
	public Server() {
		this.clients = new Vector();
		this.port = 2345; // default port
		this.listening = false;
		try {
			ssocket = new ServerSocket(this.port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void startServer() {
		if (!listening) {
			this.sst = new StartServerThread();
			this.sst.start();
			
			this.listening = true;
		}
	}

	public void stopServer() {
		if (this.listening) {
			this.sst.stopServerThread();
			// close all connected clients//

			java.util.Enumeration e = this.clients.elements();
			while (e.hasMoreElements()) {
				ClientThread ct = (ClientThread) e.nextElement();
				ct.stopClient();
			}
			this.listening = false;
		}
	}

	// observer interface//
	public void update(Observable observable, Object object) {
		// notified by observables, do cleanup here//
		this.clients.removeElement(observable);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private class StartServerThread extends Thread {
		private boolean listen;

		public StartServerThread() {
			this.listen = false;
		}

		public void run() {
			this.listen = true;
			try {

				

				while (this.listen) {
					// wait for client to connect//

					Server.this.socket = Server.this.ssocket.accept();
					System.out.println("Client connected");
					try {
						Server.this.clientThread = new ClientThread(Server.this.socket);
						Thread t = new Thread(Server.this.clientThread);
						Server.this.clientThread.addObserver(Server.this);
						Server.this.clients.addElement(Server.this.clientThread);
						t.start();
					} catch (IOException ioe) {
						// some error occured in ClientThread //
					}
				}
			} catch (IOException ioe) {
				// I/O error in ServerSocket//
				this.stopServerThread();
			}
		}

		public void stopServerThread() {
			try {
				Server.this.ssocket.close();
			} catch (IOException ioe) {
				// unable to close ServerSocket
			}

			this.listen = false;
		}
	}
}
