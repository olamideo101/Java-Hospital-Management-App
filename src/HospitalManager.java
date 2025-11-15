import java.time.LocalDateTime;
import java.util.Scanner;

public class HospitalManager {

    private Scanner scanner = new Scanner(System.in);
    private HospitalService service = new HospitalService();

    public void start() {
        System.out.println("=== Hospital Console System ===\n");
        registerPatientConsole();
    }

    private void registerPatientConsole() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Describe your symptoms: ");
        String symptoms = scanner.nextLine();

        String id = "P-" + System.currentTimeMillis();
        Patient p = new Patient(
                id,
                name,
                String.valueOf(age),   // Age must be a String
                gender,
                phone,
                "none",                // placeholder email if console mode has no email
                symptoms
        );

        String specialty = SymptomAnalyzer.detectSpecialty(symptoms);
        Doctor doctor = service.findDoctorForSpecialty(specialty);

        LocalDateTime dummy = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0);
        Appointment a = new Appointment("A-TEST", p, doctor, dummy);

        System.out.println("\n=== Patient Summary ===");
        System.out.println("Name: " + p.getName());
        System.out.println("Doctor Assigned: " + doctor.getName());
        System.out.println("Specialty: " + doctor.getSpecialty());
        System.out.println("Appointment: " + a.getFormattedDate());
    }
}
