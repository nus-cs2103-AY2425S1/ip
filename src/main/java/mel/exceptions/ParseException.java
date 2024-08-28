package mel.exceptions;

public class ParseException extends Exception {
    public ParseException(String str) {
        super(str);
    }

    @Override
    public String toString() {
        return "Incorrect format: " + super.getMessage();
    }
}
