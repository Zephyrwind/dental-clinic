package fcul.pco.dentalclinic.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import fcul.pco.dentalclinic.domain.Agenda;
import fcul.pco.dentalclinic.domain.Appointment;
import fcul.pco.dentalclinic.domain.Doctor;
import fcul.pco.dentalclinic.main.ApplicationConfiguration;

/**
* This AgendaPersistence class is responsible for storing and reading the information of agendas
* to and from files.
* @author fc42008
* @author fc42208
*/
public class AgendaPersistence {

		/**
		* Stores the information of an agenda in a .txt file which corresponds to a doctor
		* @param agenda
		* @param doctor
	 	* @throws IOException
	 	* @ensures that all appointments in the agenda is stored as a .txt file (in the toString() 
	 	* format) named after the doctor's id
	 	*/
		public static void save(Agenda agenda, Doctor doctor) throws IOException {
			 BufferedWriter bw = new BufferedWriter( new FileWriter(ApplicationConfiguration.ROOT_DIRECTORY + doctor.getId() + ".txt"));
			 for (Appointment app: agenda.getAppointments()) {
				 bw.write(app.toString());
				 bw.newLine();
			 } 
			 bw.close();
		 }
		
		/**
		 * Reads appointments from a .txt file corresponding to a doctor and stores it in an agenda 
		 * @throws FileNotFoundException
		 * @requires that all lines in the file be in the toString() format containing one appointment per line
		 * @ensures that all appointments in the .txt file are stored in the agenda
		 */
		 public static Agenda load(Doctor doctor) throws FileNotFoundException{
			 Agenda ag = new Agenda();
			 Scanner scan = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY + doctor.getId()+ ".txt"));
			 while (scan.hasNextLine()) {
				 ag.addAppointment(Appointment.fromString(scan.nextLine()));
			 }
			 scan.close();
			 return ag;
		 } 
}
