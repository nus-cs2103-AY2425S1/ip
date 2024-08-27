package dude.exception;

public class DudeDateTimeFormatException extends DudeException {
    public DudeDateTimeFormatException(){
        super("Please input the date and time with the following format: \"yyyy-MM-dd HH:mm\", and with valid value");
    }
}
