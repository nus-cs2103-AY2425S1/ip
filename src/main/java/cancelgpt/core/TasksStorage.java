package cancelgpt.core;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import cancelgpt.exception.task.InvalidTaskException;
import cancelgpt.task.Task;

/**
 * Represents the storage that saves the tasks persistently.
 */
public class TasksStorage {
    private final CancelGpt chatbot;
    private final Path tasksStoragePath;

    /**
     * Initialises TasksStorage for the chatbot, using
     * the tasks storage directory path given.
     * If the tasks storage file for the chatbot does not exist, creates the file,
     * along with any required parent directories.
     * Else, uses the file for the storage.
     *
     * @param chatbot                   the chatbot the TasksStorage is storing for
     * @param tasksStorageDirectoryPath the storage directory path to store the data
     * @throws IOException if the storage directory cannot be found or the file to store data cannot
     *                     be created.
     */
    public TasksStorage(CancelGpt chatbot, Path tasksStorageDirectoryPath) throws IOException {
        this.chatbot = chatbot;

        if (!Files.exists(tasksStorageDirectoryPath)) {
            Files.createDirectories(tasksStorageDirectoryPath);
        }

        this.tasksStoragePath = Paths.get(tasksStorageDirectoryPath.toString(), chatbot.getName() + ".txt");
        if (!Files.exists(this.tasksStoragePath)) {
            Files.createFile(this.tasksStoragePath);
        }

        this.readTaskStorageToTasksList();
    }

    /**
     * Reads tasks from the storage file to the storage.
     *
     * @throws IOException if tasks in the file to read from cannot be read
     */
    private void readTaskStorageToTasksList() throws IOException {
        Scanner tasksStorageReader = new Scanner(this.tasksStoragePath);
        while (tasksStorageReader.hasNextLine()) {
            try {
                this.chatbot.addToTaskList(Task.getTaskFromSavedDataString(tasksStorageReader.nextLine()));
            } catch (InvalidTaskException e) {
                System.out.println(e.getMessage());
            }
        }
        tasksStorageReader.close();
    }

    /**
     * Writes the tasks in TasksStorage persistently to the file to be stored.
     *
     * @throws IOException if the tasks in the TasksStorage cannot be written to the storage file
     */
    public void saveTasks() throws IOException {
        FileWriter tasksStorageSaver = new FileWriter(this.tasksStoragePath.toString());
        for (Task task : this.chatbot.getTasks()) {
            tasksStorageSaver.write(task.getSavedDataString());
            tasksStorageSaver.write("\n");
        }
        tasksStorageSaver.close();
    }
}
