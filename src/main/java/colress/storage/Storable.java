package colress.storage;

import java.io.IOException;

import colress.exception.FileCorruptedException;
import colress.tasklist.TaskListable;

/**
 * An interface for storages of the Colress chatbot.
 */
public interface Storable {
    void loadTasks(TaskListable taskList) throws IOException, FileCorruptedException;
    void createFile();
    void writeToTaskFile(TaskListable taskList) throws IOException;
}
