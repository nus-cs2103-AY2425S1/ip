package king;

import king.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void markTaskAsDone(int taskNumber) throws KingException {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsDone();
            //storage.save(tasks);
        } else {
            throw new KingException("Enter a valid task number within the list you buffoon!");
        }
    }

    public void markTaskAsUndone(int taskNumber) throws KingException {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsUndone();
            //storage.save(tasks);
        } else {
            throw new KingException("Enter a valid task number within the list you buffoon!");
        }
    }
}
