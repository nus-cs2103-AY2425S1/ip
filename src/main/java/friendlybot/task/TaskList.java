package friendlybot.task;

import friendlybot.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int taskNumber) {
        return this.tasks.remove(taskNumber - 1);
    }

    public int getNumTasks() {
        return this.tasks.size();
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    public void getTasksOnDate(LocalDate date) {
        for (Task task : this.tasks) {
            if (task instanceof Deadline d) {
                if (d.by.equals(date)) {
                    Ui.print(task.toString());
                }
            } else if (task instanceof Event e) {
                if (e.from.equals(date) || e.to.equals(date) || (e.from.isBefore(date) && e.to.isAfter(date))) {
                    Ui.print(task.toString());
                }
            }
        }
    }

    public String formatTasksToSave() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.tasks) {
            if (task instanceof ToDo) {
                sb.append("T | ");
            } else if (task instanceof Deadline) {
                sb.append("D | ");
            } else {
                sb.append("E | ");
            }
            if (task.isDone) {
                sb.append("1 | ");
            } else {
                sb.append("0 | ");
            }
            sb.append(task.description);
            if (task instanceof Deadline) {
                sb.append(" | ").append(((Deadline) task).by);
            } else if (task instanceof Event e) {
                sb.append(" | ").append(e.from).append(" | ").append(e.to);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
