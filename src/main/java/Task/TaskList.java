package Task;

import Utilities.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    Ui ui;

    public TaskList(){
        this.ui = new Ui();
    }

    public void markTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markDone();
            ui.markTask(tasks, index, true);
        } else {
            ui.noTask();
        }
    }

    public void unmarkTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markNotDone();
            ui.markTask(tasks, index, false);
        } else {
            ui.noTask();
        }
    }

    public void deleteTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            ui.alterTask(tasks, removedTask, false);
        } else {
            ui.noTask();
        }

    }

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
