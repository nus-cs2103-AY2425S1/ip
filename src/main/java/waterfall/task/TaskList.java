package waterfall.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;
    private int num;

    public TaskList() {
        tasks = new ArrayList<Task>();
        num = 0;
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        num = tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
        num++;
    }

    public void delete(int index) {
        tasks.remove(index);
        num--;
    }

    public void setDone(int index, boolean done) {
        tasks.get(index).setDone(done);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean checkIndex(int index) {
        return index >= 0 && index < num;
    }

    public void printDetail(int indentSpace) {
        for (int i = 0; i < num; i++) {
            Task task = tasks.get(i);
            if (task != null) {
                String taskString = " ".repeat(indentSpace) +
                        Integer.toString(i + 1) + "."
                        + task.toString();
                System.out.println(taskString);
            } else {
                break;
            }
        }
    }

    /**
     * Retrieves task list with the string contained in the title.
     *
     * @param text string required in the title.
     * @return The list of filtered tasks.
     */
    public List<Task> searchTasks(String text) {
        return tasks.stream().filter(task -> task.getTitle().contains(text)).toList();
    }
}
