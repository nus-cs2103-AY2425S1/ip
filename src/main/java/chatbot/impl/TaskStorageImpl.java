package chatbot.impl;

import chatbot.Task;
import chatbot.TaskStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class TaskStorageImpl implements TaskStorage {

    private static final Path FILE_PATH = Paths.get("./data/tasks.txt");

    private final ArrayList<Task> tasks;

    public TaskStorageImpl() {
        tasks = new ArrayList<>();

        try {
            Files.createDirectories(Paths.get("./data"));
        } catch (IOException e) {
            // Todo: Proper exception handling
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);

        try {
            Files.write(FILE_PATH,
                    (task.serialize() + "\n").getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            // Todo: Proper exception handling
            e.printStackTrace();
        }
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
    }

    @Override
    public void setTaskAsNotDone(int taskIdx) {
        tasks.get(taskIdx).setDone(false);
    }

    @Override
    public void deleteTask(int taskIdx) {
        tasks.remove(taskIdx);
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

}
