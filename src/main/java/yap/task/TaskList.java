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
     * @return The message to print to the system about the marking of the task.
     */
    public String markTask(int taskIndex) {
        if (tasks.get(taskIndex) == null) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(taskIndex).markAsDone();
        saveCurrentTaskState();
        String lineToPrint = "I've marked this task as done:\n"
                + tasks.get(taskIndex);
        System.out.println(lineToPrint);
        return lineToPrint;
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param taskIndex The index of the task in the list.
     * @return The message to print to the system about the unmarking of the task.
     */
    public String unmarkTask(int taskIndex) {
        if (tasks.get(taskIndex) == null) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(taskIndex).markAsUndone();
        saveCurrentTaskState();
        String lineToPrint = "I've marked this task as not done:\n"
                + tasks.get(taskIndex);
        System.out.println(lineToPrint);
        return lineToPrint;
    }

    /**
     *
     * @param taskIndex The index of the task in the list.
     * @return The message to print to the system about the removal of the task.
     */
    public String deleteTask(int taskIndex) {
        Task tempTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        saveCurrentTaskState();
        String lineToPrint = "I've removed this task:\n" + tempTask.toString();
        System.out.println(lineToPrint);
        return lineToPrint;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add to the list.
     * @return The message to print to the system about the addition of the task.
     */
    public String addTask(Task task) {
        tasks.add(task);
        saveCurrentTaskState();
        String lineToPrint = "Added: " + tasks.get(tasks.size() - 1)
                + '\n' + "You now have " + tasks.size()
                + " tasks in the list";
        System.out.println(lineToPrint);
        return lineToPrint;
    }

    /**
     * Lists all the tasks in teh list.
     */
    public String listTasks() {
        StringBuilder lineToPrint = new StringBuilder(); // Use StringBuilder for efficient concatenation
        for (int input = 0; input < tasks.size(); ++input) {
            lineToPrint.append(input + 1).append(". ").append(tasks.get(input).toString()).append('\n');
        }
        String outputLine = lineToPrint.toString(); // Convert StringBuilder to String once
        System.out.println(outputLine);
        return outputLine;
    }

    /**
     * Lists all matching tasks based on the description of the task.
     *
     * @param description The description of the task.
     * @return A string of all matching tasks.
     */
    public String listMatchingDescriptionTasks(String description) {
        StringBuilder result = new StringBuilder();

        tasks.stream()
                .filter(task -> task.matchesTaskDescription(description))
                .forEach(task -> result.append(task).append("\n"));

        String resultString = result.toString(); // Convert StringBuilder to String
        System.out.println(resultString); // Print the result
        return resultString; // Return the result
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
