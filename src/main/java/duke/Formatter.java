package duke;

/**
 * Represents a message formatter.
 */
@FunctionalInterface
public interface Formatter {
    String format(String message);
}
