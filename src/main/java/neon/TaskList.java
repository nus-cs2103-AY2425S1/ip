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

    public void removeTask(int index) {
        System.out.println("deleting task number : " + index);
        tasks.remove(index - 1);
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

        //message.append(message);

        for(int i = 0; i < getSize(); i++) {
            int inc = i + 1;
            message.append((inc) + ". " + tasks.get(i).toString() + "\n");
        }

        return message.toString();
    }

    public void markItem(int taskNumber) {
        Task currTask = this.getTask(taskNumber - 1);
        currTask.check();

        System.out.println("checking task number : " + taskNumber);
    }

    public void unmarkItem(int taskNumber) {
        Task currTask = this.getTask(taskNumber - 1);
        currTask.uncheck();

        System.out.println("unchecking task number : " + taskNumber);
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
