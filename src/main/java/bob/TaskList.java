package bob;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bob.task.Task;

public class TaskList {

    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Gets Task at index i.
     */
    public Task getTask(int i) {
        return list.get(i);
    }

    public Task remove(int i) {
        return list.remove(i);
    }

    public void add(Task task) {
        list.add(task);
    }

    public int size() {
        return list.size();
    }

    public String toText() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            text.append(list.get(i).toText()).append("\n");
        }
        return text.toString();
    }

    /**
     * Gets a tasklist of all tasks completed in the specified date range.
     *
     * @param from Start date and time of date range.
     * @param to End date and time of date range.
     * @return Tasklist of all completed tasks.
     */
    public TaskList getCompleted(LocalDateTime from, LocalDateTime to) {
        return new TaskList(list.stream().filter(x -> x.getCompletedAt() != null &&
                x.getCompletedAt().isAfter(from) &&
                x.getCompletedAt().isBefore(to)).toList());
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            str.append((i + 1)).append(":").append(list.get(i));
            if (i < list.size() - 1) {
                str.append("\n");
            }
        }
        return (str.isEmpty()) ? "No tasks :(" : str.toString();
    }
}
