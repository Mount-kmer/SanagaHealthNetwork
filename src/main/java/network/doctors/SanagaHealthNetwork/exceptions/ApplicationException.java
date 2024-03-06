package network.doctors.SanagaHealthNetwork.exceptions;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String error) {
        super(error);
    }
}
