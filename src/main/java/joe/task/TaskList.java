package joe.task;

import java.util.ArrayList;

/**
 * This class is used to represent the collection of tasks and
 * provides methods to interact with it.
 */
public class TaskList {
    // contains the task list
    private final ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(tasks);
    }

    public ArrayList<Task> toArrayList() {
        return this.taskList;
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void removeTask(int idx) {
        Task task = this.taskList.remove(idx - 1);
        System.out.printf("\tNoted. I've removed this task:\n\t  %s\n", task);
    }

    public void markTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.markDone();
        System.out.printf("\tNice! I've marked this task as done:\n\t  %s\n", task);
    }

    public void unmarkTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.unmarkDone();
        System.out.printf("\tOK, I've marked this task as not done yet:\n\t  %s\n", task);
    }

    public ArrayList<Task> find(String query) {
        ArrayList<Task> matchedArr = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.contains(query)) {
                matchedArr.add(task);
            }
        }
        return matchedArr;
    }
}
