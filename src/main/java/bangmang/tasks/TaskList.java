package LittleMissHelpful.Tasks;
import java.util.ArrayList;

import LittleMissHelpful.Exception.InvalidTaskFormatException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        /**
         * Adds task to tasklist
         */
        tasks.add(task);
    }

    public void delete(int index) throws InvalidTaskFormatException {
        /**
         * Deletes task from task list given index
         */
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Task number out of range.");
        }
        tasks.remove(index);
    }

    public Task get(int index) throws InvalidTaskFormatException {
        /**
         * Returns task from tasklist given
         */
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Task number out of range.");
        }
        return tasks.get(index);
    }

    public int size() {
        /**
         * Returns number of tasks in list
         */
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        /**
         * Returns ArrayList of tasks
         */
        return tasks;
    }

    public void markTask(int index) throws InvalidTaskFormatException {
        /**
         * Marks task as done
         */
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Task number out of range.");
        }
        Task t = tasks.get(index).markTask();
        tasks.set(index, t);
    }

    public void unmarkTask(int index) throws InvalidTaskFormatException {
        /**
         * Marks task as undone
         */
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskFormatException("Task number out of range.");
        }
        Task t = tasks.get(index).unmarkTask();
        tasks.set(index, t);
    }
}
