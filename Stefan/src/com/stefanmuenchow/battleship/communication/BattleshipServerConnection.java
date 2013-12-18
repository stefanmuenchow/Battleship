package com.stefanmuenchow.battleship.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class BattleshipServerConnection {
	
	private final Socket socket;
	private BufferedWriter out;
	private BufferedReader in;
	
	public BattleshipServerConnection(final String ip, final int port, String encoding) {
		try {
			socket = new Socket(InetAddress.getByAddress(ip.getBytes()), port);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), encoding));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), encoding));
		} catch (IOException e) {
			throw new BattleshipServerException(e.getMessage());
		}
	}
	
	public String send(final String message) {
		try {
			out.write(message);
			
			String result = in.readLine();
			return result;
		} catch (IOException e) {
			throw new BattleshipServerException(e.getMessage());
		}
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			throw new BattleshipServerException(e.getMessage());
		}
	}
}
