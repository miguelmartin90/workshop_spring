package org.example.response;

public class CsvPerson {

    private String index;
    private String userId;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
    private String phone;
    private String dateOfBirth;
    private String jobTitle;

    public CsvPerson(String index, String userId, String firstName, String lastName, String sex, String email, String phone, String dateOfBirth, String jobTitle) {
        this.index = index;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.jobTitle = jobTitle;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUser() {
        return userId;
    }

    public void setUser(String user) {
        this.userId = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
