package fcul.pco.dentalclinic.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import fcul.pco.dentalclinic.persistence.PatientCatalogPersistence;

/**
* The PatientCatalog class represents a catalog of Patients, only one can exist at all times.
* @author fc42008
* @author fc42208
*/
public class PatientCatalog {
	// ATRIBUTES
		private TreeMap<Integer, Patient> patientCatalog;
		static PatientCatalog instance;
		
		/**
		 * Constructor of the patient catalog. Can only be called by getInstance().
		 */
		private PatientCatalog() {
			patientCatalog=new TreeMap<Integer,Patient>();
		}
		
		/**
		 * It refers to the patient catalog and, if it doesn't already exist, creates it.
		 * @return instance
		 */
		public static PatientCatalog getInstance() {
			if (instance == null) {
				instance = new PatientCatalog();
			}
			return instance;
		}
		
		/**
		 * Adds a patient to the patient catalog.
		 * @param d
		 */
		public void addPatient(Patient p) {
			patientCatalog.put(p.getId(), p);
		}
		
		/**
		 * Gets a patient by its ID.
		 * @param id
		 */
		public Patient getPatientById(int id){
			return patientCatalog.get(id);
		}
		@Override
		public String toString() {
			List<List<String>> table = new ArrayList<List<String>>();
			for (Patient p : patientCatalog.values()) {
				ArrayList<String> row = new ArrayList<String>();
				row.add(String.valueOf(p.getId()));
				row.add(p.getName());
				table.add(row);
			}
			return Utils.tableToString(table);
		}
		
		/**
		 * Saves the data in the patient catalog.
		 * @throws IOException. If the file can't be opened.
		 */
		public void save() throws IOException {
			PatientCatalogPersistence.save(patientCatalog);
		}
		
		/**
		 * Reads data from a file and saves it to the patient catalog.
		 * @throws IOException. If the file doesn't exist or can't be opened.
		 */
		public void load() throws IOException {
			instance = PatientCatalogPersistence.load();
		}
}
