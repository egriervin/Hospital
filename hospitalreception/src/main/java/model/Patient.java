/**
 * 
 */
package model;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.Utils;

/**
 * @author Ervin
 *
 */
public class Patient extends Person implements Runnable {
	static final Logger LOGGER = Logger.getLogger(Patient.class.getName());

	private Injury injury;
	private int priority;
	private Nurse nurse;
	private boolean changebleState = true;

	public Patient(Nurse nurse) {
		this.nurse = nurse;
		Random randomNumber1 = new Random();
		int newPriority = randomNumber1.nextInt(3) + 2;
		Utils.setInjuryAndPriority(this, newPriority);
		this.nurse.setPriority(this);
		LOGGER.log(Level.INFO, "New patient with id " + this + " entered the waiting room, injury: "
				+ this.injury.toString() + ", priority: " + this.priority);
	}

	public Injury getInjury() {
		return injury;
	}

	public void setInjury(Injury injury) {
		this.injury = injury;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public void run() {
		while (this.changebleState) {
			Random randomNumber1 = new Random();
			if (randomNumber1.nextInt(3) == 0) {
				int oldPriority = this.priority;
				int newPriority = randomNumber1.nextInt(4) + 1;

				if (this.priority != newPriority) {
					this.setPriority(newPriority);
					LOGGER.log(Level.INFO, "The patient with id " + this + " changed it's priority from " + oldPriority
							+ " to " + this.priority);
					if (this.priority == 1) {
						nurse.updatePatientPriorityQueue();
						LOGGER.log(Level.INFO, "The nurse updated the queue because the patient with id " + this
								+ " it's in a critical state!");
					}
				}
			}
			try {
				Thread.sleep(13000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean isChangebleState() {
		return changebleState;
	}

	public void setChangebleState(boolean changebleState) {
		this.changebleState = changebleState;
	}

}
