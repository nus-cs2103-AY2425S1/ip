package Tools;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Exception.EmptyDescriptionException;
import Exception.MissingDateException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Manages a list of tasks and provides methods to manipulate and retrieve tasks.
 */
public class NewTaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty list of tasks.
     */
    public NewTaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Deletes a task from the list based on its ID.
     *
     * @param id the index of the task to be deleted
     * @return a confirmation message about the removed task
     */
    public String deleteTask(int id) {
        if (id < 0 || id >= this.tasks.size()) {
            return "Task ID is out of range!";
        }

        Task removedTask = this.tasks.remove(id);
        return "Noted. I've removed this task:\n  " + removedTask +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Updates a task in the list based on user input.
     *
     * @param input the user input containing task ID and details for update
     * @return a response message indicating the outcome of the update
     */
    public String updateTask(String input) {
        // First, split the input on spaces to determine the basic parts
        String[] args = input.split("\\s+", 3); // Split into three parts: command, ID, rest
        if (args.length < 3) {
            return "Lack of ID, or description, or priority";
        }

        Integer id;
        try {
            id = Integer.parseInt(args[1]) - 1; // Convert ID from input and adjust for 0-based index
        } catch (NumberFormatException e) {
            return "Invalid ID format";
        }

        Task task = this.tasks.get(id);
        String updates = args[2]; // Remainder of the input containing updates

        boolean hasDescription = updates.contains("d");
        boolean hasPriority = updates.contains("p");
        if (hasDescription && hasPriority) {
            try {
                String[] parts = updates.split(" p ");
                String descriptionPart = parts[0].substring(parts[0].indexOf("d") + 1).trim();
                Integer priority = Integer.valueOf(parts[1].trim());
                task.update(descriptionPart, priority);
                return "Description updated to " + descriptionPart + " and priority updated to " + priority;
            } catch (Exception e) {
                return "Description update failed";
            }
        } else if (hasDescription) {
            String description = updates.substring(updates.indexOf("d") + 1).trim();
            task.update(description);
            return "Description updated to " + description;
        } else if (hasPriority) {
            try {
                String priorityStr = updates.substring(updates.indexOf("p") + 1).trim();
                Integer priority = Integer.valueOf(priorityStr);
                task.update(priority);
                return "Priority updated to " + priority;
            } catch (NumberFormatException e) {
                return "Invalid priority format";
            }
        } else {
            return "Invalid command. Please specify 'd' for description or 'p' for priority.";
        }
    }

    /**
     * Returns a formatted string listing all tasks.
     *
     * @return a formatted string of all tasks
     */
    public String listTasks() {
        String tasklist = "";

        for (int i = 0; i < tasks.size(); i++) {
            int index = i+1;
            tasklist += index + " , " + tasks.get(i).toString() + "\n";
        }

        return tasklist;
    }

    /**
     * Marks a task as done.
     *
     * @param id the index of the task to mark as done
     * @return a confirmation message stating the task is marked done
     */
    String markTask(int id) {
        tasks.get(id).markDone();
        return"Nice! I've marked this task as done:";
    }


    /**
     * Marks a task as not done.
     *
     * @param id the index of the task to mark as not done
     * @return a confirmation message stating the task is marked not done
     */
    String unmarkTask(int id) {
        tasks.get(id).unmarkDone();
        return"OK, I've marked this task as not done yet:";
    }

    /**
     * Adds a new task to the list.
     *
     * @param task the task to add
     */
    void add(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a new Todo task to the list based on user input.
     *
     * @param input the user input describing the todo task
     * @return a confirmation message indicating the task has been added
     * @throws EmptyDescriptionException if the description is missing
     */
    public String addTodoTask(String input) throws EmptyDescriptionException {

        if (input.trim().length() <= 5) {
            throw new EmptyDescriptionException("OPS!!! The description of a todo cannot be empty.");
        }

        String description = "";

        Todo task;

        if (input.contains(" p ")) {
            String[] splits = input.split(" p ");
            description = splits[0].substring(5).trim();
            String priority = splits[1];
            task = new Todo(description, priority);
            tasks.add(this.tasks.size(), task);
        } else {
            description = input.substring(5).trim();
            task = new Todo(description);
            tasks.add(task);
        }
        return "Got it. I've added this task:\n  " +
                task + "\nNow you have " +
                tasks.size() + " tasks in the list" + ".";

    }

    /**
     * Adds a new Deadline task to the list based on user input.
     *
     * @param input the user input describing the deadline task
     * @return a confirmation message indicating the task has been added
     * @throws EmptyDescriptionException if the description is missing
     * @throws MissingDateException if the date is missing
     */
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
            if (parts[1].contains("p")) {
                String[] date_priority = parts[1].split(" p ");
                String by = date_priority[0].trim();
                String priority = date_priority[1];
                tasks.add(new Deadline(description, by, priority));
                return "Got it. I've added this task:\n  "
                        + tasks.get(tasks.size() - 1) +
                        "\nNow you have " + tasks.size() + " tasks in the list.";
            } else {
                String by = parts[1].trim();
                tasks.add(new Deadline(description, by));
                return "Got it. I've added this task:\n  "
                        + tasks.get(tasks.size() - 1) +
                        "\nNow you have " + tasks.size() + " tasks in the list.";
            }

        } catch (DateTimeParseException e) {
            return"Error: Invalid date format. Please use yyyy-MM-dd HHmm.";
        }
    }

    /**
     * Adds a new Event task to the list based on user input.
     *
     * @param input the user input describing the event task
     * @return a confirmation message indicating the task has been added
     * @throws EmptyDescriptionException if the description is missing
     * @throws MissingDateException if the event time is missing
     */
    String addEventTask(String input) throws EmptyDescriptionException, MissingDateException {
        String[] parts = {};
        if (input.contains(" p ")) {
            parts = input.split(" from | to | p ");
        } else {
            parts = input.split(" from | to ");
        }

        if (parts.length < 3) {
            throw new MissingDateException("OPS!!! The start or end time of an event cannot be missing.");
        }

        String description = parts[0].substring(6).trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("OPS!!! The description of an event cannot be empty.");
        }
        String start = parts[1].trim();
        String end = parts[2].trim();
        Event event;
        if (parts.length > 3) {
            String priority = parts[3].trim();
            event = new Event(description, start, end, priority);
            tasks.add(event);
        } else {
            event = new Event(description, start, end);
            tasks.add(event);
        }
        return "Got it. I've added this task:\n"
                + "  " + event + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Searches for tasks containing the specified keyword.
     *
     * @param input the keyword to search for
     * @return a list of tasks matching the keyword
     */
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

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }


}
