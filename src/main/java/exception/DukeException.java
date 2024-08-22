package exception;

import java.lang.Exception;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

}
