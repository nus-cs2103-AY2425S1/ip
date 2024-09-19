package socchat;

import java.util.ArrayList;

import socchat.storage.Storage;
import socchat.task.Task;

/**
 * The TaskList class manages a list of tasks, allowing users to add, delete,
 * mark, unmark, list, and find tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Default constructor that initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor that initializes the task list with existing tasks.
     *
     * @param tasks the list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Deletes a task from the task list based on the index provided as a string.
     *
     * @param indexString the index of the task to delete (1-based index)
     * @throws SocchatException if the index is invalid or not a valid number
     * @return a string confirming that the task has been successfully added
     */
    public String delete(String indexString) throws SocchatException {
        int taskIndex = parseTaskIndex(indexString);
        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);

        String respond = "";
        respond += ("Deleted " + "\"" + task.toString() + "\"" + "\n");
        respond += ("Now you have " + tasks.size() + " task(s). Meow~\n");

        Storage.update(tasks, false);

        return respond;
    }

    /**
     * Lists all tasks in the task list.
     */
    public String list() {
        StringBuilder respond = new StringBuilder("Meow~ Here is your task list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            respond.append((i + 1)).append(": ").append(curr.toString()).append("\n");
        }
        return respond.toString();
    }

    public String addTask(Task t) {
            tasks.add(t);
            Storage.update(tasks, true);
            return addingTaskAcknowledgement(t);
    }

    /**
     * Creates a response string confirming that the task has been successfully added
     */
    public String addingTaskAcknowledgement(Task task) {
        return "Meow~ added: " + task + "\nNow you have " + tasks.size() + " task(s).\n";
    }

    /**
     * Marks or unmarks a task in the task list.
     *
     * @param indexString the index of the task to mark or unmark (1-based index)
     * @param mark whether to mark or unmark the task
     * @throws SocchatException if the index is invalid or not a valid number
     */
    public String setMark(String indexString, Boolean mark) throws SocchatException {
        String respond = "";
        int taskIndex = parseTaskIndex(indexString);

        if (mark) {
            respond =  tasks.get(taskIndex).mark();
        } else {
            respond =  tasks.get(taskIndex).unmark();
        }

        Storage.update(tasks, false);
        return respond;

    }

    /**
     * Finds tasks that contain a keyword in their description.
     *
     * @param keyword the keyword to search for in the task descriptions
     * @throws SocchatException if no tasks contain the keyword
     */
    public String find(String keyword) throws SocchatException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }

        StringBuilder respond = new StringBuilder();
        respond.append("Found ").append(foundTasks.size()).append(" task(s). Meow~\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            Task curr = foundTasks.get(i);
            respond.append((i + 1)).append(": ");
            respond.append(curr.toString()).append("\n");
        }
        return respond.toString();
    }

    /**
     * Helper method to parse the task index from a string and handle exceptions.
     *
     * @param indexString the index string to parse
     * @return the parsed task index (0-based)
     * @throws SocchatException if the index is invalid or out of bounds
     */
    private int parseTaskIndex(String indexString) throws SocchatException {
        try {
            int taskIndex = Integer.parseInt(indexString) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            return taskIndex;
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid task number. Meow~");
        } catch (NumberFormatException e) {
            throw new SocchatException("Please enter a valid task number. Meow~");
        }
    }


}
