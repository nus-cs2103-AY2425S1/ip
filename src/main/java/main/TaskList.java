package main;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

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

    public Todo addTodo(String description) {
        Todo t = new Todo(description);
        this.tasks.add(t);
        return t;
    }

    public Task removeTask(int i) {
        return this.tasks.remove(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public Deadline addDeadline(String description, LocalDateTime deadline) {
        Deadline d = new Deadline(description, deadline);
        this.tasks.add(d);
        return d;
    }

    public Event addEvent(String description, LocalDateTime from, LocalDateTime to) {
        Event e = new Event(description, from, to);
        this.tasks.add(e);
        return e;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
