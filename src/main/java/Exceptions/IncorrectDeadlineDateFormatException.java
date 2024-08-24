package Exceptions;

public class IncorrectDeadlineDateFormatException extends Exception {
    public IncorrectDeadlineDateFormatException() {
        super("Please enter the correct format: 'yyyy-mm-dd' or 'yyyy-mm-dd hh-mm'");
    }
}
