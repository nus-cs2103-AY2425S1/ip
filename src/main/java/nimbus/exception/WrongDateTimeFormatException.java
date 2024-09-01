package nimbus.exception;

public class WrongDateTimeFormatException extends Exception {
    public WrongDateTimeFormatException() {
        super("Wrong nimbus.ui.DateTime format, Follow DD/MM/YYYY 24HR Time \n" +
                "Eg: 2/12/2019 1800");
    }
}
