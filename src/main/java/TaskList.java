import java.util.ArrayList;

import tasks.Task;

public class TaskList {
    private static final ArrayList<Task> LIST = new ArrayList<>();

    public void addTask(Task task) {
        LIST.add(task);
    }

    public Task getItem(int label) {
        return LIST.get(label - 1);
    }

    public void removeItem(int label) {
        LIST.remove(label - 1);
    }

    public int getTaskCount() {
        return LIST.size();
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < LIST.size(); i++) {
            output.append(String.valueOf(i + 1))
                    .append(". ")
                    .append(LIST.get(i).toString())
                    .append("\n");
        }
        return output.toString();
    }
}
