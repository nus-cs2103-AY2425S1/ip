package Tuesday.util;

public class TuesdayException extends Exception{
    private String errorMessage;
    public TuesdayException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
