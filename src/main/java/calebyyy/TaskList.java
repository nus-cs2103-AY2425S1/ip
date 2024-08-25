package calebyyy;

import java.util.ArrayList;

import calebyyy.Tasks.*;

public class TaskList {

    private ArrayList<Task> tasks;
    private Ui ui;
    
    public TaskList(Ui ui) {
        tasks = new ArrayList<>();
        this.ui = ui;
    }

    public void listTasks() {
        ui.listTasksMessage(this);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    public void unmarkTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsNotDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        ui.deleteTaskMessage(task, tasks.size());
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void findKeyword(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        ui.listTasksWithKeyword(foundTasks);
    }
    
}
