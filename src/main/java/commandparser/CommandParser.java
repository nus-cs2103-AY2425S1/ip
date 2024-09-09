package commandparser;

import storage.Storage;
import taskmanager.TaskManager;
import task.Task;
import deadline.Deadline;
import todo.Todo;
import event.Event;


import java.time.LocalDate;

/**
 * Parses and executes user commands for task management.
 */
public class CommandParser {
    protected TaskManager taskManager;
    protected Storage storage;
    /**
     * Constructs a CommandParser with the specified TaskManager and Storage.
     *
     * @param taskManager The TaskManager to manage tasks.
     * @param storage The Storage to handle task persistence.
     */
    public CommandParser(TaskManager taskManager, Storage storage) {
        this.taskManager = taskManager;
        this.storage = storage;
    }

    /**
     * Marks a task as complete based on user input.
     *
     * @param input The user input containing the task index to mark.
     */
    public String handleMark(String input) {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            String temp = taskManager.markTask(index);
            this.storage.writeTasks();
            return temp;
        } catch (NumberFormatException e) {
            return "Invalid task number!";
        }
    }

    /**
     * Unmarks a task as incomplete based on user input.
     *
     * @param input The user input containing the task index to unmark.
     */
    public String handleUnmark(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            String temp = taskManager.unmarkTask(index);
            this.storage.writeTasks();
            return temp;
        } catch (NumberFormatException e) {
            return "Invalid task number!";
        }
    }

    /**
     * Deletes a task based on user input.
     *
     * @param input The user input containing the task index to delete.
     */
    public String handleDelete(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            String temp = taskManager.deleteTask(index);
            this.storage.writeTasks();
            return temp;
        } catch (NumberFormatException e) {
            return "Invalid task number!";
        }
    }

    public String handleFind(String input) {
        String search = input.replaceFirst("find ", "").trim();
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");

        int counter = 1;
        for (Task task : taskManager.getTasks()) {
            if (task.toString().contains(search)) {
                response.append(counter).append(". ").append(task.toString()).append("\n");
                counter++;
            }
        }

        if (counter == 1) {
            return "No matching tasks found.";
        }

        return response.toString();
    }

    /**
     * Adds a deadline task based on user input.
     *
     * @param input The user input containing the task description and deadline.
     * @param isSilent If true, suppress output messages.
     */
    public String handleDeadline(String input, boolean isSilent) {
        if (!input.contains("/by ")) {
            return "You need a deadline to add this task!";

        }
        String[] parts = input.split("/by ");
        String taskName = parts[0].replaceFirst("deadline ", "").trim();
        if (taskName.isEmpty()) {
            return "You need a task description!";

        }
        String initial = parts[1].trim();
        LocalDate deadline = LocalDate.parse(initial);
        Deadline deadlineTask = new Deadline(taskName, deadline, input);
        String temp = taskManager.addTask(deadlineTask, isSilent);
        this.storage.writeTasks();
        return temp;
    }

    /**
     * Adds a todo task based on user input.
     *
     * @param input The user input containing the task description.
     * @param isSilent If true, suppress output messages.
     */
    public String handleTodo(String input, boolean isSilent) {
        String taskName = input.replaceFirst("todo ", "").trim();
        if (taskName.isEmpty()) {
            return "You need a task description!";
        }
        Todo todoTask = new Todo(taskName, input);
        String temp = taskManager.addTask(todoTask, isSilent);
        this.storage.writeTasks();
        return temp;
    }

    /**
     * Adds an event task based on user input.
     *
     * @param input The user input containing the task description, start, and end time.
     * @param isSilent If true, suppress output messages.
     */
    public String handleEvent(String input, boolean isSilent) {
        boolean withoutFrom = !input.contains("/from ");
        boolean withoutTo = !input.contains("/to ");
        boolean isNotEvent = withoutFrom || withoutTo;
        if (isNotEvent) {
            return "You need a starting and ending date to add this task!";

        }
        String[] parts = input.split("/from ");
        String[] dateParts = parts[1].split("/to ");
        String taskName = parts[0].replaceFirst("event ", "").trim();
        if (taskName.isEmpty()) {
            return "You need a task description!";

        }
        String initialStartDate = dateParts[0].trim();
        String initialEndDate = dateParts[1].trim();
        LocalDate startDate = LocalDate.parse(initialStartDate);
        LocalDate endDate = LocalDate.parse(initialEndDate);
        Event eventTask = new Event(taskName, startDate, endDate, input);
        String temp = taskManager.addTask(eventTask, isSilent);
        this.storage.writeTasks();
        return temp;
    }
}
