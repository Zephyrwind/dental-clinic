package fcul.pco.dentalclinic.domain;

/**
* The Patient class represents a patient composed of a name and an ID.
* @author fc42008
* @author fc42208
*/
public class Patient {
	
	private String name;
	private int id;
	

		/**
		* Creates a Patient instance from a String.
		* @param s. String to read.
		* @requires toString() format
		*/
		public static Patient fromString(String s) {
			// Creates string with ID
			String s1=s.substring(0, s.indexOf(','));
			// Creates string with name
			String s2=s.substring(s.indexOf(',')+1);
			return new Patient(s2, Integer.parseInt(s1));
		}
		
		
		public Patient (String name, int id) {
			this.name=name;
			this.id=id;
		}
		
		public Patient (int id) {
			this.id=id;
		}
		
		/**
		 * Gets the Patient's name.
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * Gets the Patient's ID.
		 */
		public int getId() {
			return id;
		}
		
		/**
		* Represents the Patient's name and ID.
		*/
		public String toString() {
			return id + "," + name;
		}

		/**
		* Creates a Patient instance from a String.
		* @param s. String to read.
		* @requires toString() format
		*/
		public static Patient fromString(String s) {
			// Creates string with ID
			String s1=s.substring(0, s.indexOf(','));
			// Creates string with name
			String s2=s.substring(s.indexOf(',')+1);
			return new Patient(s2, Integer.parseInt(s1));
		}
}
