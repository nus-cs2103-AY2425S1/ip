package rose;

import rose.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public void showTasks(Ui ui) {
        for (int i = 0; i < tasks.size(); i++) {
            ui.display((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public void findTask(String keyword, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.hasWord(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.showFind(matchingTasks);


    }
}
