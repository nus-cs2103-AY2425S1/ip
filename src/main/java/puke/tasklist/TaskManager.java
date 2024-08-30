package puke.tasklist;

import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.storage.Storage;
import puke.tasks.Deadline;
import puke.tasks.Event;
import puke.tasks.Task;
import puke.tasks.Todo;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager(ArrayList<Task> loadedFromStorage) {
        this.tasks = loadedFromStorage;
    }

    public String addTask(String type, String description, String... timeInfo) {
        Task task = null;
        switch (type) {
            case "todo":
                task = new Todo(description, false);
                break;
            case "deadline":
                task = new Deadline(description, false, timeInfo[0]);
                break;
            case "event":
                task = new Event(description, false, timeInfo[0], timeInfo[1]);
                break;
        }
        tasks.add(task);
        Storage.saveTasks(tasks);

        return "Roger that! I've added in this task:\n  " + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list ~ !";
    }

    public String deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            return "OOPS!!! The task number " + taskNumber + " is out of bounds.";
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        Storage.saveTasks(tasks);

        return "Noted. I've removed this task:\n  " + removedTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    public String listTasks() {
        if (tasks.isEmpty()) {
            return "You haven't added any task yet! :L";
        }
        StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return list.toString();
    }

    public String markTask(int taskNumber, boolean isDone) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);

            if (isDone) {
                task.markAsDone();
                Storage.saveTasks(tasks);
                return "Yippee~ *uweah* I've marked this task as done:\n  " + task;
            } else {
                task.unmarkAsDone();
                Storage.saveTasks(tasks);
                return "LOL I've marked this task as not done yet:\n  " + task;
            }
        }
        return "Invalid task number!!!!";
    }

    public int getTaskCount() {
        return tasks.size();
    }

    private boolean isInvalidTaskNum(int taskNum) {
        return taskNum < 0 || taskNum >= tasks.size();
    }

    public Task getTask(int taskNum) throws TaskNumberOutOfBoundsException {
        if (isInvalidTaskNum(taskNum)) {
            throw new TaskNumberOutOfBoundsException(taskNum);
        } else {
            return tasks.get(taskNum);
        }
    }
}
