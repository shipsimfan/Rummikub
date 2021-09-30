package me.shipsimfan.rummikub.server;

import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.junit.Test;

import me.shipsimfan.rummikub.Config;

public class ServerUITest {
	public class ServerThread extends Thread {
		public void run() {
			Server.main(new String[] { "server", "O1,O2,O3,O4", "R12,B12,O12,R1,R2,R3,R4", "R11,R12,R13,G1,G2,G3,G4",
					"R13,B13,G13,G2,R2,O2,O1" });
		}
	}

	@Test
	public void ServerUITest() throws InterruptedException, IOException {
		// Start server
		ServerThread server = new ServerThread();
		server.start();
		Thread.sleep(100);

		// Connect player 1
		Socket client1 = new Socket("localhost", Config.PORT);
		DataInputStream input1 = new DataInputStream(client1.getInputStream());
		DataOutputStream output1 = new DataOutputStream(client1.getOutputStream());

		assertEquals("You are player 1", input1.readUTF());
		assertEquals("Currently 1/3 players connected", input1.readUTF());

		// Connect player 2
		Socket client2 = new Socket("localhost", Config.PORT);
		DataInputStream input2 = new DataInputStream(client2.getInputStream());
		DataOutputStream output2 = new DataOutputStream(client2.getOutputStream());

		assertEquals("You are player 2", input2.readUTF());
		assertEquals("Player 2 has connected", input1.readUTF());

		assertEquals("Currently 2/3 players connected", input2.readUTF());
		assertEquals("Currently 2/3 players connected", input1.readUTF());

		// Connect player 3
		Socket client3 = new Socket("localhost", Config.PORT);
		DataInputStream input3 = new DataInputStream(client3.getInputStream());
		DataOutputStream output3 = new DataOutputStream(client3.getOutputStream());

		assertEquals("You are player 3", input3.readUTF());
		assertEquals("Player 3 has connected", input2.readUTF());
		assertEquals("Player 3 has connected", input1.readUTF());

		// Start of game
		assertEquals("begin", input1.readUTF());
		assertEquals("begin", input2.readUTF());
		assertEquals("begin", input3.readUTF());

		assertEquals("The game is beginning!", input1.readUTF());
		assertEquals("The game is beginning!", input2.readUTF());
		assertEquals("The game is beginning!", input3.readUTF());

		// Play round 1
		assertEquals("", input1.readUTF());
		assertEquals("", input2.readUTF());
		assertEquals("", input3.readUTF());
		assertEquals("Player 1's turn", input1.readUTF());
		assertEquals("Player 1's turn", input2.readUTF());
		assertEquals("Player 1's turn", input3.readUTF());

		assertEquals("Table: ", input1.readUTF());
		assertEquals("Your hand: R1,R2,R3,R4,R12,B12,O12", input1.readUTF());
		assertEquals("play", input1.readUTF());
		output1.writeUTF("");

		// Verify round 1
		assertEquals("Board: ", input1.readUTF());
		assertEquals("Board: ", input2.readUTF());
		assertEquals("Board: ", input3.readUTF());

		// Play round 2
		assertEquals("", input1.readUTF());
		assertEquals("", input2.readUTF());
		assertEquals("", input3.readUTF());
		assertEquals("Player 2's turn", input1.readUTF());
		assertEquals("Player 2's turn", input2.readUTF());
		assertEquals("Player 2's turn", input3.readUTF());

		assertEquals("Table: ", input2.readUTF());
		assertEquals("Your hand: R11,R12,R13,G1,G2,G3,G4", input2.readUTF());
		assertEquals("play", input2.readUTF());
		output2.writeUTF("R11");
		output2.writeUTF("");

		assertEquals("Table: {*R11}", input2.readUTF());
		assertEquals("Your hand: R12,R13,G1,G2,G3,G4", input2.readUTF());
		assertEquals("play", input2.readUTF());
		output2.writeUTF("R12");
		output2.writeUTF("0");

		assertEquals("Table: {*R11,*R12}", input2.readUTF());
		assertEquals("Your hand: R13,G1,G2,G3,G4", input2.readUTF());
		assertEquals("play", input2.readUTF());
		output2.writeUTF("R13");
		output2.writeUTF("0");

		assertEquals("Table: {*R11,*R12,*R13}", input2.readUTF());
		assertEquals("Your hand: G1,G2,G3,G4", input2.readUTF());
		assertEquals("play", input2.readUTF());
		output2.writeUTF("");

		// Verify round 2
		assertEquals("Board: {*R11,*R12,*R13}", input1.readUTF());
		assertEquals("Board: {*R11,*R12,*R13}", input2.readUTF());
		assertEquals("Board: {*R11,*R12,*R13}", input3.readUTF());

		// Play round 3
		assertEquals("", input1.readUTF());
		assertEquals("", input2.readUTF());
		assertEquals("", input3.readUTF());
		assertEquals("Player 3's turn", input1.readUTF());
		assertEquals("Player 3's turn", input2.readUTF());
		assertEquals("Player 3's turn", input3.readUTF());

		assertEquals("Table: {R11,R12,R13}", input3.readUTF());
		assertEquals("Your hand: R2,R13,B13,G2,G13,O1,O2", input3.readUTF());
		assertEquals("play", input3.readUTF());
		output3.writeUTF("R13");
		output3.writeUTF("");

		assertEquals("Table: {R11,R12,R13},{*R13}", input3.readUTF());
		assertEquals("Your hand: R2,B13,G2,G13,O1,O2", input3.readUTF());
		assertEquals("play", input3.readUTF());
		output3.writeUTF("B13");
		output3.writeUTF("1");

		assertEquals("Table: {R11,R12,R13},{*R13,*B13}", input3.readUTF());
		assertEquals("Your hand: R2,G2,G13,O1,O2", input3.readUTF());
		assertEquals("play", input3.readUTF());
		output3.writeUTF("G13");
		output3.writeUTF("1");

		assertEquals("Table: {R11,R12,R13},{*R13,*B13,*G13}", input3.readUTF());
		assertEquals("Your hand: R2,G2,O1,O2", input3.readUTF());
		assertEquals("play", input3.readUTF());
		output3.writeUTF("G2");
		output3.writeUTF("");

		assertEquals("Table: {R11,R12,R13},{*R13,*B13,*G13},{*G2}", input3.readUTF());
		assertEquals("Your hand: R2,O1,O2", input3.readUTF());
		assertEquals("play", input3.readUTF());
		output3.writeUTF("R2");
		output3.writeUTF("2");

		assertEquals("Table: {R11,R12,R13},{*R13,*B13,*G13},{*G2,*R2}", input3.readUTF());
		assertEquals("Your hand: O1,O2", input3.readUTF());
		assertEquals("play", input3.readUTF());
		output3.writeUTF("O2");
		output3.writeUTF("2");

		assertEquals("Table: {R11,R12,R13},{*R13,*B13,*G13},{*G2,*R2,*O2}", input3.readUTF());
		assertEquals("Your hand: O1", input3.readUTF());
		assertEquals("play", input3.readUTF());
		output3.writeUTF("");

		// Verify round 3
		assertEquals("Board: {R11,R12,R13},{*R13,*B13,*G13},{*G2,*R2,*O2}", input1.readUTF());
		assertEquals("Board: {R11,R12,R13},{*R13,*B13,*G13},{*G2,*R2,*O2}", input2.readUTF());
		assertEquals("Board: {R11,R12,R13},{*R13,*B13,*G13},{*G2,*R2,*O2}", input3.readUTF());

		// Play round 4
		assertEquals("", input1.readUTF());
		assertEquals("", input2.readUTF());
		assertEquals("", input3.readUTF());
		assertEquals("Player 1's turn", input1.readUTF());
		assertEquals("Player 1's turn", input2.readUTF());
		assertEquals("Player 1's turn", input3.readUTF());

		assertEquals("Table: {R11,R12,R13},{R13,B13,G13},{G2,R2,O2}", input1.readUTF());
		assertEquals("Your hand: R1,R2,R3,R4,R12,B12,O4,O12", input1.readUTF());
		assertEquals("play", input1.readUTF());
		output1.writeUTF("R12");
		output1.writeUTF("");

		assertEquals("Table: {R11,R12,R13},{R13,B13,G13},{G2,R2,O2},{*R12}", input1.readUTF());
		assertEquals("Your hand: R1,R2,R3,R4,B12,O4,O12", input1.readUTF());
		assertEquals("play", input1.readUTF());
		output1.writeUTF("B12");
		output1.writeUTF("3");

		assertEquals("Table: {R11,R12,R13},{R13,B13,G13},{G2,R2,O2},{*R12,*B12}", input1.readUTF());
		assertEquals("Your hand: R1,R2,R3,R4,O4,O12", input1.readUTF());
		assertEquals("play", input1.readUTF());
		output1.writeUTF("O12");
		output1.writeUTF("3");

		assertEquals("Table: {R11,R12,R13},{R13,B13,G13},{G2,R2,O2},{*R12,*B12,*O12}", input1.readUTF());
		assertEquals("Your hand: R1,R2,R3,R4,O4", input1.readUTF());
		assertEquals("play", input1.readUTF());
		output1.writeUTF("");

		// Verify round 4
		assertEquals("Board: {R11,R12,R13},{R13,B13,G13},{G2,R2,O2},{*R12,*B12,*O12}", input1.readUTF());
		assertEquals("Board: {R11,R12,R13},{R13,B13,G13},{G2,R2,O2},{*R12,*B12,*O12}", input2.readUTF());
		assertEquals("Board: {R11,R12,R13},{R13,B13,G13},{G2,R2,O2},{*R12,*B12,*O12}", input3.readUTF());

		// End server in round 5
		assertEquals("", input1.readUTF());
		assertEquals("", input2.readUTF());
		assertEquals("", input3.readUTF());
		assertEquals("Player 2's turn", input1.readUTF());
		assertEquals("Player 2's turn", input2.readUTF());
		assertEquals("Player 2's turn", input3.readUTF());

		assertEquals("Table: {R11,R12,R13},{R13,B13,G13},{G2,R2,O2},{R12,B12,O12}", input2.readUTF());
		assertEquals("Your hand: G1,G2,G3,G4", input2.readUTF());
		assertEquals("play", input2.readUTF());
		output2.writeUTF("end");

		// Close sockets
		client1.close();
		client2.close();
		client3.close();
	}

}
