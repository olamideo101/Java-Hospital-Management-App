public class Patient {

    private String id;
    private String name;
    private String age;
    private String gender;
    private String phone;
    private String email;
    private String symptoms;

    public Patient(String id, String name, String age, String gender,
                   String phone, String email, String symptoms) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.symptoms = symptoms;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getAge() { return age; }
    public String getGender() { return gender; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getSymptoms() { return symptoms; }
}
