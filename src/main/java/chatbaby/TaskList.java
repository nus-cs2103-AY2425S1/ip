package chatbaby;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task taskAt(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public boolean listTasksOn(LocalDate date) {
        boolean hasTask = false;
        System.out.println("Tasks that end on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        for (Task task : tasks) {
            if (task.isOnDate(date)) {
                System.out.println(task);
                hasTask = true;
            }
        }
        return hasTask;
    }
}
