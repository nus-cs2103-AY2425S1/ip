package casper.exceptions;

/**
 * Thrown to indicate that a line in the locally saved tasks list is corrupted.
 * This exception provides a suggested solution for handling the corruption.
 */
public class CorruptedSavedTasksException extends ExceptionWithSolution {
    /**
     * Constructs a new {@code CorruptedSavedTasksException} with the specified line number.
     * The exception message is formatted to include the line number where the corruption was found,
     * and a suggested solution is provided.
     *
     * @param lineNumber The line number in the tasks list that is corrupted.
     */
    public CorruptedSavedTasksException(int lineNumber) {
        super(String.format("Line %d in locally saved casper.tasks list is corrupted", lineNumber),
                "You may want to edit `casper.txt` manually, or delete the file to reset.");
    }
}
