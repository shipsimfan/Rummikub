package me.shipsimfan.rummikub.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	private final Socket socket;
	private final DataOutputStream output_stream;
	private final DataInputStream input_stream;

	public Client(Socket socket) throws IOException {
		output_stream = new DataOutputStream(socket.getOutputStream());
		input_stream = new DataInputStream(socket.getInputStream());
		this.socket = socket;
	}

	public void send(String message) throws IOException {
		output_stream.writeUTF(message);
	}
	
	public String read() throws IOException {
		return input_stream.readUTF();
	}

	public void close() throws IOException {
		socket.close();
	}

	public boolean isClosed() {
		return socket.isClosed();
	}
}
