package task;

import utility.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Manages the list of tasks, including adding, removing, marking, and unmarking tasks.
 */
public class TaskList {

    private Task switchTask(ArrayList<Task> tasks, TaskType type, String desc, LocalDateTime[] args) {
        return switch (type) {
            case TODO -> new ToDo(desc);
            case DEADLINE -> new Deadline(desc, args[0]);
            case EVENT -> new Event(desc, args[0], args[1]);
        };
    }

    /**
     * Marks a task as done.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task to mark as done.
     */
    public String markTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markDone();
            return "  " + tasks.get(index - 1);
        } else {
            return "No Task Found";
        }
    }

    /**
     * Unmarks a task, indicating it is not done.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task to unmark.
     */
    public String unmarkTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markNotDone();
            return "  " + tasks.get(index - 1);
        } else {
            return "No Task Found";
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task to delete.
     */
    public String deleteTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            return "  " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.";
        } else {
            return "No Task Found";
        }

    }

    /**
     * Adds a new task to the list.
     *
     * @param tasks The list of tasks.
     * @param type  The type of the task (TODO, DEADLINE, EVENT).
     * @param desc  The description of the task.
     * @param args  Additional arguments for DEADLINE or EVENT tasks (e.g., due date, start and end times).
     */
    public String addTask(ArrayList<Task> tasks, TaskType type, String desc, LocalDateTime... args) {
        Task newTask = switchTask(tasks, type, desc, args);
        if (new Validator().detectDuplicates(tasks, newTask)){
            return "This task exists!";
        }
        tasks.add(newTask);
        return "  " + newTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Searches for tasks that contain the specified keyword and displays the matching tasks.
     *
     * @param tasks   The list of tasks to search through.
     * @param keyword The keyword to search for in the task descriptions.
     */
    public String findTask(ArrayList<Task> tasks, String keyword) {
        String foundTasks = tasks.stream()
                .filter(task -> task.getDesc().contains(keyword))
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task.toString())
                .collect(Collectors.joining("\n"));

        if (foundTasks.isEmpty()) {
            return "No matching tasks found.";
        }
        return foundTasks;
    }
}
