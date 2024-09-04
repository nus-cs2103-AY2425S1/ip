package spike.storage;

import spike.exceptions.SpikeException;
import spike.tasks.Task;
import spike.tasks.Deadline;
import spike.tasks.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }

    public int getSize() {
        return tasks.size();
    }

    public String getTaskString(int index) throws IndexOutOfBoundsException {
        return tasks.get(index).toString();
    }

    public String getTaskStatus(int index) throws IndexOutOfBoundsException {
        return tasks.get(index).getStatusIcon();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException{
        return tasks.remove(index);
    }

    public void markTaskDone(int index) throws IndexOutOfBoundsException{
        tasks.get(index).markAsDone();
    }

    public void markTaskUndone(int index) throws IndexOutOfBoundsException {
        tasks.get(index).markAsUndone();
    }

    private static final Comparator<Task> dateComparator = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            LocalDateTime date1 = t1 instanceof Deadline ? ((Deadline) t1).getDate() : ((Event) t1).getFrom();
            LocalDateTime date2 = t2 instanceof Deadline ? ((Deadline) t2).getDate() : ((Event) t2).getFrom();
            return date1.compareTo(date2);
        }
    };

    public ArrayList<Task> listTasksByDate() {
        return tasks.stream()
                .filter(t -> t instanceof Deadline || t instanceof Event)
                .sorted(dateComparator)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
