/**
 * 
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;

import utils.PatientPriorityBlockingQueue;
import utils.Utils;

/**
 * @author Ervin
 *
 */
public class Nurse extends Person implements Runnable {
	static final Logger LOGGER = Logger.getLogger(Nurse.class.getName());
	PatientPriorityBlockingQueue patientPriorityBlockingQueue;

	public Nurse(PatientPriorityBlockingQueue patientPriorityQueue) {
		this.patientPriorityBlockingQueue = patientPriorityQueue;
	}

	public void setPriority(Patient patient) {
		Utils.setPriorityByInjury(patient);
		this.addPatient(patient);
	}

	public void updatePatientPriorityQueue() {
		this.patientPriorityBlockingQueue.updatePatientQueue();
	}

	public void addPatient(Patient patient) {
		this.patientPriorityBlockingQueue.addPatient(patient);
	}

	public int size() {
		return this.size();
	}

	public Patient pollPatient() {
		return this.patientPriorityBlockingQueue.pollPatient();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (this.patientPriorityBlockingQueue.size() > 1) {
				this.updatePatientPriorityQueue();
				LOGGER.log(Level.INFO, "The nurse updated the queue in the waiting room. \n" + this.patientPriorityBlockingQueue);
			}
		}
	}
}
