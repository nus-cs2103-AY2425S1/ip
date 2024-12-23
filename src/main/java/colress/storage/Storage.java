package colress.storage;

import java.io.File;
import java.io.IOException;

import colress.exception.FileCorruptedException;
import colress.tasklist.TaskList;

/**
 * An interface for storages of the Colress chatbot.
 */
public abstract class Storage {
    private final File taskFile;

    public Storage(File taskFile) {
        this.taskFile = taskFile;
    }

    public File getTaskFile() {
        return taskFile;
    }

    public abstract void loadTasks(TaskList taskList) throws IOException, FileCorruptedException;
    public abstract void createFile();
    public abstract void writeToTaskFile(TaskList taskList) throws IOException;
}
