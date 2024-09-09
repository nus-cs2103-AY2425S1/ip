package hypebot.tasklist;

import hypebot.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tasklist {
    private final ArrayList<Task> TASKS;

    public Tasklist() {
        TASKS = new ArrayList<>();
    }

    public Tasklist(List<Task> tasks) {
        this();
        TASKS.addAll(tasks);
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    public int size() {
        return TASKS.size();
    }

    public Task getTaskByIndex(int index) {
        return TASKS.get(index);
    }

    public void add(Task task) {
        TASKS.add(task);
    }

    public Task delete(int task) {
        return TASKS.remove(task);
    }

    public void mark(int idx) throws IndexOutOfBoundsException {
        TASKS.get(idx).mark();
    }

    public void unmark(int idx) throws IndexOutOfBoundsException {
        TASKS.get(idx).unmark();
    }

    public Tasklist getHappeningOn(LocalDate date) {
        List<Task> tasksOnDate = TASKS.stream().filter(task -> task.isHappeningOn(date)).toList();
        return new Tasklist(tasksOnDate);
    }

    @Override
    public String toString() {
        StringBuilder listMessage = new StringBuilder();
        for (int i = 0; i < TASKS.size(); i++) {
            listMessage.append(i + 1).append(". ").append(TASKS.get(i)).append("\n");
        }
        return listMessage.toString();
    }
}
