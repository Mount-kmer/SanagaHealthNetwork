package network.doctors.SanagaHealthNetwork.entity;


import network.doctors.SanagaHealthNetwork.model.Gender;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sanaga_doctors")
public class DoctorList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doctor_id")
    private Integer id;

    @Column(name = "first_name" )
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "practice")
    private String practice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_id")
    private Hospital hospitalWorked;

    @Column(name = "password")
    private String password;

    @Column(name = "email_address")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(name = "imagepath")
    private String imagePath;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctors_roles", joinColumns = @JoinColumn(name = "doctors_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Roles> roles;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_non_expired")
    private boolean account_non_expired;

    @Column(name = "account_non_locked")
    private boolean account_non_locked;

    @Column(name = "credentials_non_expired")
    private boolean credentials_non_expired;


    @Column(name = "failed_attempts")
    private int failedAuthenticationAttempt;

    @Column(name = "lockout_time")
    private Date lockOutTime;

    // constructor

    public DoctorList() {

    }

    public DoctorList(Integer id, String firstName, String lastName, String practice,
                      Hospital hospitalWorked, String password, String email, String phoneNumber, Gender gender, Date dateOfBirth, String imagePath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.practice =practice;
        this.hospitalWorked = hospitalWorked;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.imagePath = imagePath;
    }

    //To string method to print data
    @Override
    public String toString() {
        return
                 firstName  +"  "+ lastName+  " \n" + practice+ "\n" +   "\n"+ email+ "\n  " + phoneNumber +"\n "
                         +imagePath;
    }


// getters and setters

    public Integer getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAreaOfPractice() {
        return practice;
    }

    public void setAreaOfPractice(String areaOfPractice) {
        this.practice = areaOfPractice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDoctorFullName(String doctorId){
        return this.getFirstName() + this.getLastName();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Hospital getHospitalWorked() {
        return hospitalWorked;
    }

    public int setHospitalWorked(Hospital hospitalWorked) {
        this.hospitalWorked = hospitalWorked;
        return hospitalWorked.getId();
    }

    public boolean getAccountIsEnabled(){
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled){
        this.isEnabled = isEnabled;
    }

    public boolean getAccountNonExpired(){
        return account_non_expired;
    }

    public void setAccount_non_expired(boolean account_non_expired){
        this.account_non_expired = account_non_expired;
    }

    public boolean getAccountNonLocked(){
        return account_non_locked;
    }

    public void setAccount_non_locked(boolean account_non_locked){
        this.account_non_locked = account_non_locked;
    }

    public boolean getCredentialsNonExpired(){
        return credentials_non_expired;
    }

    public void setCredentials_non_expired(boolean credentials_non_expired){
        this.credentials_non_expired = credentials_non_expired;
    }

    public int getFailedAuthAttempts(){
        return failedAuthenticationAttempt;
    }

    public void setFailedAuthenticationAttempt(int authenticationAttempt){
        this.failedAuthenticationAttempt = authenticationAttempt;
    }

    public Date getLockOutTime(){
        return lockOutTime;
    }

    public void setLockOutTime(Date lockOutTime){
        this.lockOutTime = lockOutTime;

    }


}