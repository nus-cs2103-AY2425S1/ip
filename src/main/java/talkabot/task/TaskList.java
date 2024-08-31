package talkabot.task;

import talkabot.Ui;
import talkabot.exceptions.InvalidEditException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int n) {
        return this.taskList.get(n);
    }

    public Task delete(int n) {
        return this.taskList.remove(n);
    }

    /**
     * Finds all tasks that match the keyword
     * @param input keyword
     */
    public void find(String input) {
        TaskList matches = new TaskList();
        for (Task task : this.taskList) {
            if (task.description.contains(input)) {
                matches.add(task);
            }
        }
        Ui.returnMatches(matches);
    }
}
