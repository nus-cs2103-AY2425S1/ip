package killua.util;

import killua.task.Deadline;
import killua.task.Event;
import killua.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTaskDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    public TaskList getTasksOnDate(LocalDate date) {
        TaskList tasksOnDate = new TaskList();

        for (Task task : tasks) {
            if (task instanceof Deadline deadline) {
                if (deadline.getDate().equals(date)) {
                    tasksOnDate.addTask(task);
                }
            } else if (task instanceof Event event) {
                if (!event.getStartDate().isAfter(date) && !event.getEndDate().isBefore(date)) {
                    tasksOnDate.addTask(task);
                }
            }
        }

        return tasksOnDate;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s%n", i + 1, task);
        }
    }
}
