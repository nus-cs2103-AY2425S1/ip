package CancelGPT.core;

import CancelGPT.exception.task.InvalidTaskException;

import CancelGPT.task.Task;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

/**
 * Represents the storage that saves the tasks persistently.
 */
public class TasksStorage {
    private final CancelGPT CHATBOT;
    private final Path TASKS_STORAGE_PATH;

    /**
     * Initialises TasksStorage for the chatbot, using
     * the tasks storage directory path given.
     * If the tasks storage file for the chatbot does not exist, creates the file,
     * along with any required parent directories.
     * Else, uses the file for the storage.
     * 
     * @param chatbot the chatbot the TasksStorage is storing for
     * @param tasksStorageDirectoryPath the storage directory path to store the data
     * @throws IOException if the storage directory cannot be found or the file to store data cannot
     * be created.
     */
    public TasksStorage(CancelGPT chatbot, Path tasksStorageDirectoryPath) throws IOException {
        this.CHATBOT = chatbot;

        if (!Files.exists(tasksStorageDirectoryPath)) {
            Files.createDirectories(tasksStorageDirectoryPath);
        }
        
        this.TASKS_STORAGE_PATH = Paths.get(tasksStorageDirectoryPath.toString(), chatbot.getName() + ".txt");
        if (!Files.exists(this.TASKS_STORAGE_PATH)) {
            Files.createFile(this.TASKS_STORAGE_PATH);
        }
        
        this.readTaskStorageToTasksList();
    }

    /**
     * Reads tasks from the storage file to the storage.
     * 
     * @throws IOException if tasks in the file to read from cannot be read
     */
    private void readTaskStorageToTasksList() throws IOException {
        Scanner tasksStorageReader = new Scanner(this.TASKS_STORAGE_PATH);
        while (tasksStorageReader.hasNextLine()) {
            try {
                this.CHATBOT.addToTaskList(Task.getTaskFromSavedDataString(tasksStorageReader.nextLine()));
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
        FileWriter tasksStorageSaver = new FileWriter(this.TASKS_STORAGE_PATH.toString());
        for (Task task : this.CHATBOT.getTasks()) {
            tasksStorageSaver.write(task.getSavedDataString());
            tasksStorageSaver.write("\n");
        }
        tasksStorageSaver.close();
    }
}
