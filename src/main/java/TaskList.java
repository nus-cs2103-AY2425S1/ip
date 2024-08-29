package src.main.java;

import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> tasklist = new ArrayList<>();

    public TaskList() {
    }

    protected static ArrayList<Task> getTasks() {
        return tasklist;
    }

    protected static int getSize() {
        return tasklist.size();
    }

    protected static void addTask(Task task) {
        tasklist.add(task);
    }

    protected static void markTaskAsDone(int taskNumber) {
        tasklist.get(taskNumber - 1).markAsDone();
    }

    protected static void unmarkTaskAsDone(int taskNumber) {
        tasklist.get(taskNumber - 1).unmarkAsDone();
    }

    protected static void removeTask(int taskNumber) {
        tasklist.remove(taskNumber - 1);
        System.out.println(String.format("You have successfully deleted task %d!", taskNumber));
    }
}
