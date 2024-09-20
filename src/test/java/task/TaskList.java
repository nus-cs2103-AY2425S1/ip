package task;

import exceptions.HandsomeException;
import ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
//    private static final ArrayList<Task> data = new ArrayList<>();
    private final List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Task task) {
        tasks.add(task);
        Ui.show("Looking productive! I have added: \n" + task.toString());
        Ui.show("Seems like the task count is " + tasks.size() + ", don't overwork yourself wookie?");
    }
    public void printList() {
        Ui.show("Hey, here are the tasks you have!");
        for (int i = 0; i < tasks.size(); i++) {
            Ui.show((i + 1) + "." + tasks.get(i).toString());
        }
    }
    public void markDone(int taskNum) {
        tasks.get(taskNum).markAsDone();
        Ui.show("Congrats, you finally finished this task!");
        Ui.show(tasks.get(taskNum).toString());
    }

    public void markUndone(int taskNum) {
        tasks.get(taskNum).markAsUndone();
        Ui.show("Hey, the task is not done but time to stop procrastinating?");
        Ui.show(tasks.get(taskNum).toString());
    }

    public void deleteCheck(int taskNum) throws HandsomeException {
        if (taskNum > tasks.size()) {
            throw new HandsomeException("Hey, that task simply... does not exist.");
        }
        // temporarily store task to let user know what is deleted
        Task removedTask = tasks.remove(taskNum);
        Ui.show("Well... I have removed the task: \n" + removedTask);
        Ui.show("Now you have " + tasks.size() + " count of tasks left to do");
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

}
