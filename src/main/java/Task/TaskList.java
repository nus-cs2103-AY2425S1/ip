package Task;

import Utilities.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;


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
            ui.noTask();
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
            ui.noTask();
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
            ui.noTask();
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
        Task newTask = switch (type) {
            case TODO -> new ToDo(desc);
            case DEADLINE -> new Deadline(desc, args[0]);
            case EVENT -> new Event(desc, args[0], args[1]);
        };
        tasks.add(newTask);
        ui.alterTask(tasks, newTask, true);
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

        ui.foundTask(foundTasks);
    }
}
