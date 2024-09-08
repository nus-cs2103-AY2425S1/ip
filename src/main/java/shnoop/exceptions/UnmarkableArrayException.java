package shnoop.exceptions;

/**
 * Obsolete Exception representing an IndexOutOfBounds situation when accessing TaskList.
 */
public class UnmarkableArrayException extends Exception {

    public UnmarkableArrayException(String message) {
        super(message);
    }

    public UnmarkableArrayException() {
        super("✿ Shnoop ✿: Don't break your neck tryna creep a little sneak mark, there's no task with that number.");
    }
}
