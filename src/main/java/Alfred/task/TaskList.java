package alfred.task;

import alfred.ui.Ui;
import alfred.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getTasksCount() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) {
        Task task = tasks.remove(taskNumber - 1);
        Ui.showTaskDeleted(task, tasks.size());
    }

    public void updateTaskStatus(int taskNumber, boolean mark) {
        Task task = tasks.get(taskNumber - 1);
        if (mark) {
            task.markAsDone();
            Ui.showTaskMarked(task);
        } else {
            task.unmark();
            Ui.showTaskUnmarked(task);
        }
    }
}
