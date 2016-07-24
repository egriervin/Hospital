package main;

public class Main {

	public static void main(String[] args) {
		WaitingRoom waitingRoom = new WaitingRoom();
		new Thread(waitingRoom).start();
	}
}
