package wansbot.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    // adds a task to the tasklist
    public void add(Task task) {
        this.listOfTasks.add(task);
    }

    // returns task of specified number
    public Task number(int index) {
        if (listOfTasks.isEmpty()) {
            return null;
        }
        return this.listOfTasks.get(index);
    }

    // finishes a task in a task list
    public void finish(int index) {
        listOfTasks.get(index).finish();
    }

    // unchecks a finished task in a task list
    public void unfinish(int index) {
        listOfTasks.get(index).unfinish();
    }

    // returns number of wansbot.tasks currently in a tasklist
    public int numOfTasks() {
        return this.listOfTasks.size();
    }

    // removes a specified task in a task list
    public void removeTask(int taskNum) {
        this.listOfTasks.remove(taskNum);
    }

    // returns a task in  a task list
    public Task getTask(int i) {
        return this.listOfTasks.get(i);
    }

    @Override
    public String toString() {
        if (this.listOfTasks.size() <= 0) {
            return "Maybe you can try adding tasks :)";
        }
        String myTasks = "";
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            myTasks += (i + 1) + ". " + this.listOfTasks.get(i).toString() + "\n";
        }

        return myTasks;
    }
}
