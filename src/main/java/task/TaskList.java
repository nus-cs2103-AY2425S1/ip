package task;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkTaskAsDone(int index) {
        tasks.get(index).markAsNotDone();
    }

    public void printTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void printTasksOnDate(LocalDate date) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks occurring on " + date + ":");

        boolean hasTasks = false;
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getByDate().equals(date)) {
                    System.out.println(" " + task);
                    hasTasks = true;
                }
            }
        }

        if (!hasTasks) {
            System.out.println(" No tasks found on this date.");
        }
        System.out.println("____________________________________________________________");
    }
}