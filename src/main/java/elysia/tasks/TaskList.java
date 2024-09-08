package elysia.tasks;

import elysia.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return list.remove(index-1);
    }

    public void markTask(int taskNumber) throws IndexOutOfBoundsException, NullPointerException {
        list.get(taskNumber-1).updateStatus(true);
    }

    public void unmarkTask(int taskNumber) throws IndexOutOfBoundsException, NullPointerException {
        list.get(taskNumber-1).updateStatus(false);
    }

    public String printTask(int taskNumber) throws IndexOutOfBoundsException, NullPointerException {
        return list.get(taskNumber-1).toString();
    }

    public String size() {
        return "" + list.size();
    }

    public String toFile() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += list.get(i).toFile() + "\n";
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += (i+1) + "." + list.get(i).toString() + "\n";
        }
        return output;
    }

    public TaskList searchByKeyword(String keyword) {
        TaskList searchResults = new TaskList();

        for (Task task: list) {
            if (task.containsString(keyword)) {
                searchResults.addTask(task);
            }
        }

        return searchResults;
    }
}
