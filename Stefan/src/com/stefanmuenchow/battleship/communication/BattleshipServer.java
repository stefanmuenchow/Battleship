package com.stefanmuenchow.battleship.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.stefanmuenchow.battleship.field.Coordinate;

public class BattleshipServer {
	private Socket socket;
	private BufferedWriter out;
	private BufferedReader in;
	private final String playerName;
	
	public BattleshipServer(final String ip, final int port, final String playerName) {
		try {
			socket = new Socket(InetAddress.getByAddress(ip.getBytes()), port);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			throw new BattleshipServerException(e.getMessage());
		}
		
		this.playerName = playerName;
	}
	
	private String createMessage(String... elements) {
		StringBuffer sb = new StringBuffer();
		
		for (String e : elements) {
			sb.append(e).append(" ");
		}
		
		sb.setLength(sb.length() - 1);
		sb.append("\n");
		return sb.toString();
	}
	
	private void send(final String message) {
		try {
			out.write(message);
		} catch (IOException e) {
			throw new BattleshipServerException(e.getMessage());
		}
	}
	
	private String read() {
		try {
			String result = in.readLine();
			return result;
		} catch (IOException e) {
			throw new BattleshipServerException(e.getMessage());
		}
	}
	
	public boolean join(final String roomName) {
		send(createMessage(Messages.JOIN, roomName, playerName));
		String result = read();
		return result.startsWith(Messages.JOIN_OK);
	}
	
	public boolean lineup(final String shipPositions) {
		// TODO
		return false;
	}
	
	public void ready() {
		// TODO
	}
	
	public EServerResult shot(final Coordinate coordinate) {
		// TODO
		return EServerResult.Lost;
	}
	
	public void bye() {
		send(createMessage(Messages.BYE, playerName));
		read();
		
		try {
			socket.close();
		} catch (IOException e) {
			throw new BattleshipServerException(e.getMessage());
		}
	}
}
