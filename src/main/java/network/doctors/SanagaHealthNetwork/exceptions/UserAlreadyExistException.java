package network.doctors.SanagaHealthNetwork.exceptions;

import javax.naming.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {
    public UserAlreadyExistException(final String msg){
        super(msg);
    }
}
