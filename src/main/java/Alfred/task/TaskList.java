package Alfred.task;

import Alfred.ui.Ui;
import Alfred.parser.Parser;

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

    public void deleteTask(String input) {
        if (!Parser.isValidCommand(input, "delete", this.getTasksCount())) {
            return;
        }

        int taskNumber = Parser.getTaskNumberFromInput(input);
        Task task = tasks.remove(taskNumber - 1);
        Ui.showTaskDeleted(task, tasks.size());
    }

    public void updateTaskStatus(String input, boolean mark) {
        String action = mark ? "mark" : "unmark";

        if (!Parser.isValidCommand(input, action, getTasksCount())) {
            return;
        }

        int taskNumber = Parser.getTaskNumberFromInput(input);
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
