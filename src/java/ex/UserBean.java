package ex;

import java.io.Serializable;

public class UserBean implements Serializable {

    private String login;
    private String pass;
    private String name;
    private String firstName;
    private String birthDate;
    private String email;
    private int phone;
    private int credits;
    private boolean valid;

    public UserBean() {
    }

    public UserBean(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }

    public UserBean(String login, String pass, String name, String firstName, String birthDate, String email, int phone, int credits) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.credits = credits;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean newValid) {
        valid = newValid;
    }
}
