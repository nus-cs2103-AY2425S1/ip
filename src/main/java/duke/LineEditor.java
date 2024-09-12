package duke;

/**
 * Represents a line editor for task storage.
 */
@FunctionalInterface
public interface LineEditor {
    String editLine(String msg) throws BobException;
}
