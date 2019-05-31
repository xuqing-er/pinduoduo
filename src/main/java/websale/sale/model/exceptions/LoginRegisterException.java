package websale.sale.model.exceptions;

public class LoginRegisterException extends RuntimeException {
    public LoginRegisterException(){
        super();
    }

    public LoginRegisterException(String message){
        super(message);
    }

    public LoginRegisterException(String message,Throwable cause){
        super(message,cause);
    }

    public LoginRegisterException(Throwable cause){
        super(cause);
    }
}
