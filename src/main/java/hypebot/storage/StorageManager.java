package hypebot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import hypebot.command.Command;
import hypebot.main.HypeBot;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;

/**
 * Represents a {@code StorageManager} which {@link HypeBot} and {@link Command} objects
 * have access to.
 * <p>Triggers loading and saving of {@link Task}s in the {@link HypeBot}'s {@link Tasklist}
 * into/from a {@link File} located through a given {@link String} {@code filePath}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see TasklistDecoder
 * @see TasklistEncoder
 */
public class StorageManager {
    /** {@link File} object {@link Task}s are read / written to. */
    private final File tasklistFile;

    /**
     * Takes in a {@link String} filepath to locate the {@link File} with
     * {@link Task} data, and creates a new {@code StorageManager} object
     * with the specified {@link File}.
     *
     * @param filePath {@link String} filepath with saved {@link Task} data.
     */
    public StorageManager(String filePath) {
        tasklistFile = new File(filePath);
        try {
            if (!tasklistFile.exists()) {
                tasklistFile.getParentFile().mkdirs();
                tasklistFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create tasklist file", e);
        }
    }

    /**
     * Creates a new {@link TasklistDecoder} with the {@link File} {@code tasklistFile},
     * and returns the {@link Tasklist} created by {@code TasklistDecoder.decode()}.
     *
     * @return {@link Tasklist} of {@link Task}s saved in {@link File} {@code tasklistFile}.
     * @throws FileNotFoundException If specified {@link File} not found.
     * @throws RuntimeException If any saved data is not in expected format.
     */
    public Tasklist load() throws FileNotFoundException, RuntimeException {
        TasklistDecoder decoder = new TasklistDecoder(tasklistFile);
        return decoder.decode();
    }

    /**
     * Takes in a {@link Tasklist} to encode and save to the {@link File} specified.
     * Creates a new {@link TasklistEncoder} and calls {@code TasklistEncoder.encode()}
     * to save {@link Task}s in {@link Tasklist}.
     *
     * @param tasks {@link Tasklist} of {@link Task}s to save.
     * @throws IOException If specified {@link File} not found.
     */
    public void save(Tasklist tasks) throws IOException {
        TasklistEncoder encoder = new TasklistEncoder(tasklistFile, tasks);
        encoder.encode();
    }
}
