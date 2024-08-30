package Tools;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Exception.EmptyDescriptionException;
import Exception.MissingDateException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();

    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void deleteTask(int id) {
        if (id < 0 || id >= this.tasks.size()) {
            System.out.println("Task ID is out of range!");
            return;
        }

        Task removedTask = this.tasks.remove(id); // Removes the task and captures it for confirmation message
        System.out.println("Noted. I've removed this task:\n  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            int index = i+1;
            System.out.println(index + " , " + tasks.get(i).toString());
        }
    }

    void markTask(int id) {
        tasks.get(id).markDone();
        System.out.println("Nice! I've marked this task as done:");
    }
    void unmarkTask(int id) {
        tasks.get(id).unmarkDone();
        System.out.println("OK, I've marked this task as not done yet:");
    }

    void add(Task task) {
        tasks.add(task);
    }

    public void addTodoTask(String input) throws EmptyDescriptionException {
        if (input.trim().length() <= 5) {
            throw new EmptyDescriptionException("OPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        tasks.add(new Todo(description));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + new Todo(description));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addDeadlineTask(String input) throws EmptyDescriptionException, MissingDateException {
        String[] parts = input.split(" by ");
        if (parts.length < 2) {
            throw new MissingDateException("OPS!!! The date of a deadline cannot be empty");
        }
        String description = parts[0].substring(9).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("OPS!!! The description of a deadline cannot be empty");
        }
        try {
            String by = parts[1].trim();
            tasks.add(new Deadline(description, by));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use yyyy-MM-dd HHmm.");
        }
    }

    void addEventTask(String input) throws EmptyDescriptionException, MissingDateException {
        String[] parts = input.split(" from | to ");
        if (parts.length < 3) {
            throw new MissingDateException("OPS!!! The start or end time of an event cannot be missing.");
        }
        String description = parts[0].substring(6).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("OPS!!! The description of an event cannot be empty.");
        }
        String start = parts[1].trim();
        String end = parts[2].trim();
        tasks.add(new Event(description, start, end));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + new Event(description, start, end));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void findTask(String input) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(input)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.isEmpty()) {
            System.out.println("Task not found!");
        } else {
            foundTasks.listTasks();
        }
    }


}
