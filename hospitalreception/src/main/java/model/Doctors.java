/**
 * 
 */
package model;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ervin
 *
 */
public class Doctors extends Person implements Runnable {
	static final Logger LOGGER = Logger.getLogger(Doctors.class.getName());

	Nurse nurse;

	public Doctors(Nurse nurse) {
		this.nurse = nurse;
	}

	public Patient getTheNextPatient() {
		return nurse.pollPatient();
	}

	@Override
	public void run() {
		Random randomNumber = new Random();
		while (true) {
			Patient patient = getTheNextPatient();
			if (patient != null) {
				patient.setChangebleState(false);
				LOGGER.log(Level.INFO, "The patient with id " + patient + " entered the doctors room.");
				try {
					Thread.sleep(3000 + randomNumber.nextInt(10000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				LOGGER.log(Level.INFO, "The patient with id " + patient + " was saved by the doctors.");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
