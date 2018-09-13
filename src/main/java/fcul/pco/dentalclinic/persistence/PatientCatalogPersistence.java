package fcul.pco.dentalclinic.persistence;

	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.Map;
	import java.util.Scanner;

	import fcul.pco.dentalclinic.domain.Patient;
	import fcul.pco.dentalclinic.domain.PatientCatalog;
import fcul.pco.dentalclinic.main.App;
import fcul.pco.dentalclinic.main.ApplicationConfiguration;

	/**
	* This patientCatalog class is responsible for storing and reading the information of the patient catalog
	* to and from files.
	* @author fc42008
	* @author fc42208
	*/
	public class PatientCatalogPersistence {

		 
		 /**
		 * Stores the information in the patient catalog in a .txt file
		 * @param patients
		 * @throws IOException
		 * @ensures that all data in doctors is stored as .txt file in the toString() format
		 */
		public static void save(Map<Integer, Patient> patients) throws IOException {
			 BufferedWriter bw = new BufferedWriter( new FileWriter(ApplicationConfiguration.ROOT_DIRECTORY + ApplicationConfiguration.PATIENT_CATALOG_FILENAME + ".txt"));
			 for (Patient p: patients.values()) {
				 bw.write(p.toString());
				 bw.newLine();
			 } 
			 bw.close();
		 }
		
		/**
		 * Reads patient information from a .txt file and stores it in the patient catalog 
		 * @throws FileNotFoundException
		 * @requires that all lines in the file be in the toString() format containing one patient per line
		 * @ensures that all patients in the .txt file are stored in the patient catalog
		 */
		 public static PatientCatalog load() throws FileNotFoundException{
			 PatientCatalog pc = App.getPatientCatalog();
			 Scanner scan = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY + ApplicationConfiguration.PATIENT_CATALOG_FILENAME + ".txt"));
			 while (scan.hasNextLine()) {
				 Patient p = Patient.fromString(scan.nextLine());
				 pc.addPatient(p);
			 }
			 scan.close();
			 return pc;
		 }
			 
		 
	}


