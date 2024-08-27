package PurrfessorDipsy.Exception;

public class InvalidDateException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid date format. Please enter the date in the format yyyy-MM-dd (e.g., 2024-08-25).";
    }
}
