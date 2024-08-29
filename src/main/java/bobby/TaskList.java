package bobby;

import bobby.exception.BobbyException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    public int getNumberOfTasks() {
        return this.listOfTasks.size();
    }

    public Task getTask(int x) {
        return this.listOfTasks.get(x);
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public void addTask(Task t) {
        this.listOfTasks.add(t);
    }

    public void mark(int x) throws BobbyException {
        if (x > this.listOfTasks.size() || x <= 0) {
            throw new BobbyException("My apologies. There is no task at that number!");
        }
        Task t = this.listOfTasks.get(x - 1);
        t.indComplete();
    }

    public void unmark(int x) throws BobbyException {
        if (x > this.listOfTasks.size() || x <= 0) {
            throw new BobbyException("My apologies. There is no task at that number!");
        }
        Task t = this.listOfTasks.get(x - 1);
        t.indIncomplete();
    }

    public void deleteTask(int x) {
        this.listOfTasks.remove(x - 1);
    }
}
