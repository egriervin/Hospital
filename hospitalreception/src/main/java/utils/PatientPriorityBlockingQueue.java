package utils;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

import model.Patient;

public class PatientPriorityBlockingQueue {
	@Override
	public String toString() {
		return "PatientPriorityBlockingQueue [patientPriorityBlockingQueue=" + patientPriorityBlockingQueue + "]";
	}

	PriorityComparator patientPriorityComparator;
	PriorityBlockingQueue<Patient> patientPriorityBlockingQueue;

	public PatientPriorityBlockingQueue() {
		this.patientPriorityComparator = new PriorityComparator();
		this.patientPriorityBlockingQueue = new PriorityBlockingQueue<Patient>(10, patientPriorityComparator);
	}

	class PriorityComparator implements Comparator<Patient> {
		@Override
		public int compare(Patient patient1, Patient patient2) {
			if (patient1.getPriority() < patient2.getPriority()) {
				return -1;
			}
			if (patient1.getPriority() > patient2.getPriority()) {
				return 1;
			}
			return 0;
		}
	}

	/**
	 * the complexity is O(1) because we poll the last element.
	 * 
	 * @return
	 */
	public Patient pollPatient() {
		return this.patientPriorityBlockingQueue.poll();
	}

	/**
	 * inserting new elements is O(n) since the insertion point needs to be
	 * found.
	 * 
	 * @return
	 */
	public boolean addPatient(Patient patient) {
		return this.patientPriorityBlockingQueue.add(patient);
	}

	public boolean isEmpty() {
		return this.patientPriorityBlockingQueue.isEmpty();
	}

	public int size() {
		return this.patientPriorityBlockingQueue.size();
	}

	/**
	 * updating the new queue is O(n) because if we want to reorder the elements
	 * by priority we have to recreate the queue.
	 */
	public void updatePatientQueue() {
		PriorityBlockingQueue<Patient> newPatientPriorityBlockingQueue = new PriorityBlockingQueue<Patient>(10,
				patientPriorityComparator);
		if (patientPriorityBlockingQueue.size() > 1) {
			while (patientPriorityBlockingQueue.peek() != null) {
				newPatientPriorityBlockingQueue.add(patientPriorityBlockingQueue.poll());
			}
			this.patientPriorityBlockingQueue = newPatientPriorityBlockingQueue;
		}
	}
}
