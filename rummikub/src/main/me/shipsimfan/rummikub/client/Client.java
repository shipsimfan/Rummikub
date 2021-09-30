package me.shipsimfan.rummikub.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import me.shipsimfan.rummikub.Config;

public class Client {
	private final Socket socket;
	private final DataInputStream input;
	private final DataOutputStream output;

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
		output = new DataOutputStream(socket.getOutputStream());

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
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			// Get message
			String message = input.readUTF();

			// Check for command
			if (message.equals("end"))
				return;
			else if (message.equals("play")) {
				System.out.print("Enter a tile to play (Leave blank for end turn): ");
				String tile = reader.readLine();
				output.writeUTF(tile);

				if (!tile.equals("") && !tile.equals("end")) {
					System.out.print("Enter a meld to play it to (Leave blank for new): ");
					String meld = reader.readLine();
					output.writeUTF(meld);
				}
			} else // Display otherwise
				System.out.println(message);
		}
	}
}
