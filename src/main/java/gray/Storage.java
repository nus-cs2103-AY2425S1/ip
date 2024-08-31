package gray;

import java.io.File;
import java.io.IOException;

/**
 * A storage that manages file-related IO procedures.
 */
public class Storage {

    private final File saveFile;

    /**
     * Constructor to initialise Storage.
     * Takes in a file path to indicate where to save the tasks to.
     *
     * @param saveFilePath
     */
    public Storage(String saveFilePath) {
        this.saveFile = new File(saveFilePath);
    }

    /**
     * Serialises a list of tasks to its associated save filepath.
     *
     * @param taskList
     */
    public void saveTasks(TaskList taskList) {
        saveFile.getParentFile().mkdirs();
        try {
            Utility.serialise(saveFile, taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserialise the list of tasks that has been serialised from its associated save filepath.
     *
     * @return the task list parsed
     */
    public TaskList loadTasks() {
        saveFile.getParentFile().mkdirs();
        if (!saveFile.exists()) {
            return new TaskList();
        }
        try {
            return (TaskList) Utility.deserialise(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
