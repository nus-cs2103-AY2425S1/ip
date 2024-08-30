package task;

import parser.BobException;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();

    public void addTask(Task t) {
        this.tasks.add(t);
        this.ui.addTaskUi(t, this.tasks.size());
    }

    public void listTasks() {
        if (this.tasks.isEmpty()) {
            this.ui.sendMessage("No items yet!");
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                this.ui.sendMessage((i + 1) + ". " + this.tasks.get(i));
            }
        }
    }

    public void markTask(int num) {
        Task t = this.tasks.get(num);
        t.markAsDone();
        this.ui.markTaskUI(t);
    }

    public void unmarkTask(int num) {
        Task t = this.tasks.get(num);
        t.markAsNotDone();
        this.ui.unmarkTaskUI(t);
    }

    public void deleteTask(int num) {
        Task t = this.tasks.get(num);
        this.tasks.remove(num);
        this.ui.deleteTaskUI(t, this.tasks.size());
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
        for (Task t: this.tasks) {
            s.saveTask(t.toSave());
        }
    }

    public void findTasks(String name) {
        int i = 1;
        for (Task t: this.tasks) {
            if (t.getDescription().toLowerCase().contains(name.toLowerCase())) {
                this.ui.sendMessage((i++) + ". " + t);
            }
        }
        if (i == 0) {
            this.ui.sendMessage("No items yet!");
        }
    }
}
