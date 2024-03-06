package network.doctors.SanagaHealthNetwork.entity;

import network.doctors.SanagaHealthNetwork.model.Gender;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Table(name = "sanga_users")
@Entity
public class User {

    private static final int tokenExpiration = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private int id;

//    @Size(min=6, message = "{userName.cannot.be.less.than.six.characters}")
//    @Column(name = "username")
//    private String username;
//    @Pattern(regexp = "((?=.*[A-Z]).{8,20})", message = "Password must have one upper case, one lower case and should be minimum 8 characters")
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,20}$", message = "Password must have one upper case, one lower case, one digit, and should be minimum 8 characters with special characters")
    @Column(name="password")
    private String userPassword;

    @NotEmpty(message="first name cannot be empty")
    @Column(name = "firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Roles> roles;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Email
    @NotEmpty
    @Column(name="email")
    private String email;

    @Column(name="password_token")
    public String passwordResetToken;

    @Column(name = "isenabled")
    private boolean isEnabled;

    private boolean account_expired;

    private boolean account_locked;

    @Column(name = "failed_attempts")
    private int failedAuthenticationAttempt;

    @Column(name = "lockout_time")
    private Date lockOutTime;


    public User() {
    }

    public User(String email, String password, int failedAuthenticationAttempt, Date lockOutTime) {
        this.email =email;
        this.userPassword =password;

        this.failedAuthenticationAttempt = failedAuthenticationAttempt;
        this.lockOutTime = lockOutTime;
    }

    public Date whenItExpires(final int expiryDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiryDate);
        return new Date(calendar.getTime().getTime());
    }


    public void setId(int id) {
        this.id = id;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public int getId(){
        return this.id;
    }

//
//    public String getUserName() {
//        return this.username;
//    }
//
//    public void setUserName(String userName) {
//        this.username = userName;
//    }

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

    public Gender getGender() {
        return gender;
    }


    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isAccount_non_expired(){return account_expired;}

    public void setAccount_non_expired(boolean account_expired){
        this.account_expired = account_expired;
    }

    public boolean getAccount_non_locked(){return account_locked;}

    public void  setAccount_non_locked(boolean account_locked){
        this.account_locked = account_locked;
    }


    public String getFullName() {
        return firstName +  ", " + lastName;
    }


    public int getFailedAuthenticationAttempt() {
        return failedAuthenticationAttempt;
    }

    public void setFailedAuthenticationAttempt(int failedAuthenticationAttempt) {
        this.failedAuthenticationAttempt = failedAuthenticationAttempt;
    }

    public Date getLockOutTime() {
        return lockOutTime;
    }

    public void setLockOutTime(Date lockOutTime) {
        this.lockOutTime = lockOutTime;
    }
}
