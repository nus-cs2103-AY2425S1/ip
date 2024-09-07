package task;

import utility.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Manages the list of tasks, including adding, removing, marking, and unmarking tasks.
 */
public class TaskList {

    Ui ui;

    public TaskList(){
        this.ui = new Ui();
    }

    /**
     * Marks a task as done.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task to mark as done.
     */
    public void markTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markDone();
            ui.markTask(tasks, index, true);
        } else {
            ui.printNoTask();
        }
    }

    /**
     * Unmarks a task, indicating it is not done.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task to unmark.
     */
    public void unmarkTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markNotDone();
            ui.markTask(tasks, index, false);
        } else {
            ui.printNoTask();
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task to delete.
     */
    public void deleteTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            ui.alterTask(tasks, removedTask, false);
        } else {
            ui.printNoTask();
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
    public void addTask(ArrayList<Task> tasks, TaskType type, String desc, LocalDateTime... args) {
        Task newTask = switchTask(tasks, type, desc, args);
        if (detectDuplicates(tasks, newTask)){
            ui.printExists();
        }
        tasks.add(newTask);
        ui.alterTask(tasks, newTask, true);
    }

    private Task switchTask(ArrayList<Task> tasks, TaskType type, String desc, LocalDateTime[] args) {
        return switch (type) {
            case TODO -> new ToDo(desc);
            case DEADLINE -> new Deadline(desc, args[0]);
            case EVENT -> new Event(desc, args[0], args[1]);
        };
    }

    /**
     * Searches for tasks that contain the specified keyword and displays the matching tasks.
     *
     * @param tasks   The list of tasks to search through.
     * @param keyword The keyword to search for in the task descriptions.
     */
    public void findTask(ArrayList<Task> tasks, String keyword){

        ArrayList<String> foundTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDesc().contains(keyword)) {
                foundTasks.add((i + 1) + ". " + tasks.get(i));
            }
        }
        ui.printFoundTasks(foundTasks);
    }


    /**
     * Marks a task as done.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task to mark as done.
     */
    public String markTaskUI(ArrayList<Task> tasks, int index) {
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
    public String unmarkTaskUI(ArrayList<Task> tasks, int index) {
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
    public String deleteTaskUI(ArrayList<Task> tasks, int index) {
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
    public String addTaskUI(ArrayList<Task> tasks, TaskType type, String desc, LocalDateTime... args) {
        Task newTask = switchTask(tasks, type, desc, args);
        if (detectDuplicates(tasks, newTask)){
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
    public String findTaskUI(ArrayList<Task> tasks, String keyword) {
        String foundTasks = tasks.stream()
                .filter(task -> task.getDesc().contains(keyword))
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task.toString())
                .collect(Collectors.joining("\n"));

        if (foundTasks.isEmpty()) {
            return "No matching tasks found.";
        }
        return foundTasks;
    }

    /**
     * Detects if there is a duplicate task in the list.
     *
     * @param tasks The list of tasks.
     * @param newTask The new task to check for duplicates.
     * @return True if a duplicate task is found, false otherwise.
     */
    public Boolean detectDuplicates(ArrayList<Task> tasks, Task newTask) {
        for (Task task : tasks) {
            if (task.getDesc().equals(newTask.getDesc())) {
                if (task instanceof Deadline && newTask instanceof Deadline) {
                    if (((Deadline) task).getBy().equals(((Deadline) newTask).getBy())) {
                        return true;
                    }
                }
                else if (task instanceof Event existingEvent && newTask instanceof Event newEvent) {
                    if (existingEvent.getFrom().equals(newEvent.getFrom()) &&
                            existingEvent.getTo().equals(newEvent.getTo())) {
                        return true;
                    }
                }
                // For TODO tasks, a matching description is enough to count as duplicate
                else if (task instanceof ToDo && newTask instanceof ToDo) {
                    return true; // Duplicate ToDo task
                }
            }
        }
        return false; // No duplicates found
    }
}
