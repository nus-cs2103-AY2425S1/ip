package vuewee;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

import vuewee.task.TaskList;

/**
 * The TasksStorage class represents a storage for tasks. Only once instance of
 * TasksStorage is created, and it reads and writes tasks to a file.
 */
class TasksStorage {
    private static final Path DATA_DIRECTORY = Paths.get("data");
    private final Path TASKS_FILE_PATH = Paths.get(DATA_DIRECTORY.toString(), "tasks.txt");
    private static TasksStorage instance;

    private TasksStorage() {
        try {
            Files.createDirectories(DATA_DIRECTORY);
            if (!Files.exists(TASKS_FILE_PATH)) {
                Files.createFile(TASKS_FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("Error creating data directory");
        }
    }

    /**
     * Returns an instance of TasksStorage.
     *
     * @return an instance of TasksStorage
     */
    public static TasksStorage getInstance() {
        if (instance == null) {
            instance = new TasksStorage();
        }
        return instance;
    }

    public TaskList readTasks() {
        try {
            String tasksStore = Files.readString(TASKS_FILE_PATH);
            return TaskList.deserialize(tasksStore);
        } catch (IOException e) {
            System.out.println("Error reading tasks from file");
            return new TaskList();
        }
    }

    public void storeTasks(TaskList list) {
        try {
            Files.writeString(TASKS_FILE_PATH, list.serialize());
        } catch (IOException e) {
            System.out.println("Error writing tasks to file");
        }
    }
}
