package fcul.pco.dentalclinic.main;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import fcul.pco.dentalclinic.domain.DoctorCatalog;
import fcul.pco.dentalclinic.domain.PatientCatalog;

/**
* The main class.
* @author fc42008
* @author fc42208
*/
public class App {

	static DoctorCatalog doctorCatalog;
	static PatientCatalog patientCatalog;
	
	public static void main(String[] args) throws IOException {

		initialize();	
		interactiveMode();
		//executeUseCase("data/testCase.txt");
		
	}

	/**
	 *This method may be called to use the application in default mode i.e.
	 *interacting with the keyboard.
	 *@throws IOException
	 */
	private static void interactiveMode() throws IOException {
		try (Scanner in = new Scanner(System.in)) {
			in.useLocale(Locale.US);
			Menu.mainMenu(in);
		}
	}
 
	/**
	 *This method initializes the doctor catalog through an already existing file.
	 *@throws IOException
	 */
	private static void initialize() {
		doctorCatalog = DoctorCatalog.getInstance();
		patientCatalog = PatientCatalog.getInstance();
		try { doctorCatalog.load();} 
		catch (IOException ex) { System.err.println("Error loading DoctorCatalog."); }
		try { patientCatalog.load();} 
		catch (IOException ex1) { System.err.println("Error loading PatientCatalog."); }
	}
	
	public static DoctorCatalog getDoctorCatalog() {
		return DoctorCatalog.getInstance();
	}
	
	public static PatientCatalog getPatientCatalog() {
		return PatientCatalog.getInstance();
	}
	
	private static void executeUseCase(String useCaseFileName) throws IOException {
		System.out.println("Test: " + useCaseFileName);
		Scanner in = new Scanner(new File(useCaseFileName));
		in.useLocale(Locale.US);
		in.nextLine();
		Menu.mainMenu(in);
		in.close();
	}
	
	public static void executeAllUseCases() {
		try {
		executeUseCase("data/addDoctorUsecase");
		// acrescentar aqui a execu��o de mais usecases.
		// executeUseCase("data/maisUsecaseAVossaEscolha");
		} catch (IOException ex) {
		System.err.println(ex.getMessage());
		ex.printStackTrace();
		}
	}
	
}
