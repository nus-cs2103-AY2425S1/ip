package echobot.io;

/**
 * Saveable interface.
 */
public interface Saveable {
    /**
     * Converts a savable item to string for persistent storage.
     *
     * @return The string of the task list for persistent storage.
     */
    String save();
}
