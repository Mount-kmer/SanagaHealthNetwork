package network.doctors.SanagaHealthNetwork.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sanaga_hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hospital_id")
    private Integer id;
    @Column(name = "hospital_name")
    private String hospitalName;

    @OneToOne
    @JoinColumn(name = "address_id")
    private HospitalAddress hospitalAddresses;

    @OneToMany(mappedBy = "hospitalWorked")
    private Set<DoctorList> doctorList;


    public Hospital() {

    }

    public Hospital(String hospitalName, HospitalAddress hospitalAddresses, Set<DoctorList> doctorList){
        this.hospitalName = hospitalName;
        this.hospitalAddresses = hospitalAddresses;
        this.doctorList = doctorList;
    }

    public HospitalAddress getHospitalAddresses() {
        return hospitalAddresses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Set<DoctorList> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(Set<DoctorList> doctorList) {
        this.doctorList = doctorList;
    }
}
