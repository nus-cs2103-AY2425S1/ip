package Tools;

import Exception.EmptyDescriptionException;
import Exception.MissingDateException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class NewTaskList {
    private ArrayList<Task> tasks;

    public NewTaskList() {
        tasks = new ArrayList<>();

    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String deleteTask(int id) {
        Integer size = tasks.size();
        if (id < 0 || id >= this.tasks.size()) {
            return "Task ID is out of range!";

        }

        Task removedTask = this.tasks.remove(id); // Removes the task and captures it for confirmation message

        assert(tasks.size() == size - 1);

        return "Noted. I've removed this task:\n  " + removedTask +
                "\nNow you have " + tasks.size() + " tasks in the list.";

    }

    public String listTasks() {
        String tasklist = "";

        for (int i = 0; i < tasks.size(); i++) {
            int index = i+1;
             tasklist += index + " , " + tasks.get(i).toString() + "\n";
        }

        return tasklist;
    }

    String markTask(int id) {
        tasks.get(id).markDone();
        assert(tasks.get(id).getStatusIcon() == "[X]");

        return"Nice! I've marked this task as done:";
    }
    String unmarkTask(int id) {
        tasks.get(id).unmarkDone();
        assert(tasks.get(id).getStatusIcon() == "[ ]");

        return"OK, I've marked this task as not done yet:";
    }

    void add(Task task) {
        tasks.add(task);
    }

    public String addTodoTask(String input) throws EmptyDescriptionException {
        if (input.trim().length() <= 5) {
            throw new EmptyDescriptionException("OPS!!! The description of a todo cannot be empty.");
        }

        Integer size = tasks.size();

        String description = input.substring(5).trim();
        tasks.add(new Todo(description));

        assert(tasks.size() == size + 1);

        return "Got it. I've added this task:\n  " +
                new Todo(description) + "\nNow you have " +
                tasks.size() + " tasks in the list" + tasks.size() + ".";

    }

    public String addDeadlineTask(String input) throws EmptyDescriptionException, MissingDateException {
        String[] parts = input.split(" by ");
        if (parts.length < 2) {
            throw new MissingDateException("OPS!!! The date of a deadline cannot be empty");
        }
        String description = parts[0].substring(9).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("OPS!!! The description of a deadline cannot be empty");
        }

        try {
            Integer size = tasks.size();
            String by = parts[1].trim();
            tasks.add(new Deadline(description, by));
            assert(tasks.size() == size + 1);
            return "Got it. I've added this task:\n  "
                    + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.";

        } catch (DateTimeParseException e) {
            return"Error: Invalid date format. Please use yyyy-MM-dd HHmm.";
        }
    }

    String addEventTask(String input) throws EmptyDescriptionException, MissingDateException {
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
        Integer size = tasks.size();
        tasks.add(new Event(description, start, end));
        assert(tasks.size() == size + 1);
        return "Got it. I've added this task:\n"
                + "  " + new Event(description, start, end) + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public String findTask(String input) {
        NewTaskList foundTasks = new NewTaskList();
        for (Task task : tasks) {
            if (task.toString().contains(input)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.isEmpty()) {
            return "Task not found!";
        } else {
            foundTasks.listTasks();
        }

        return "findTask function from NewTaskList";
    }


}
