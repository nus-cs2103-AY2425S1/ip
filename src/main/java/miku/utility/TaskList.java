package miku.utility;

import miku.task.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addItem(Task task) {
        tasks.add(task);
        System.out.println("Got it . I've added this task:\n" + task.stringValue());
        System.out.println("いまは " + tasks.size() + " tasks in the list");
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void deleteItem(int num) {
        System.out.println("はい、わかりました\nI have removed the following task: " + tasks.get(num - 1).stringValue());
        tasks.remove(num - 1);
        System.out.println("いまは " + tasks.size() + " tasks in the list");
    }

    public void mark(int num) {
        tasks.get(num - 1).markDone();
    }

    public void unmark(int num) {
        tasks.get(num - 1).markUndone();
    }

    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.valueOf(i + 1) + ". " + tasks.get(i).stringValue());
        }
    }

    public void initAdd(Task element) {
        tasks.add(element);
    }

    public int size() {
        return tasks.size();
    }
}
