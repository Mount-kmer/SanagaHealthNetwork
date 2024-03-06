package network.doctors.SanagaHealthNetwork.restDTO;

public class AppointmentDTO {

    private String doctorFN;
    private String doctorLN;
    private String time;
    private String date;
    private String hospital;
    private String address;


    public String getDoctorFN(){
        return doctorFN;
    }

    public void setDoctorFN(String fn){
        this.doctorFN = fn;
    }

    public String getDoctorLN() {
        return doctorLN;
    }

    public void setDoctorLN(String doctorLN) {
        this.doctorLN = doctorLN;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
