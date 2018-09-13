package fcul.pco.dentalclinic.domain;

/**
* The Doctor class represents a doctor composed of a name, an ID and an agenda.
* @author fc42008
* @author fc42208
*/
public class Doctor extends Patient{
	
	// ATRIBUTES
	private Agenda agenda;
	
	/**
	* Creates a Doctor instance from a String.
	* @param s. String to read.
	* @requires toString() format
	*/

	public static Doctor fromString(String s) {
		// Creates string with ID
		String s1=s.substring(0, s.indexOf(','));
		// Creates string with name
		String s2=s.substring(s.indexOf(',')+1);
		return new Doctor(s2, Integer.parseInt(s1), new Agenda());
	}
	
	/**
	* Creates a Doctor instance. Arguments are a name and an ID.
	* @param nome. Name of the Doctor.
	* @param idn. ID of the Doctor.
	*/
	public Doctor(String nome, int idn, Agenda agenda) {
		super(nome,idn);
		this.agenda=agenda;
	}
	
	/**
	 * Gets the Doctor's agenda
	 */
	public Agenda getAgenda() {	
		return agenda;
	}

	public void setAgenda(Agenda ag) {
		this.agenda=ag;
		
	}
}
