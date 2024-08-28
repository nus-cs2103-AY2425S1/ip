package pikappi;

import pikappi.exception.PikappiException;
import pikappi.task.DeadlineTask;
import pikappi.task.EventTask;
import pikappi.task.Task;
import pikappi.task.TodoTask;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void addTask(Task task) throws PikappiException {
        if (task == null) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
    }

    public void deleteTask(int taskNum) throws PikappiException {
        if (taskNum > tasks.size() || taskNum < 1) {
            throw new PikappiException("Pi-ka..?? Task does not exist..");
        }
        Task task = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        ui.showDeletedTask(task, tasks.size());
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            ui.showNoTasks();
            return;
        }
        ui.showAllTasks(this.tasks);
    }

    public void markTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        ui.showMarkedTask(task);
    }

    public void unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.unmarkAsDone();
        ui.showUnmarkedTask(task);
    }

    public TaskList findTask(String keyword) {
        TaskList matches = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }
}
