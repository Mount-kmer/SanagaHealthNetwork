package network.doctors.SanagaHealthNetwork.entity;

import javax.persistence.*;

@Entity
@Table(name = "sanaga_hospital_addresses")
public class HospitalAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer address_id;
    private String street;
    private String city;
    private String country;

    public HospitalAddress(Integer address_id, String street, String city, String country) {
        this.address_id = address_id;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public HospitalAddress() {

    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
