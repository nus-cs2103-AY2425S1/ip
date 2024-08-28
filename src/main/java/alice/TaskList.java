package alice;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    Ui ui;
    private static final String line =
            "____________________________________________________________";

    public TaskList() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
    }

    public void addToList(Task task) {
        try {
            this.list.add(task);
            ui.addToListMsg(task, this.list.size());
        } catch (Exception e) {
            ui.addFailMsg();
        }
    }

    public void markTask(int num) {
        if (num <= list.size() && num > 0) {
            Task currTask = list.get(num - 1);
            currTask.markDone();
            ui.markMsg(currTask);
        } else {
            ui.invalidNumMsg();
        }
    }

    public void unmarkTask(int num) {
        if (num <= list.size() && num > 0) {
            Task currTask = list.get(num - 1);
            currTask.markUndone();
            ui.unMarkMsg(currTask);
        } else {
            ui.invalidNumMsg();
        }
    }

    public void delete(int num) {
        if (num <= list.size() && num > 0) {
            Task currTask = list.get(num - 1);
            this.list.remove(currTask);
            ui.deleteMsg(currTask, list.size());
        } else {
            ui.invalidNumMsg();
        }
    }

    public ArrayList<Task> getTask() {
        return this.list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            int currNum = i + 1;
            sb.append(currNum).append(".").append(currTask.toString()).append("\n");
        }
        return sb.toString();
    }
}
