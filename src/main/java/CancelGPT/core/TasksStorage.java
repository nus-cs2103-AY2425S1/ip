package CancelGPT.core;

import CancelGPT.exception.task.InvalidTaskException;

import CancelGPT.task.Task;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class TasksStorage {
    private final CancelGPT CHATBOT;
    private final Path TASKS_STORAGE_PATH;

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

    public void saveTasks() throws IOException {
        FileWriter tasksStorageSaver = new FileWriter(this.TASKS_STORAGE_PATH.toString());
        for (Task task : this.CHATBOT.getTasks()) {
            tasksStorageSaver.write(task.getSavedDataString());
            tasksStorageSaver.write("\n");
        }
        tasksStorageSaver.close();
    }
}
