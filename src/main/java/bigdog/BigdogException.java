package bigdog;
public class BigdogException extends RuntimeException {

    public BigdogException(String str) {
        super(str + "\n");
    }

}
