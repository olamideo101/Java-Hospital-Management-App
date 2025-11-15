This is a full-featured hospital triage and appointment system built with Java and JavaFX.
It allows users to:

 Register new patients
 Analyze symptoms using a rule-based engine
 Automatically assign the correct medical specialist
 Generate unique patient IDs and appointment IDs
 Schedule appointments with conflict-free time allocation
 View all scheduled appointments in a clean UI
 Technologies Used

Java 17

JavaFX (FXML UI)

OOP Architecture (Patient, Doctor, Appointment, Services)

MVC-style Controller

IntelliJ IDEA

 Features

Dynamic medical specialty detection

Automated doctor matching

Intelligent appointment scheduling (increments in time slots)

Full JavaFX interface for patient registration and triage

Console fallback version included

 Project Structure
src/
 ├─ App.java
 ├─ main_view.fxml
 ├─ HospitalController.java
 ├─ HospitalService.java
 ├─ SymptomAnalyzer.java
 ├─ models/
 │   ├─ Patient.java
 │   ├─ Doctor.java
 │   └─ Appointment.java
