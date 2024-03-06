package network.doctors.SanagaHealthNetwork.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AppointmentForm {
    private int doctorId;
    private String lastName;
    private int userId;
    private String time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public AppointmentForm(){
    }

    public AppointmentForm(int doctorId, int userId, String time, LocalDate date) {
        this.doctorId= doctorId;
        this.userId = userId;
        this.time = time;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public int getUserId() {
        return userId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

}
