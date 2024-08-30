/**
 * The `InvalidDateException` class represents an exception that is thrown when a date
 * is provided in an invalid format.
 */
package duke.exceptions;

public class InvalidDateException extends Exception {
    public String toString() {
        return "OOPS!!! Please ensure that date format are in dd/MM/yyyy HH:mm or dd/MM/yyyy.";
    }
}
