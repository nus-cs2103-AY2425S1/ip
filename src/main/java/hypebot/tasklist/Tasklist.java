package hypebot.tasklist;

import hypebot.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static hypebot.common.Messages.ERROR_DELETE_TASK_INDEX_OUT_OF_BOUNDS;
import static hypebot.common.Messages.ERROR_MARK_TASK_INDEX_OUT_OF_BOUNDS;
import static hypebot.common.Messages.ERROR_UNMARK_TASK_INDEX_OUT_OF_BOUNDS;

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

    public Task delete(int task) throws IndexOutOfBoundsException {
        try {
            return TASKS.remove(task);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(ERROR_DELETE_TASK_INDEX_OUT_OF_BOUNDS);
        }
    }

    public void mark(int idx) throws IndexOutOfBoundsException {
        try {
            TASKS.get(idx).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(ERROR_MARK_TASK_INDEX_OUT_OF_BOUNDS);
        }
    }

    public void unmark(int idx) throws IndexOutOfBoundsException {
        try {
            TASKS.get(idx).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(ERROR_UNMARK_TASK_INDEX_OUT_OF_BOUNDS);
        }
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
