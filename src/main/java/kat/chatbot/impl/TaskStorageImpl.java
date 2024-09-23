package kat.chatbot.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import kat.chatbot.TaskStorage;
import kat.tasks.Task;
import kat.tasks.impl.AbstractTask;

/**
 * Implements the TaskStorage interface to manage tasks in a custom file-based storage system.
 */
public class TaskStorageImpl implements TaskStorage {

    private static final Path FILE_PATH = Paths.get("./data/tasks.txt");

    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskStorageImpl.
     * Initializes the task list and loads existing tasks from the file.
     */
    public TaskStorageImpl() {
        tasks = new ArrayList<>();

        try {
            Files.createDirectories(Paths.get("./data"));
            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }

            List<String> lines = Files.readAllLines(FILE_PATH);
            for (String line : lines) {
                tasks.add(AbstractTask.deserialize(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation adds the task to the in-memory list and saves it to the file.
     */
    @Override
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";

        tasks.add(task);
        saveTasks();
    }

    @Override
    public Task getTask(int taskIdx) {
        return tasks.get(taskIdx);
    }

    @Override
    public int getSize() {
        return tasks.size();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation marks the task as done in the in-memory list and saves the changes.
     */
    @Override
    public void setTaskAsDone(int taskIdx) {
        tasks.get(taskIdx).setDone(true);
        saveTasks();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation marks the task as not done in the in-memory list and saves the changes.
     */
    @Override
    public void setTaskAsNotDone(int taskIdx) {
        tasks.get(taskIdx).setDone(false);
        saveTasks();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation removes the task from the in-memory list and saves the changes.
     */
    @Override
    public void deleteTask(int taskIdx) {
        tasks.remove(taskIdx);
        saveTasks();
    }

    @Override
    public List<Task> findTasks(String keyword) {
        return tasks
                .stream()
                .filter(task -> task.toString().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    /**
     * Returns a string representation of all tasks in the storage.
     *
     * @return A formatted string containing all tasks.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        for (Task task : tasks) {
            stringBuilder.append(index).append(". ").append(task.toString()).append("\n");
            index++;
        }
        return stringBuilder.toString().trim();
    }

    /**
     * Saves all tasks to the file.
     * This method serializes each task and writes it to the file.
     */
    private void saveTasks() {
        try {
            List<String> serializedTasks = tasks
                    .stream()
                    .map(Task::serialize)
                    .toList();

            Files.write(FILE_PATH, serializedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
