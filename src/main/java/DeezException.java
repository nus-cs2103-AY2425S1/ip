import java.util.ArrayList;

public class DeezException extends RuntimeException {
    protected String[] errorMessages;
    public DeezException (String ...errorMessage) {
        super();
        this.errorMessages = errorMessage;
    }
    public String[] getErrorMessages() {
        return this.errorMessages;
    }
}
