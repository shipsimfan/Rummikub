package me.shipsimfan.rummikub.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	private final Socket socket;
	private final DataOutputStream output_stream;

	public Client(Socket socket) throws IOException {
		output_stream = new DataOutputStream(socket.getOutputStream());
		this.socket = socket;
	}

	public void send(String message) throws IOException {
		output_stream.writeUTF(message);
	}

	public void close() throws IOException {
		socket.close();
	}

	public boolean isClosed() {
		return socket.isClosed();
	}
}
