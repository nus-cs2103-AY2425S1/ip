package Karen.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int i) {
        tasks.remove(i);
    }

    public void markTask(int i) {
        tasks.get(i).mark();
    }

    public void unmarkTask(int i) {
        tasks.get(i).unmark();
    }

    public String[] toTaskStrings() {
        if (this.tasks.isEmpty()) {
            return new String[] {};
        } else {
            String[] taskStrings = new String[this.tasks.size()];
            for (int i = 0; i < this.tasks.size(); i++) {
                taskStrings[i] = this.tasks.get(i).toString();
            }
            return taskStrings;
        }
    }

    public List<String> toFileStrings() {
        if (this.tasks.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<String> fileStrings = new ArrayList<>();
            for (Task t : tasks) {
                fileStrings.add(t.toFileString());
            }
            return fileStrings;
        }
    }
}
