package chacha.exception;

import java.time.DateTimeException;

public class WrongDateFormatException extends DateTimeException {
    private String msg;
    public WrongDateFormatException() {
        super("Please input the date in the format YYYY-MM-DD. ");
        this.msg = "Please input the date in the format YYYY-MM-DD. ";
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
