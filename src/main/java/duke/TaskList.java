package duke;

import duke.Exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A class that handle storing and dealing of task in ArrayList.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        boolean isValidIndex = index >= 0 && index < tasks.size();

        if (!isValidIndex) {
            throw new DukeException("Invalid nameless.task number.");
        }

        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
