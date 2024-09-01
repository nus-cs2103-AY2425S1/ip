package duke;

/**
 * Represents a message formatter.
 */
@FunctionalInterface
public interface Formatter {
    public String format(String message);
}
