package Exceptions;

public class DavidInvalidDateTimeException extends DavidException{
    public DavidInvalidDateTimeException() {}

    @Override
    public String toString() {
        return "The inputted date time format is wrong. Please ensure that it is in the format of" +
                "\"YYYY-MM-DD HHHH\"" + "where Y is the year, M is the month, D is the date and H" +
                "is the 24 hour time.";
    }
}
