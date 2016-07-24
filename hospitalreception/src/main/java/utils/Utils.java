package utils;

import model.Injury;
import model.Patient;

public class Utils {

	public static final String PAPER_CUT = "PAPER_CUT";
	public static final String BROKEN_LEG = "BROKEN_LEG";
	public static final String GUN_SHOT = "GUN_SHOT";
	public static final String COLLAPSED_NOT_BREATHING = "COLLAPSED_NOT_BREATHING";

	public static void setInjuryAndPriority(Patient patient, int injuryGrade) {
		switch (injuryGrade) {
		case 4:
			patient.setInjury(Injury.PAPER_CUT);
			patient.setPriority(4);
			break;
		case 3:
			patient.setInjury(Injury.BROKEN_LEG);
			patient.setPriority(3);
			break;
		case 2:
			patient.setInjury(Injury.GUN_SHOT);
			patient.setPriority(2);
			break;
		case 1:
			patient.setInjury(Injury.COLLAPSED_NOT_BREATHING);
			patient.setPriority(1);
			break;
		default:
			try {
				throw new NotKnownInjuryException("The injury cannot be recognised!");
			} catch (NotKnownInjuryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void setPriorityByInjury(Patient patient) {
		switch (patient.getInjury().toString()) {
		case PAPER_CUT:
			patient.setPriority(4);
			break;
		case BROKEN_LEG:
			patient.setPriority(3);
			break;
		case GUN_SHOT:
			patient.setPriority(2);
			break;
		case COLLAPSED_NOT_BREATHING:
			patient.setPriority(1);
			break;
		default:
			try {
				throw new NotKnownInjuryException("The injury cannot be recognised!");
			} catch (NotKnownInjuryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
