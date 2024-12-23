package colress.storage;

import java.io.IOException;

import colress.exception.FileCorruptedException;
import colress.tasklist.TaskList;

/**
 * An interface for storages of the Colress chatbot.
 */
public interface Storable {
    void loadTasks(TaskList taskList) throws IOException, FileCorruptedException;
    void createFile();
    void writeToTaskFile(TaskList taskList) throws IOException;
}
