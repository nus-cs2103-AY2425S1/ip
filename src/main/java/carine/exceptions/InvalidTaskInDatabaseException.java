package carine.exceptions;

/**
 * This exception is thrown when tasks stored in database fail to load.
 */
public class InvalidTaskInDatabaseException extends Exception {
    public String toString() {
        return "ERROR: There are invalid inputs in database, you can only continue after you reset the data base. \n\n"
                + "To reset database: reset";
    }
}
