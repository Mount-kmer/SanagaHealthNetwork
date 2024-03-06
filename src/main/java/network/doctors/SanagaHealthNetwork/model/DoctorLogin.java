package network.doctors.SanagaHealthNetwork.model;

public class DoctorLogin {
    private String username;
    private String password;

    public DoctorLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public DoctorLogin(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
