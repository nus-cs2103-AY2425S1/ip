package Tasks;

import Storage.FileStorage;
import Tasks.Task;
import Utils.DateTime;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

public class TaskManager {
    private final List<Task> tasks;
    private final FileStorage storage;
    public TaskManager(FileStorage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
    }
    private String getItem(int index) {
        return index + 1 + ". " + tasks.get(index);
    }
    private void print(String str) {
        System.out.println("         " + str);
    }
    private void printStatus() {
        String taskStr = tasks.size() > 1 ? "tasks" : "task";
        String message = String.format("Now you have %d %s in the list", tasks.size(), taskStr);
        print(message);
    }
    public void listTasks() {
        print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            print(getItem(i));
        }
    }
    public void completeTask(int index) {
        Task task = tasks.get(index);
        task.complete();
        saveList();
        print("Nice! I've marked this task as done:");
        print(getItem(index));
    }
    public void unCompleteTask(int index) {
        Task task = tasks.get(index);
        task.unComplete();
        saveList();
        print("OK, I've marked this task as not done yet:");
        print(getItem(index));
    }
    public void addTask(Task task) {
        tasks.add(task);
        saveList();
        print("Got it. I've added this task:");
        printStatus();
        print(getItem(tasks.size() - 1));
    }
    public void deleteTask(int index) {
        Task task = tasks.remove(index);
        saveList();
        print("Noted. I've removed this task:");
        print(task.toString());
        printStatus();
    }
    private void saveList() {
        storage.write(this.toString());
    }
    public void getTasksByDate(LocalDate date) {
        print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isOccuringOnDate(date)) {
                print(getItem(i));
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append(getItem(i)).append("\n");
        }
        return str.toString();
    }
}
