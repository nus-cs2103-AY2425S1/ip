package bottle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            taskList.remove(index);
        } else {
            throw new IndexOutOfBoundsException("OOPS!!! Task number is out of bounds.");
        }
    }

    public void markTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task task = taskList.get(taskIndex);
            task.mark();
        } else {
            throw new IndexOutOfBoundsException("OOPS!!! Task number is out of bounds.");
        }
    }

    public void unMarkTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task task = taskList.get(taskIndex);
            task.unMark();
        } else {
            throw new IndexOutOfBoundsException("OOPS!!! Task number is out of bounds.");
        }
    }

    public void addTodoTask(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
        }
        taskList.add(new Todo(description));
    }

    public void addDeadlineTask(String input) {
        String[] parts = input.split(" /by ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("OOPS!!! The deadline format is incorrect. Use: deadline <description> /by <dd/MM/yyyy HHmm>");
        }
        String description = parts[0];
        LocalDateTime byTime = parseDateTime(parts[1]);
        taskList.add(new Deadline(description, byTime));
    }

    public void addEventTask(String input) {
        String[] parts = input.split(" /from | /to ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("OOPS!!! The event format is incorrect. Use: event <description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>");
        }
        String description = parts[0];
        LocalDateTime from = parseDateTime(parts[1]);
        LocalDateTime to = parseDateTime(parts[2]);
        taskList.add(new Event(description, from, to));
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS!!! The date format is incorrect. Please use: dd/MM/yyyy HHmm");
        }
    }
    public String toString() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            int idx = i + 1;
            tasks.append(idx).append(". ").append(taskList.get(i)).append("\n");
        }
        return tasks.toString();
    }
}
