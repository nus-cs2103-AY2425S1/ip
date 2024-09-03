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

    public void printList() {
        String message = "";
        if (getSize() == 0) {
            message = "nothing in the list!";
        } else {
            message = "printing list:";
        }

        System.out.println(message);

        for(int i = 0; i < getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
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
