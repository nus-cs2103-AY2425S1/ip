package task;

import command.DukeException;

import java.util.ArrayList;

public final class TaskList {

    private final ArrayList<Task> _taskList;

    public TaskList() {
        this._taskList = new ArrayList<>();
    }

    public boolean add(Task t) {
        return _taskList.add(t);
    }

    public boolean addAll(ArrayList<Task> arr) {
        return this._taskList.addAll(arr);
    }

    public ArrayList<Task> getTaskList() {
        return this._taskList;
    }

    public void writeToDisk(Storage storage) throws DukeException {
        storage.writeToDisk(this._taskList);
    }

    public void printTasksRemaining() {
        String objectivesRemaining = "";
        int n = this._taskList.size();
        if (n == 0) {
            objectivesRemaining = "No mission objectives specified";
        } else if (n == 1) {
            objectivesRemaining = "1 objective remaining";
        } else {
            objectivesRemaining = String.valueOf(n) + " objectives remaining";
        }
        System.out.println("\n" + objectivesRemaining);
    }
}
