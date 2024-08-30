package bob.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task removeTaskAtIndex(int index) {
        if (this.size() <= index) {
            return null;
        }
        return this.taskList.remove(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void markTaskAtIndex(int index) {
        if (this.size() > index) {
            this.taskList.get(index).mark();
        }
    }

    public void unmarkTaskAtIndex(int index) {
        if (this.size() > index) {
            this.taskList.get(index).unmark();
        }
    }

    public String export() {
        return this.taskList.stream().map(Task::export)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        String taskString = " Here are the tasks in your list:";
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            taskString = taskString + "\n " + i + "." + this.taskList.get(i-1);
        }
        return taskString;
    }
}
