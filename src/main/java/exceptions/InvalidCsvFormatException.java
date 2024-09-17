package exceptions;

public class InvalidCsvFormatException extends Exception {
    public InvalidCsvFormatException() {
        super("Invalid format for EventTask in CSV.");
    }
}
