package data;

import exceptions.InvalidTaskException;
import ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void loadTask(Task task) {
        this.taskList.add(task);
    }

    public void addTask(Task task, Ui ui) {
        this.taskList.add(task);
        ui.displayString("Added: " + task);
        ui.displayString("You now have " + taskList.size() + " tasks.");
    }

    public void deleteTask(int i, Ui ui) throws InvalidTaskException {
        int numberOfTasks = this.taskList.size();
        if (i <= 0 || i > numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        this.taskList.remove(i - 1);
        numberOfTasks--;

        ui.displayString("Deleted: " + task);
        ui.displayString("You now have " + numberOfTasks + " tasks.");
    }

    public void markTask(int i, Ui ui) throws InvalidTaskException {
        int numberOfTasks = this.taskList.size();
        if (i <= 0 || i > numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        task.markDone();

        ui.displayString("Good Job! The task is now marked as done: ");
        ui.displayString("Marked task: " + task);
    }

    public void unmarkTask(int i, Ui ui) throws InvalidTaskException {
        int numberOfTasks = this.taskList.size();
        if (i <= 0 || i > numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        task.markNotDone();

        ui.displayString("Alright, the task is marked as not done: ");
        ui.displayString("Unmarked task: " + task);
    }

    public void listAllTasks(Ui ui) {
        int numberOfTasks = taskList.size();
        ui.displayString("You currently have " + numberOfTasks + " tasks.");
        for (int i = 0; i < numberOfTasks; i++) {
            int num = i + 1;
            Task task = taskList.get(i);
            ui.displayString(num + ". " + task);
        }
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

}
