package main;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Doctors;
import model.Nurse;
import model.Patient;
import utils.PatientPriorityBlockingQueue;

public class WaitingRoom implements Runnable {
	static final Logger LOGGER = Logger.getLogger(WaitingRoom.class.getName());

	PatientPriorityBlockingQueue patientPriorityBlockingQueue;
	Nurse nurse;
	Doctors doctors;

	public WaitingRoom() {
		this.patientPriorityBlockingQueue = new PatientPriorityBlockingQueue();
		this.nurse = new Nurse(this.patientPriorityBlockingQueue);
		this.doctors = new Doctors(this.nurse);
	}

	@Override
	public void run() {
		new Thread(this.doctors).start();
		new Thread(this.nurse).start();
		new Thread(new Patient(this.nurse)).start();
		while (true) {
			Random randomNumber = new Random();
			if (randomNumber.nextInt(3) == 0) {
				new Thread(new Patient(this.nurse)).start();
				LOGGER.log(Level.INFO, this.patientPriorityBlockingQueue.toString());

			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
