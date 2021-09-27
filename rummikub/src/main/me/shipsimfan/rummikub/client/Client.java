package me.shipsimfan.rummikub.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import me.shipsimfan.rummikub.Config;

public class Client {
	private final Socket socket;
	private final DataInputStream input;

	public static void main(String[] args) {
		// Get IP Address
		String ipAddress = "localhost";
		if (args.length >= 2)
			ipAddress = args[1];

		// Run client
		try {
			new Client(ipAddress, Config.PORT);
		} catch (IOException e) {
			System.err.println("Network error: " + e);
			System.exit(1);
		}
	}

	public Client(String ipAddress, int port) throws IOException {
		// Join game
		socket = new Socket(ipAddress, port);

		input = new DataInputStream(socket.getInputStream());

		// Wait for players to connect
		while (true) {
			String response = input.readUTF();

			if (response.equals("begin"))
				break;

			System.out.println(response);
		}

		// Run game
		runGame();

		// Disconnect
		socket.close();
	}

	private void runGame() throws IOException {
		while (true) {
			// Get message
			String message = input.readUTF();
			
			// Check for command
			if (message.equals("end"))
				return;
			else // Display otherwise
				System.out.println(message);
		}
	}
}
