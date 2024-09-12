package yap.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import yap.storage.Storage;
import yap.storage.BadDataFormatException;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        try {
            this.storage = storage;
            this.tasks = storage.readTasksFromFile();
        } catch (BadDataFormatException | FileNotFoundException exception) {
            this.tasks = new ArrayList<Task>();
        }
    }

    public void markTask(int taskIndex) {
        if (tasks.get(taskIndex) == null) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(taskIndex).markAsDone();
        saveCurrentTaskState();
        System.out.println("I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
    }

    public void unmarkTask(int taskIndex) {
        if (tasks.get(taskIndex) == null) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(taskIndex).markAsUndone();
        saveCurrentTaskState();
        System.out.println("I've marked this task as not done:");
        System.out.println(tasks.get(taskIndex));
    }

    public void deleteTask(int taskIndex) {
        Task tempTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        saveCurrentTaskState();
        System.out.println("I've removed this task:");
        System.out.println(tempTask);
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveCurrentTaskState();
        System.out.println("Added: " + tasks.get(tasks.size() - 1));
        System.out.printf("You now have %d tasks in the list%n", tasks.size());
    }

    public void listTasks() {
        for (int input = 0; input < tasks.size(); ++input) {
            System.out.println((input + 1) + ". " + tasks.get(input).toString());
        }
    }

    public void listMatchingDescriptionTasks(String description) {
        tasks.stream()
                .filter(task -> task.matchesTaskDescription(description))
                .toList()
                .forEach(task -> System.out.println(task.toString()));
    }

    private void saveCurrentTaskState() {
        try {
            storage.writeTasksToFile(tasks);
        } catch (IOException exception) {
            System.out.println("Error saving tasks to hard disk!");
        }
    }
}
