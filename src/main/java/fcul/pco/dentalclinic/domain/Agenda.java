package fcul.pco.dentalclinic.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fcul.pco.dentalclinic.persistence.AgendaPersistence;

/**
* The Agenda class represents a list of appointments.
* @author fc42008
* @author fc42208
*/
public class Agenda {
	
	// ATRIBUTES
	private ArrayList<Appointment> appointments;
	
	/**
	 * Reads a doctor's agenda from a file
	 * @param d
	 * @return the agenda belonging to doctor d
	 * @throws FileNotFoundException
	 */
	public static Agenda load(Doctor d) throws FileNotFoundException {
		return AgendaPersistence.load(d);
	}
	
	/**
	* Creates an Agenda instance.
	*/
	public Agenda(){
		appointments = new ArrayList<Appointment>();
	}
	
	/**
	* Adds an appointment to the Agenda instance.
	* @param a. An appointment.
	*/
	public void addAppointment (Appointment a) {
		appointments.add(a);
	}

	/**
	 *  returns the appointments saved in this agenda
	 */
	public List<Appointment> getAppointments(){
		return appointments;
	}
	

	public void save(Doctor d) throws IOException{
		AgendaPersistence.save(d.getAgenda(), d);
	}
	
	/**
	 * @return a list of the dates of all appointments later than this date
	 */
	public List<Date> getNextAppointmentDates(Date from){
		List<Date> d = new ArrayList<Date>();
		for (Appointment a: appointments) {
			if(Date.compareDates(a.getDate() , from) > 0)
				d.add(a.getDate());
		}
		return d;
	}
	
	/**
	* @return la a list of all appointments in a date
	*/
	public List<Appointment> getDayAppointments(Date from){
		List<Appointment> la= new ArrayList<Appointment>();
		for(Appointment a: appointments){
			if(Date.compareDates(a.getDate(), from) == 0)
				la.add(a);
		}
		Collections.sort(la);
		return la;
	}
	
}
