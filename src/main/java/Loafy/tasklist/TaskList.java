package loafy.tasklist;

import java.util.ArrayList;

import loafy.storage.Storage;
import loafy.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage, ArrayList<Task> tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    public TaskList(Storage storage) {
        this(storage, new ArrayList<Task>());
    }

    public String add(Task task) {
        this.tasks.add(task);
        this.writeToFile();
        return "Got it. I've added this task:\n" +
                "       " + task + "\n" +
                "       Now you have " + this.tasks.size() + " tasks in the list.";
    }

     public String view() {
        int i = 1;
        String s = "Here are the tasks in your list:";
        for (Task task : this.tasks) {
            s += "\n" + i + ". " + task;
            i++;
        }
        return s;
    }

    public String markTask(boolean isDone, int taskId) {
        Task task = this.tasks.get(taskId - 1);
        task.changeStatus(isDone);
        this.writeToFile();
        return task.toString();
    }

    public String delete(int taskId) {
        Task task = this.tasks.get(taskId - 1);
        this.tasks.remove(taskId - 1);
        this.writeToFile();
        return "Got it. I've removed this task:\n" +
                "       " + task + "\n" +
                "       Now you have " + this.tasks.size() + " tasks in the list.";
    }

    public boolean isValid(int taskId) {
        return 1 <= taskId && taskId <= this.tasks.size();
    }

    public String find(String keyword) {
        boolean hasMatch = false;
        int i = 1;
        String message = "Here are the matching tasks in your list:";
        for (Task task : this.tasks) {
            if (task.hasMatch(keyword)) {
                hasMatch = true;
                message += "\n" + i + ". " + task;
            }
            i++;
        }
        if (!hasMatch) {
            message = "There were no matching tasks in your list.";
        }
        return message;
    }

    private void writeToFile() {
        String text = "";
        for (Task task : this.tasks) {
            text += task.convertToTxt() + "\n";
        }
        this.storage.writeToFile(text);
    }
}
