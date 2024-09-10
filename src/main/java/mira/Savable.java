package mira;

import java.io.IOException;

/**
 * Represents an interface for commands that can be saved to storage.
 */
public interface Savable {
    /**
     * Saves the command's state to the specified storage.
     *
     * @param storage The storage to save the command's state.
     * @throws IOException If there is an error in file operations.
     */
    void save(Storage storage) throws IOException;
}
