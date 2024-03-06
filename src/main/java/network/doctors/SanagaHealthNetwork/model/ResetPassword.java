package network.doctors.SanagaHealthNetwork.model;

public class ResetPassword {
    private String email;
    private String password;

    public ResetPassword(){

    }
    public void setEmail(String email, String password){
        this.password = password;
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public String getPassword(){return password;}

}
