import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HospitalController {

    private final HospitalService hospitalService = new HospitalService();

    public void onRegisterClicked() {
        openRegistrationForm();
    }

    public void onSymptomClicked() {
        openRegistrationForm();
    }

    public void onViewAppointmentsClicked() {
        showAppointmentsWindow();
    }

    private void openRegistrationForm() {
        Stage stage = new Stage();
        stage.setTitle("Patient Registration & Triage");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();

        Label genderLabel = new Label("Gender:");
        TextField genderField = new TextField();

        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label symptomsLabel = new Label("Describe your symptoms:");
        TextArea symptomsArea = new TextArea();
        symptomsArea.setPrefRowCount(4);

        Button submitButton = new Button("Submit");
        submitButton.setDefaultButton(true);

        // Add all fields to Grid
        grid.add(nameLabel, 0, 0);    grid.add(nameField, 1, 0);
        grid.add(ageLabel, 0, 1);     grid.add(ageField, 1, 1);
        grid.add(genderLabel, 0, 2);  grid.add(genderField, 1, 2);
        grid.add(phoneLabel, 0, 3);   grid.add(phoneField, 1, 3);
        grid.add(emailLabel, 0, 4);   grid.add(emailField, 1, 4);
        grid.add(symptomsLabel, 0, 5);grid.add(symptomsArea, 1, 5);
        grid.add(submitButton, 1, 6);

        submitButton.setOnAction(e -> {
            try {
                // --- Collect user input ---
                String name = nameField.getText().trim();
                String age = ageField.getText().trim(); // age as String (matches Patient class)
                String gender = genderField.getText().trim();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();
                String symptoms = symptomsArea.getText().trim();

                if (name.isEmpty() || symptoms.isEmpty()) {
                    showAlert(Alert.AlertType.WARNING, "Missing Data",
                            "Please enter at least a name and symptoms.");
                    return;
                }

                String patientId = "P-" + System.currentTimeMillis();

                // --- FIXED Patient constructor (7 parameters) ---
                Patient patient = new Patient(
                        patientId,
                        name,
                        age,
                        gender,
                        phone,
                        email,
                        symptoms
                );


                String specialty = SymptomAnalyzer.detectSpecialty(symptoms);

                Doctor doctor = hospitalService.findDoctorForSpecialty(specialty);

                if (doctor == null) {
                    showAlert(Alert.AlertType.ERROR, "No Doctor Available",
                            "No doctor available for specialty: " + specialty);
                    return;
                }

                // Create appointment
                Appointment appointment = hospitalService.createAppointment(patient, doctor);

                // Show summary
                StringBuilder sb = new StringBuilder();
                sb.append("Patient Registered Successfully!\n\n");
                sb.append("Patient: ").append(patient.getName())
                        .append(" (").append(patient.getAge()).append(", ").append(patient.getGender()).append(")\n");
                sb.append("Phone: ").append(patient.getPhone()).append("\n");
                sb.append("Email: ").append(patient.getEmail()).append("\n");
                sb.append("Symptoms: ").append(patient.getSymptoms()).append("\n\n");
                sb.append("Assigned Specialist: ").append(doctor.getName())
                        .append(" (").append(doctor.getSpecialty()).append(")\n");
                sb.append("Appointment: ").append(appointment.getDateTime())
                        .append(" | ID: ").append(appointment.getId());

                showAlert(Alert.AlertType.INFORMATION, "Triage Result", sb.toString());

                stage.close();

            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Invalid Age",
                        "Please enter a valid number (example: 21).");
            }
        });

        Scene scene = new Scene(grid, 520, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void showAppointmentsWindow() {
        Stage stage = new Stage();
        stage.setTitle("All Appointments");

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        if (hospitalService.getAppointments().isEmpty()) {
            root.getChildren().add(new Label("No appointments yet."));
        } else {
            for (Appointment a : hospitalService.getAppointments()) {
                root.getChildren().add(new Label(a.toString()));
            }
        }

        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

