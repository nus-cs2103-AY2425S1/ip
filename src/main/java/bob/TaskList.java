package bob;

import java.util.ArrayList;
import java.util.List;

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
