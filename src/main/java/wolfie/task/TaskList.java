package wolfie.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks using ArrayList.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(); // Initialize an empty list
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks; // Initialize with existing tasks if it exists checked in wolfie.Wolfie.java
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a list of tasks that are on the specified date in the parameter.
     * @param date The date to check for tasks
     * @return A list of tasks that are on the specified date
     */
    public List<Task> getTasksOnDate(LocalDate date) {
        return tasks.stream()
                    .filter(task -> task instanceof Deadline && ((Deadline) task).getBy().toLocalDate().equals(date)
                            || task instanceof Event && (((Event) task).getFrom().toLocalDate().equals(date)
                            || ((Event) task).getTo().toLocalDate().equals(date)))
                    .collect(Collectors.toList());
    }
}
