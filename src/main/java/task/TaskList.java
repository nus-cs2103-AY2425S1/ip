package task;

import java.util.ArrayList;
import java.util.List; // Add this import statement
import java.util.stream.Collectors;

/**
 * The TaskList class manages a list of Task objects.
 * It provides methods to add, delete, mark, and unmark tasks,
 * as well as to retrieve the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with a list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a task to the TaskList.
     * Displays a confirmation message after adding the task.
     *
     * @param task The Task to be added.
     * @return A confirmation message after adding the task.
     */
    public String add(Task task) {
        taskList.add(task);
        return  "Got it. I've added this task:\n" + task + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String addMultipleTodos(ArrayList<Task> todos) {
        for (Task task: todos) {
            taskList.add(task);
        }
        return  "Got it. I've added the todos";
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     * Displays a confirmation message after deleting the task.
     *
     * @param index The index of the task to be deleted.
     */
    public String delete(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String find(String word) {
        List<Task> matchingTasks = this.taskList.stream()
                                            .filter(task -> task.getName()
                                            .contains(word))
                                            .collect(Collectors.toList());

        // for (int i = 0; i < this.taskList.size(); i++) {
        //     Task task = this.taskList.get(i);
        //     if (task.getName().contains(word)) {
        //         matchingTasks.add(task);
        //     }
        // }

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            String message = "Here are the matching tasks in your list:";
            for (int i = 0; i < matchingTasks.size(); i++) {
                message += "\n" + (i + 1) + "." + matchingTasks.get(i).toString();
            }
            return message;
        }
    }

    /**
     * Marks a task as done at the specified index.
     * Displays a confirmation message after marking the task.
     *
     * @param index The index of the task to be marked as done.
     */
    public String mark(int index) {
        Task task = taskList.get(index);
        task.mark();
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Marks a task as not done at the specified index.
     * Displays a confirmation message after unmarking the task.
     *
     * @param index The index of the task to be marked as not done.
     */
    public String unmark(int index) {
        Task task = taskList.get(index);
        task.unmark();
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Returns the string representation of the TaskList, listing all tasks.
     *
     * @return A string representation of the tasks in the list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1) + ". " + taskList.get(i) + "\n");
        }
        return sb.toString();
    }
}
