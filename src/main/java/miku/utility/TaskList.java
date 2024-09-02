package miku.utility;

import java.util.ArrayList;

import miku.task.Task;

/**
 * A class extends the ArrayList and stores a ArrayList to store tasks.
 */
public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task object that is to be added to the task list.
     */
    public void addItem(Task task) {
        tasks.add(task);
        System.out.println("Got it . I've added this task:\n" + task.stringValue());
        System.out.println("いまは " + tasks.size() + " tasks in the list");
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Delete a task from the taskList.
     *
     * @param num the index of the task to be removed
     */
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

    /**
     * Prints all tasks stored in the list.
     */
    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.valueOf(i + 1) + ". " + tasks.get(i).stringValue());
        }
    }

    /**
     * Searches and prints all the matched tasks.
     *
     * @param string the matching key phrase.
     */
    public void searchList(String string) {
        int counter = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDesc().contains(string)) {
                System.out.println(String.valueOf(counter) + ". " + tasks.get(i).stringValue());
                counter += 1;
            }
        }
    }

    public void initAdd(Task element) {
        tasks.add(element);
    }

    public int size() {
        return tasks.size();
    }
}
