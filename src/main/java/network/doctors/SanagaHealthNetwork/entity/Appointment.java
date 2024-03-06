package network.doctors.SanagaHealthNetwork.entity;

import network.doctors.SanagaHealthNetwork.model.AppointmentStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Entity
@Table(name = "sanaga_appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorList doctorList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @DateTimeFormat(pattern = "HH:mm")
    private String time;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;


    public Appointment(String time){
        this.time = time;
    }



//    public Appointment(DoctorList doctorList) {
//        this.doctorList = doctorList;
//
//    }

    public Appointment(){

    }

    public Appointment(DoctorList doctorList, User user, String time, LocalDate date){
        this.doctorList=doctorList;
        this.user = user;
        this.time = time;
        this.date = date;
    }

    public void setUserList(User user) {
        this.user = user;
    }

    public User getUSerList(){
        return user;
    }

    public void setDoctorList(DoctorList doctorList){
        this.doctorList = doctorList;

    }
    public DoctorList getDoctorList(){

        return doctorList;
    }

    public void setStatus(AppointmentStatus appointmentStatus){
        this.status = appointmentStatus;
    }

    public AppointmentStatus getStatus(){
        return  status;
    }

    public User getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocalDate() throws ParseException {
        return formatDate(date);
    }

    public void setLocalDate(LocalDate localDate) {
        this.date = localDate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String formatDate(LocalDate date) throws ParseException {
        String dateValue = date.toString();

        LocalDate localDate = LocalDate.parse(dateValue);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String weekNumber = new SimpleDateFormat("dd").format(dateFormat.parse(dateValue));
        String dayOfWeek = new SimpleDateFormat("EEEE").format(dateFormat.parse(dateValue));
        String monthName = new SimpleDateFormat("MMMM").format(dateFormat.parse(dateValue));
        String year = new SimpleDateFormat("yyyy").format(dateFormat.parse(dateValue));
        return dayOfWeek + "  " + monthName + "  " + weekNumber + "  " + year;
    }


}
