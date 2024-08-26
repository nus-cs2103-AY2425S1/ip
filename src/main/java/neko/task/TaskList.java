package neko.task;
import neko.NekoException;
import neko.Storage;
import neko.ui.Ui;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String taskType, Ui ui, Storage storage) throws IOException {
        Task task = null;
        switch (taskType) {
            case "1":
                task = addTodoTask(ui, storage);
                break;
            case "2":
                task = addDeadlineTask(ui, storage);
                break;
            case "3":
                task = addEventTask(ui, storage);
                break;
            default:
                ui.showMessage("Oops /ᐠ > ˕ <マ, that's not a valid option meow! Please enter 1, 2, or 3 meow!");
                break;
        }
        if (task != null) {
            tasks.add(task);
            ui.showMessage("Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ\n "
                    + task + "\nNow you have " + tasks.size() + " tasks in your list meow");
        }
    }

    private Task addTodoTask(Ui ui, Storage storage) throws IOException {
        String taskName = ui.getTaskName();
        Task task = new Todo(taskName);
        storage.writeFile("T | 0 | " + taskName + "\n");
        ui.showMessage("");
        return task;
    }

    private Task addDeadlineTask(Ui ui, Storage storage) throws IOException {
        String taskName = ui.getTaskName();
        LocalDateTime deadline = ui.getDateTime("Enter the deadline date and time in the form 'yyyy-MM-ddTHH:mm' meow:");
        Task task = new Deadline(taskName, deadline);
        storage.writeFile("D | 0 | " + taskName + " | " + task.getTime() + "\n");
        return task;
    }

    private Task addEventTask(Ui ui, Storage storage) throws IOException {
        String taskName = ui.getTaskName();
        LocalDateTime startDateTime = ui.getDateTime("Enter the start date and time in the form 'yyyy-MM-ddTHH:mm' meow:");
        LocalDateTime endDateTime = ui.getDateTime("Enter the end date and time in the form 'yyyy-MM-ddTHH:mm' meow:");

        while (endDateTime.isBefore(startDateTime)) {
            ui.showMessage("End time cannot be before start time meow! Please enter the end date and time again");
            endDateTime = ui.getDateTime("Enter the end date and time in the form 'yyyy-MM-ddTHH:mm' meow:");
        }

        Task task = new Event(taskName, startDateTime, endDateTime);
        storage.writeFile("E | 0 | " + taskName + " | " + task.getTime() + "\n");
        return task;
    }

    public Task getTask(int index) throws NekoException {
        checkValidIndex(index);
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void listTasks(Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage("You don't have any tasks yet meow!");
            return;
        }
        ui.showMessage("Here are the tasks in your list meow:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessageWODivier((i + 1) + ". " + tasks.get(i));
        }
    }

    public Task markTask(int index) throws NekoException {
        checkValidIndex(index);
        if (tasks.get(index).markAsDone()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    public Task unmarkTask(int index) throws NekoException {
        checkValidIndex(index);
        if (tasks.get(index).markAsNotDone()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    protected void checkValidIndex(int index) throws NekoException {
        if (index < 0)  throw new NekoException("Invalid task number meow!");
        if (tasks.isEmpty())  throw new NekoException("You don't have any tasks yet meow!");
        if (index >= tasks.size()) throw new NekoException("You only have " + tasks.size() + " tasks now meow!");
    }

    public void deleteTask(int index) throws NekoException {
        checkValidIndex(index);
        tasks.remove(index);
    }

    public String findTasks(String key) {
        String tasksFound = "";
        int numTasksFound = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(key)) {
                tasksFound += ++numTasksFound + "." + task.toString() + "\n";
            }
        }
        return tasksFound.trim();
        }
}