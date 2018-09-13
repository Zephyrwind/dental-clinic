package fcul.pco.dentalclinic.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import fcul.pco.dentalclinic.domain.Doctor;
import fcul.pco.dentalclinic.domain.DoctorCatalog;
import fcul.pco.dentalclinic.main.App;
import fcul.pco.dentalclinic.main.ApplicationConfiguration;

/**
* This DoctorCatalog class is responsible for storing and reading the information of the doctor catalog
* to and from files.
* @author fc42008
* @author fc42208
*/
public class DoctorCatalogPersistence {

	 
	 /**
	 * Stores the information in the doctor catalog in a .txt file
	 * @param doctors
	 * @throws IOException
	 * @ensures that all data in doctors is stored as .txt file in the toString() format
	 */
	public static void save(Map<Integer, Doctor> doctors) throws IOException {
		 BufferedWriter bw = new BufferedWriter( new FileWriter(ApplicationConfiguration.ROOT_DIRECTORY + ApplicationConfiguration.DOCTOR_CATALOG_FILENAME + ".txt"));
		 for (Doctor d: doctors.values()) {
			 bw.write(d.toString());
			 bw.newLine();
		 } 
		 bw.close();
	 }
	
	/**
	 * Reads doctor information from a .txt file and stores it in the doctor catalog 
	 * @throws FileNotFoundException
	 * @requires that all lines in the file be in the toString() format containing one doctor per line
	 * @ensures that all doctors in the .txt file are stored in the doctor catalog
	 */
	 public static DoctorCatalog load() throws FileNotFoundException{
		 DoctorCatalog dc = App.getDoctorCatalog();
		 Scanner scan = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY + ApplicationConfiguration.DOCTOR_CATALOG_FILENAME + ".txt"));
		 while (scan.hasNextLine()) {
			 Doctor d= Doctor.fromString(scan.nextLine());
			 dc.addDoctor(d);
			 d.setAgenda(AgendaPersistence.load(d));
		 }
		 scan.close();
		 return dc;
	 }
		 
	 
}
