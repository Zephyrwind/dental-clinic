package fcul.pco.dentalclinic.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import fcul.pco.dentalclinic.persistence.DoctorCatalogPersistence;

/**
* The DoctorCatalog class represents a catalog of Doctors, only one can exist at all times.
* @author fc42008
* @author fc42208
*/
public class DoctorCatalog {
	// ATRIBUTES
	private TreeMap<Integer, Doctor> doctorCatalog;
	static DoctorCatalog instance;
	
	/**
	 * Constructor of the doctor catalog. Can only be called by getInstance().
	 */
	private DoctorCatalog() {
		doctorCatalog=new TreeMap<Integer,Doctor>();
	}
	
	/**
	 * It refers to the doctor catalog and, if it doesn't already exist, creates it.
	 * @return instance
	 */
	public static DoctorCatalog getInstance() {
		if (instance == null) {
			instance = new DoctorCatalog();
		}
		return instance;
	}
	
	/**
	 * Adds a doctor to the doctor catalog.
	 * @param d
	 */
	public void addDoctor(Doctor d) {
		doctorCatalog.put(d.getId(), d);
	}
	
	/**
	 * Gets a doctor by its ID.
	 * @param id
	 */
	public Doctor getDoctorById(int id){
		return doctorCatalog.get(id);
	}
	@Override
	public String toString() {
		List<List<String>> table = new ArrayList<List<String>>();
		for (Doctor d : doctorCatalog.values()) {
			ArrayList<String> row = new ArrayList<String>();
			row.add(String.valueOf(d.getId()));
			row.add(d.getName());
			table.add(row);
		}
		return Utils.tableToString(table);
	}
	
	/**
	 * Saves the data in the doctor catalog.
	 * @throws IOException. If the file can't be opened.
	 */
	public void save() throws IOException {
		DoctorCatalogPersistence.save(doctorCatalog);
	}
	
	/**
	 * Reads data from a file and saves it to the doctor catalog.
	 * @throws IOException. If the file doesn't exist or can't be opened.
	 */
	public void load() throws IOException {
		instance = DoctorCatalogPersistence.load();
	}
	
}
