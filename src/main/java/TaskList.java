import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    TaskList(Storage storage) {
        this.tasks = new ArrayList<Task>();
        this.storage = storage;
    }

    static TaskList ofLoadFromTxt(Storage storage) throws LoafyException {
        TaskList tl = new TaskList(storage);
        try {
            for (String line : storage.load()) {
                String[] arr = line.split(",");
                boolean isDone = arr[1].equals("true");
                if (arr[0].equals("T")) {
                    Todo td = new Todo(isDone, arr[2]);
                    tl.tasks.add(td);
                } else if (arr[0].equals("D")) {
                    Deadline dl = new Deadline(isDone, arr[2], LocalDateTime.parse(arr[3]));
                    tl.tasks.add(dl);
                } else if (arr[0].equals("E")) {
                    Event e = new Event(isDone, arr[2], LocalDateTime.parse(arr[3]), LocalDateTime.parse(arr[4]));
                    tl.tasks.add(e);
                } else {
                    throw LoafyException.ofCorruptedList();
                }
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw LoafyException.ofCorruptedList();
        }
        return tl;
    }

    String add(Task task) {
        this.tasks.add(task);
        this.writeToFile();
        return "Got it. I've added this task:\n" +
                "   " + task + "\n" +
                "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    String view() {
        int i = 1;
        String s = "Here are the tasks in your list:";
        for (Task task : this.tasks) {
            s += "\n" + i + ". " + task;
            i++;
        }
        return s;
    }

    String markTask(boolean isDone, int taskId) {
        Task task = this.tasks.get(taskId - 1);
        task.changeStatus(isDone);
        this.writeToFile();
        return task.toString();
    }

    String delete(int taskId) {
        Task task = this.tasks.get(taskId - 1);
        this.tasks.remove(taskId - 1);
        this.writeToFile();
        return "Got it. I've removed this task:\n" +
                "   " + task + "\n" +
                "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    boolean isValid(int taskId) {
        return 0 <= taskId && taskId <= this.tasks.size();
    }

    private void writeToFile() {
        String s = "";
        for (Task task : this.tasks) {
            s += task.convertToTxt() + "\n";
        }
        this.storage.writeToFile(s);
    }
}
