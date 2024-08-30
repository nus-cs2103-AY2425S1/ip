package chatbot.impl;

import chatbot.Task;
import chatbot.TaskStorage;
import chatbot.impl.tasks.AbstractTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskStorageImpl implements TaskStorage {

    private static final Path FILE_PATH = Paths.get("./data/tasks.txt");

    private final ArrayList<Task> tasks;

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
            // Todo: Proper exception handling
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(Task task) {
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

    @Override
    public void setTaskAsDone(int taskIdx) {
        tasks.get(taskIdx).setDone(true);
        saveTasks();
    }

    @Override
    public void setTaskAsNotDone(int taskIdx) {
        tasks.get(taskIdx).setDone(false);
        saveTasks();
    }

    @Override
    public void deleteTask(int taskIdx) {
        tasks.remove(taskIdx);
        saveTasks();
    }

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

    // Todo: Optimization
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
