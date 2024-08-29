package lolo.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task markTaskAsNotDone(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    public TaskList getTasksOnDate(LocalDateTime date) {
        TaskList tasksOnDate = new TaskList();
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).by.toLocalDate().equals(date.toLocalDate())) {
                tasksOnDate.addTask(task);
            } else if (task instanceof Event &&
                    (((Event) task).from.toLocalDate().equals(date.toLocalDate()) ||
                            ((Event) task).to.toLocalDate().equals(date.toLocalDate()))) {
                tasksOnDate.addTask(task);
            }
        }
        return tasksOnDate;
    }
}
