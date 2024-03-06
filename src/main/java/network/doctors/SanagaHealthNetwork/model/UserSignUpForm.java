package network.doctors.SanagaHealthNetwork.model;

import network.doctors.SanagaHealthNetwork.utility.ValidAge;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class UserSignUpForm {

    private String firstName;
    private String lastName;
    private String username;

    @Email
    @NotEmpty
    private String email;

//    @Pattern(regexp = "((?=.*[A-Z]).{8,20})", message = "Password must have one upper case, one lower case and should be minimum 8 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,20}$", message = "Password must have one upper case, one lower case, one digit, one special characters and should be a  minimum  of 8 characters.")
    private String password;

    @ValidAge(value = 18)
    private Date dateOfBirth;
    private Gender gender;

    public UserSignUpForm(String firstName, String lastName, String username, String email, String password, Date dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public UserSignUpForm() {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void createNewUser() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
