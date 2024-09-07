package duke.exceptions;

/**
 * This exception is thrown when tasks stored in database fail to load.
 */
public class InvalidTaskInDatabaseException extends Exception {
    public String toString() {
        return "There are invalid inputs in database, please clear and try again";
    }
}
