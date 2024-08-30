package task;

import exceptions.BuddyException;
import task.Task;

import java.util.ArrayList;
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws BuddyException {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new BuddyException("I don't think that task is on your list, buddy");
        }
    }

    public void markTask(int index) throws BuddyException {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        } else {
            throw new BuddyException("I don't think task is on your list buddy...");
        }
    }

    public void unmarkTask(int index) throws BuddyException {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsUndone();
        } else {
            throw new BuddyException("I don't think task is on your list buddy...");
        }
    }

    public boolean isTaskDone(int index) {
        return tasks.get(index).isDone;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
