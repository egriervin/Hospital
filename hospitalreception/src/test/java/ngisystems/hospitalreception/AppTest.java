package ngisystems.hospitalreception;

import junit.framework.TestCase;
import model.Nurse;
import model.Patient;
import utils.PatientPriorityBlockingQueue;
import utils.Utils;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	PatientPriorityBlockingQueue patientPriorityQueue;
	Nurse nurse;

	public AppTest() {
		patientPriorityQueue = new PatientPriorityBlockingQueue();
		nurse = new Nurse(patientPriorityQueue);
	}

	public void testPatientPriority() {
		Patient patient = new Patient(nurse);
		assertTrue(patient.getPriority() > 0 && patient.getPriority() < 5);
	}

	public void testUtilsInjury() {
		Patient patient = new Patient(nurse);
		Utils.setInjuryAndPriority(patient, 2);
		assertTrue(patient.getInjury().toString().equals("GUN_SHOT"));

	}

	public void testUtilsPriority() {
		Patient patient = new Patient(nurse);
		Utils.setInjuryAndPriority(patient, 1);
		Utils.setPriorityByInjury(patient);
		assertTrue(patient.getPriority() == 1);
	}

	public void testPriorityQueue() {
		PatientPriorityBlockingQueue patientQueue = new PatientPriorityBlockingQueue();
		Patient patient1 = new Patient(nurse);
		Patient patient2 = new Patient(nurse);
		Utils.setInjuryAndPriority(patient1, 2);
		Utils.setInjuryAndPriority(patient2, 1);
		patientQueue.addPatient(patient1);
		patientQueue.addPatient(patient2);
		patientQueue.updatePatientQueue();
		assertTrue(patientQueue.pollPatient().getPriority() == 1);
	}
}
