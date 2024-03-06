package network.doctors.SanagaHealthNetwork.restDTO;

import javax.transaction.Transactional;

@Transactional
public class DoctorDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String practice;
    private String hospital;
    private String address;
    private String email;

//    public DoctorDTO(Integer id, String firstname, String lastname, String practice, String hospital) {
//        this.id = id;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.practice = practice;
//        this.hospital = hospital;
//    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
