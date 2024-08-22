import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(); // Initialize an empty list
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks; // Initialize with existing tasks if it exists checked in Wolfie.java
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

    public List<Task> getTasksOnDate(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).getBy().toLocalDate().equals(date)) {
                tasksOnDate.add(task);
            } else if (task instanceof Event && (((Event) task).getFrom().toLocalDate().equals(date)
                    || ((Event) task).getTo().toLocalDate().equals(date))) {
                tasksOnDate.add(task);
            }
        }
        return tasksOnDate;
    }
}