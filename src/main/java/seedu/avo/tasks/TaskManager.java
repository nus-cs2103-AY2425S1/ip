package seedu.avo.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import seedu.avo.storage.Storage;

public class TaskManager {
    private final List<Task> tasks;
    private final Storage<Task, String> storage;
    public TaskManager(Storage<Task, String> storage) {
        tasks = storage.fetchAll();
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
        printTaskCount(tasks.size());
        printTasksFromIndexes(IntStream.range(0, tasks.size())
                .boxed().toList());
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
        storage.write(this.formatData());
    }
    public void getTasksByDate(LocalDate date) {
        List<Integer> indexes = filterByDate(date);
        printTaskCount(indexes.size());
        printTasksFromIndexes(indexes);
    }
    public String formatData() {
        StringBuilder str = new StringBuilder();
        for (Task task : tasks) {
            str.append(task.formatData()).append("\n");
        }
        return str.toString();
    }
    public void getTasksByName(String name) {
        List<Integer> indexes = filterByName(name);
        printTaskCount(indexes.size());
        printTasksFromIndexes(indexes);
    }
    private List<Integer> filterByName(String name) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).matchName(name)) {
                result.add(i);
            }
        }
        return result;
    }
    private List<Integer> filterByDate(LocalDate date) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isOccurringOnDate(date)) {
                result.add(i);
            }
        }
        return result;
    }
    private void printTasksFromIndexes(List<Integer> indexes) {
        for (Integer index: indexes) {
            print(getItem(index));
        }
    }
    private void printTaskCount(int count) {
        if (count == 0) {
            print("You have no tasks.");
        } else if (count == 1) {
            print("You have one task.");
        } else {
            print(String.format("You have %s tasks.", count));
        }
    }
}
