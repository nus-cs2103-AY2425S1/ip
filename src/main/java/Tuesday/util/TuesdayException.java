package Tuesday.util;

public class TuesdayException extends Exception{
    private String error_message;
    public TuesdayException(String error_message) {
        this.error_message = error_message;
    }
}
