package chacha.exception;

import java.time.DateTimeException;

public class WrongTimeFormatException extends DateTimeException {
    private String msg;
    public WrongTimeFormatException() {
        super("Please input a valid time in the format HH.MMam or HH.MMpm. ");
        this.msg = "Please input a valid time in the format HH.MMam or HH.MMpm (e.g. 10.22am). ";
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
