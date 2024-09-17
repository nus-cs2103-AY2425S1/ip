package hypebot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import hypebot.exception.datetime.HypeBotDateTimeParseException;
import hypebot.tasklist.Tasklist;

/**
 * Represents a StorageManager which HypeBot and Command objects have access to.
 * <p>Triggers loading and saving of Tasks into/from the HypeBot's tasks by allocating
 * these tasks to the TaskListDecoder and TasklistEncoder respectively.</p>
 *
 * @author Youngseo park (@youngseopark05)
 */
public class StorageManager {
    private final File tasklistFile;

    /**
     * Takes in a String filepath to locate the file with task data, and creates
     * a new StorageManager object with the specified File.
     *
     * @param filePath String filepath where the task data in .txt form is located.
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
     * Creates a new TasklistDecoder with the File specified to contain task data,
     * and returns the ArrayList of Tasks created by TasklistDecoder.decode() process.
     *
     * @return Tasklist of Tasks saved in specified file.
     * @throws FileNotFoundException If specified file not found.
     * @throws RuntimeException If any saved data is not in expected format.
     */
    public Tasklist load() throws FileNotFoundException, RuntimeException {
        TasklistDecoder decoder = new TasklistDecoder(tasklistFile);
        return decoder.decode();
    }

    /**
     * Takes in a Tasklist with Tasks to encode and save to the File specified.
     * Creates a new TasklistEncoder and calls TasklistEncoder.encode()
     * to save Tasks from Tasklist.
     *
     * @param tasks Tasklist containing Tasks to save.
     * @throws IOException If specified file not found.
     */
    public void save(Tasklist tasks) throws IOException {
        TasklistEncoder encoder = new TasklistEncoder(tasklistFile, tasks);
        encoder.encode();
    }
}
