package task;

import parser.BobException;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> TaskList = new ArrayList<>();
    private Ui ui = new Ui();

    public void addTask(Task t) {
        this.TaskList.add(t);
        this.ui.addTaskUi(t, this.TaskList.size());
    }

    public void listTasks() {
        if (this.TaskList.isEmpty()) {
            this.ui.sendMessage("No items yet!");
        } else {
            for (int i = 0; i < this.TaskList.size(); i++) {
                this.ui.sendMessage((i + 1) + ". " + this.TaskList.get(i));
            }
        }
    }

    public void markTask(int num) {
        Task t = this.TaskList.get(num);
        t.markAsDone();
        this.ui.markTaskUI(t);
    }

    public void unmarkTask(int num) {
        Task t = this.TaskList.get(num);
        t.markAsNotDone();
        this.ui.unmarkTaskUI(t);
    }

    public void deleteTask(int num) {
        Task t = this.TaskList.get(num);
        this.TaskList.remove(num);
        this.ui.deleteTaskUI(t, this.TaskList.size());
    }

    public Task addToDo(String description) {
        Task t = new ToDo(description);
        this.addTask(t);
        return t;
    }

    public Task addDeadline(String description, String end) {
        Task t = new Deadline(description, end);
        this.addTask(t);
        return t;
    }

    public Task addEvent(String description, String start, String end) {
        Task t = new Event(description, start, end);
        this.addTask(t);
        return t;
    }

    public void saveTask(Storage s) {
        s.prepareSave();
        for (Task t: this.TaskList) {
            s.saveTask(t.toSave());
        }
    }
}
