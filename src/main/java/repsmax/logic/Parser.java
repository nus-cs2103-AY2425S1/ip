package repsmax.logic;

import repsmax.storage.Storage;
import repsmax.model.Deadline;
import repsmax.model.Event;
import repsmax.model.Task;
import repsmax.model.Todo;
import repsmax.ui.Ui;


import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user inputs and executes the corresponding commands.
 * <p>
 * The {@code Parser} class interprets user commands, interacts with the task list,
 * user interface, and storage to perform actions based on the user's input.
 * </p>
 */
public class Parser {

    // Constants for command keywords
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_FIND = "find";

    private static final String COMMAND_PRIORITY = "priority";

    /**
     * Parses the user's input and executes the corresponding command.
     * <p>
     * This method splits the input string into command and arguments, then
     * delegates the command to the appropriate handler method.
     * </p>
     *
     * @param userInput The input string entered by the user.
     * @param tasks     The task list to be managed.
     * @param ui        The user interface to interact with the user.
     * @param storage   The storage handler for saving and loading tasks.
     * @return A response message based on the executed command.
     */
    public String parse(String userInput, TaskList tasks, Ui ui, Storage storage) {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0];

        switch (command) {
            case COMMAND_LIST:
                return handleListCommand(tasks, ui);

            case COMMAND_MARK:
                return handleMarkCommand(splitInput, tasks, ui, storage);

            case COMMAND_UNMARK:
                return handleUnmarkCommand(splitInput, tasks, ui, storage);

            case COMMAND_TODO:
                return handleTodoCommand(splitInput, tasks, ui, storage);

            case COMMAND_DEADLINE:
                return handleDeadlineCommand(splitInput, tasks, ui, storage);

            case COMMAND_EVENT:
                return handleEventCommand(splitInput, tasks, ui, storage);

            case COMMAND_DELETE:
                return handleDeleteCommand(splitInput, tasks, ui, storage);

            case COMMAND_BYE:
                return handleByeCommand(tasks, ui, storage);

            case COMMAND_FIND:
                return handleFindCommand(splitInput, tasks, ui);

            default:
                return "Unknown command: " + userInput;
        }
    }

    /**
     * Handles the "list" command, displaying all tasks in the list.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui    The user interface to interact with the user.
     * @return A response message with the list of tasks.
     */
    private String handleListCommand(TaskList tasks, Ui ui) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString();
    }

    /**
     * Handles the "mark" command, marking a task as done.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be managed.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage handler for saving the updated tasks.
     * @return A response message confirming the task is marked as done.
     */
    private String handleMarkCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            if (splitInput.length < 2 || !splitInput[1].matches("\\d+")) {
                return "OOPS!!! The mark command must be followed by a task number.";
            }

            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
            Task taskToMark = tasks.get(taskIndex);
            taskToMark.setDone();
            storage.save(tasks);

            return "Nice! I've marked this task as done:\n" + taskToMark;
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! The task number you provided is out of range.";
        } catch (Exception e) {
            return "Something went wrong while processing the mark command.";
        }
    }

    /**
     * Handles the "unmark" command, marking a task as not done.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be managed.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage handler for saving the updated tasks.
     * @return A response message confirming the task is marked as not done.
     */
    private String handleUnmarkCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            if (splitInput.length < 2 || !splitInput[1].matches("\\d+")) {
                return "OOPS!!! The unmark command must be followed by a task number.";
            }

            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
            Task taskToUnmark = tasks.get(taskIndex);
            taskToUnmark.setUndone();
            storage.save(tasks);

            return "OK, I've marked this task as not done yet:\n" + taskToUnmark;
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! The task number you provided is out of range.";
        } catch (Exception e) {
            return "Something went wrong while processing the unmark command.";
        }
    }

    /**
     * Handles the "todo" command, adding a new ToDo task.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be managed.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage handler for saving the updated tasks.
     * @return A response message confirming the task is added.
     */
    private String handleTodoCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        if (splitInput.length > 1) {
            String[] parts = splitInput[1].split("/priority ", 2);
            String description = parts[0];
            int priority = 3; // Default priority is low
            if (parts.length > 1) {
                try {
                    priority = Integer.parseInt(parts[1].trim());
                    if (priority < 1 || priority > 3) {
                        return "OOPS!!! Priority level must be 1, 2, or 3.";
                    }
                } catch (NumberFormatException e) {
                    return "OOPS!!! The priority level must be an integer.";
                }
            }
            Todo newTodo = new Todo(description, priority);
            tasks.add(newTodo);
            storage.save(tasks);
            return "Got it. I've added this task:\n" + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.";
        } else {
            return "OOPS!!! The description of a todo cannot be empty.";
        }
    }

    /**
     * Handles the "deadline" command by adding a new {@code Deadline} task to the task list.
     * <p>
     * This method processes the user's input to create a new deadline task. It expects the input to include a description,
     * a deadline date/time specified with '/by', and an optional priority level specified with '/priority'.
     * The priority level defaults to 3 (low) if not provided.
     * </p>
     *
     * @param splitInput An array of strings where the first element is the command, and the second element contains the arguments.
     * @param tasks      The {@code TaskList} that manages all tasks.
     * @param ui         The {@code Ui} instance used to interact with the user (not used in this method, but included for consistency).
     * @param storage    The {@code Storage} instance used to save the updated tasks.
     * @return A string message confirming the addition of the task or an error message if the input is invalid.
     */
    private String handleDeadlineCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            if (splitInput.length < 2) {
                return "OOPS!!! The deadline command must include a description, '/by <date/time>' and optional '/priority <level>'";
            }

            String[] parts = splitInput[1].split("/by ", 2);
            if (parts.length < 2) {
                return "OOPS!!! The deadline command must include '/by <date/time>'";
            }

            String description = parts[0].trim();
            String byPart = parts[1].trim();
            int priority = 3; // Default priority is low

            // Check for priority and split appropriately
            if (byPart.contains("/priority")) {
                String[] byPriorityParts = byPart.split("/priority", 2);
                byPart = byPriorityParts[0].trim();
                String priorityStr = byPriorityParts[1].trim();

                // Validate priority
                try {
                    priority = Integer.parseInt(priorityStr);
                    if (priority < 1 || priority > 3) {
                        return "OOPS!!! Priority level must be 1 (high), 2 (medium), or 3 (low).";
                    }
                } catch (NumberFormatException e) {
                    return "OOPS!!! The priority level must be a valid integer.";
                }
            }

            // Add the new deadline task to the task list
            Deadline newDeadline = new Deadline(description, byPart, priority);
            tasks.add(newDeadline);
            storage.save(tasks);

            return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                    newDeadline, tasks.size());

        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return "Something went wrong while processing the command.";
        }
    }

    /**
     * Handles the "event" command, adding a new Event task.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be managed.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage handler for saving the updated tasks.
     * @return A response message confirming the task is added.
     */
    private String handleEventCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            // Split the input on "/from "
            String[] parts = splitInput[1].split("/from ", 2);

            // Check if the input contains "/priority" and handle accordingly
            String[] fromTo;
            String description;
            int priority = 3; // Default priority is low

            if (parts[1].contains("/priority")) {
                // Split by "/priority " and process priority
                String[] fromToPriority = parts[1].split("/priority ", 2);
                fromTo = fromToPriority[0].split("/to ", 2);
                description = parts[0];

                try {
                    priority = Integer.parseInt(fromToPriority[1].trim());
                    if (priority < 1 || priority > 3) {
                        return "OOPS!!! Priority level must be 1, 2, or 3.";
                    }
                } catch (NumberFormatException e) {
                    return "OOPS!!! The priority level must be an integer.";
                }

            } else {
                // If no "/priority", proceed normally
                fromTo = parts[1].split("/to ", 2);
                description = parts[0];
            }

            String from = fromTo[0].trim();
            String to = fromTo[1].trim();

            // Add the event to the task list
            tasks.add(new Event(description, from, to, priority));
            storage.save(tasks);

            return "Got it. I've added this task:\n" + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.";

        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS!!! The event command must include '/from <start date/time>' and '/to <end date/time>' and optional '/priority <level>'";
        }
    }

    /**
     * Handles the "event" command by adding a new {@code Event} task to the task list.
     * <p>
     * This method processes the user's input to create a new event task. It expects the input to include a description,
     * a start date/time specified with '/from', an end date/time specified with '/to', and an optional priority level
     * specified with '/priority'. The priority level defaults to 3 (low) if not provided.
     * </p>
     *
     * @param splitInput An array of strings where the first element is the command, and the second element contains the arguments.
     * @param tasks      The {@code TaskList} that manages all tasks.
     * @param ui         The {@code Ui} instance used to interact with the user (not used in this method, but included for consistency).
     * @param storage    The {@code Storage} instance used to save the updated tasks.
     * @return A string message confirming the addition of the task or an error message if the input is invalid.
     */
    private String handleDeleteCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            // Ensure the command is followed by a number
            if (splitInput.length < 2 || !splitInput[1].matches("\\d+")) {
                return "OOPS!!! The delete command must be followed by a task number.";
            }

            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
            Task taskToRemove = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            storage.save(tasks);

            return "Noted. I've removed this task:\n" + taskToRemove +
                    "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! The task number you provided is out of range.";
        } catch (Exception e) {
            return "Something went wrong while processing the delete command.";
        }
    }


    /**
     * Handles the "bye" command, saving the current tasks and exiting the program.
     *
     * @param tasks   The list of tasks to be saved.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage handler for saving the tasks.
     * @return A response message confirming the exit.
     */
    private String handleByeCommand(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles the "find" command, searching for tasks with the given keyword.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be searched.
     * @param ui         The user interface to interact with the user.
     * @return A response message with the search results.
     */
    private String handleFindCommand(String[] splitInput, TaskList tasks, Ui ui) {
        if (splitInput.length > 1) {
            String keyword = splitInput[1];
            List<Task> foundTasks = tasks.find(keyword);
            if (foundTasks.isEmpty()) {
                return "There are no matching tasks in your list.";
            }
            StringBuilder response = new StringBuilder();
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                response.append((i + 1)).append(". ").append(foundTasks.get(i)).append("\n");
            }
            return response.toString();
        } else {
            return "OOPS!!! The find command must include a keyword.";
        }
    }
}


