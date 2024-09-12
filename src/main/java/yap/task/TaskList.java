package yap.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import yap.storage.BadDataFormatException;
import yap.storage.Storage;

/**
 * Stores the list of tasks the user currently has input
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs a task list.
     *
     * @param storage The storage object used to read and write the tasks in the task list to and from.
     */
    public TaskList(Storage storage) {
        try {
            this.storage = storage;
            this.tasks = storage.readTasksFromFile();
        } catch (BadDataFormatException | FileNotFoundException exception) {
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param taskIndex The index of the task in the list.
     */
    public void markTask(int taskIndex) {
        if (tasks.get(taskIndex) == null) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(taskIndex).markAsDone();
        saveCurrentTaskState();
        System.out.println("I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param taskIndex The index of the task in the list.
     */
    public void unmarkTask(int taskIndex) {
        if (tasks.get(taskIndex) == null) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(taskIndex).markAsUndone();
        saveCurrentTaskState();
        System.out.println("I've marked this task as not done:");
        System.out.println(tasks.get(taskIndex));
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskIndex The index of the task in the list.
     */
    public void deleteTask(int taskIndex) {
        Task tempTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        saveCurrentTaskState();
        System.out.println("I've removed this task:");
        System.out.println(tempTask);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        saveCurrentTaskState();
        System.out.println("Added: " + tasks.get(tasks.size() - 1));
        System.out.printf("You now have %d tasks in the list%n", tasks.size());
    }

    /**
     * Lists all the tasks in teh list.
     */
    public void listTasks() {
        for (int input = 0; input < tasks.size(); ++input) {
            System.out.println((input + 1) + ". " + tasks.get(input).toString());
        }
    }

    /**
     * Lists all matching tasks based on the description of the task.
     *
     * @param description The description of the task.
     */
    public void listMatchingDescriptionTasks(String description) {
        tasks.stream()
                .filter(task -> task.matchesTaskDescription(description))
                .toList()
                .forEach(task -> System.out.println(task.toString()));
    }

    /**
     * Saves the current task list to the storage object.
     */
    private void saveCurrentTaskState() {
        try {
            storage.writeTasksToFile(tasks);
        } catch (IOException exception) {
            System.out.println("Error saving tasks to hard disk!");
        }
    }
}
