package fcul.pco.dentalclinic.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The Appointment class represents an appointment composed of a date, a task name and the time duration.
 * @author fc42008
 * @author fc42208
*/
public class Appointment implements Comparable<Appointment>{
	// ATRIBUTES
	private Date date;
	private String task;
	private int duration;
	private Patient patient;
	
	/**
	 * Creates an appointment from a string
	 * @requires toString() format
	 * @param s
	 */
	public static Appointment fromString(String s) {
		String[] d = new String[4];
		d=s.split("\\$");
		return new Appointment(Date.fromString(d[0]),d[2], Integer.parseInt(d[1]), new Patient(Integer.parseInt(d[3])));
	}
	
	/**
	 * Creates an Appointment.
	 * @param d. Date of the Appointment
	 * @param tsk. Name of Appointment task
	 * @param t. Duration of the Appointment
	 */
	public Appointment (Date d, String tsk, int t, Patient p){
		this.date=d;
		this.task=tsk;
		this.duration=t;
		this.patient=p;
}
	
	/**
	 * Sets date of the Appointment.
	 * @param d
	 */
	public void setDate(Date d){
		this.date=d;
	}
	
	/**
	 * Sets task name of the Appointment.
	 * @param tsk
	 */
	public void setTask(String tsk){
		this.task=tsk;
	}
	
	/**
	 * Sets duration of the Appointment.
	 * @param t
	 */
	public void setDuration(int t){
		this.duration=t;
	}
	
	/**
	 * Sets the patient of the Appointment
	 * @param p
	 */
	public void setPatient(Patient p) {
		this.patient=p;
	}

	
	/**
	 * Gets date of the Appointment.
	 */
	public Date getDate(){
		return this.date;
	}
	
	/**
	 * Gets task name of the Appointment.
	 */
	public String getTask(){
		return this.task;
	}
	
	/**
	 * Gets date of the Appointment.
	 */
	public int getDuration(){
		return this.duration;
	}
	
	/**
	 * Gets the patient of the Appointment
	 */
	public Patient getPatient() {
		return this.patient;
	}
	
	/**
	 * Prints an appointment data to a string in y/m/d/h/min$duration$task$patient_id format
	 */
	public String toString() {
		return date.toString()+"$"+duration+"$"+task+"$"+patient.getId();
	}
	
	/**
	 * Splits an appointment into a list of strings containing the
	 * date, patient name and task.
	 */
	public List<String> toRow(){
		List<String> l = new ArrayList<String>();
		l.add(this.getDate().toString());
		l.add(this.getPatient().getName());
		l.add(this.getTask());
		return l;
	}
	
	@Override
	public int compareTo(Appointment other){
		return Date.compareDates(this.getDate(), other.getDate());
	}
	
}
