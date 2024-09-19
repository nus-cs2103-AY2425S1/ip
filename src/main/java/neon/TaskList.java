package neon;

import neon.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String removeTask(int index) {
        StringBuilder message = new StringBuilder();

        message.append("deleting task number : " + index);
        tasks.remove(index - 1);

        return message.toString();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String printList() {
        StringBuilder message = new StringBuilder();
        if (getSize() == 0) {
            message.append("nothing in the list!\n");
        } else {
            message.append("printing list:\n");
        }

        for(int i = 0; i < getSize(); i++) {
            int inc = i + 1;
            message.append((inc) + ". " + tasks.get(i).toString() + "\n");
        }

        return message.toString();
    }

    public String markItem(int taskNumber) {
        StringBuilder message = new StringBuilder();
        Task currTask = this.getTask(taskNumber - 1);
        currTask.check();

        message.append("checking task number : " + taskNumber + "\n");
        return message.toString();
    }

    public String unmarkItem(int taskNumber) {
        StringBuilder message = new StringBuilder();

        Task currTask = this.getTask(taskNumber - 1);
        currTask.uncheck();

        message.append("unchecking task number : " + taskNumber + "\n");
        return message.toString();
    }

    public TaskList findTask(String taskToFind) {
        List<Task> matches = new ArrayList<>();

        for (int i = 0; i < getSize(); i++) {
            if (this.getTask(i).getName().contains(taskToFind)) {
                matches.add(tasks.get(i));
            }
        }

        return new TaskList(matches);
    }
}
