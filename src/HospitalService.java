import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HospitalService {

    private List<Doctor> doctors = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public HospitalService() {
        doctors.add(new Doctor("D1", "Dr. Smith", "Cardiology"));
        doctors.add(new Doctor("D2", "Dr. Johnson", "Gastroenterology"));
        doctors.add(new Doctor("D3", "Dr. Taylor", "Dermatology"));
        doctors.add(new Doctor("D4", "Dr. Williams", "General Medicine"));
        doctors.add(new Doctor("D5", "Dr. Brown", "Psychiatry"));
        doctors.add(new Doctor("D6", "Dr. Davis", "OBGYN"));
    }

    public Doctor findDoctorForSpecialty(String specialty) {
        return doctors.stream()
                .filter(doc -> doc.getSpecialty().equalsIgnoreCase(specialty))
                .findFirst()
                .orElse(null);
    }

    public Appointment createAppointment(Patient patient, Doctor doctor) {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime appointmentTime;
        if (appointments.isEmpty()) {
            appointmentTime = now.plusDays(1).withHour(10).withMinute(0);
        } else {
            LocalDateTime last = appointments.get(appointments.size() - 1).getDateTime();
            appointmentTime = last.plusMinutes(30);
        }

        Appointment appointment = new Appointment(
                "A-" + System.currentTimeMillis(),
                patient,
                doctor,
                appointmentTime
        );

        appointments.add(appointment);
        return appointment;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
