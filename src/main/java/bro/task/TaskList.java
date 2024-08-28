package bro.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// bro.task.TaskList class encapsulates a task list tracked by bro.Bro Bot
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task deleteTask(int taskId) {
        return tasks.remove(taskId);
    }

    public Task markTask(int taskId) throws Exception {
        Task task = tasks.get(taskId);
        task.markTask();
        return task;
    }

    public Task unmarkTask(int taskId) throws Exception {
        Task task = tasks.get(taskId);
        task.unmarkTask();
        return task;
    }

    public void printAllTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void writeAllTasks() {
        File file = new File("data/BroData.txt");

        file.getParentFile().mkdirs(); // Create directory if it does not exist

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < tasks.size(); i++) {
                writer.write((i + 1) + ". " + tasks.get(i));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }


    public int getNumberOfTask() {
        return tasks.size();
    }
}
