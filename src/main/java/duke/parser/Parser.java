package duke.parser;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The Parser class interprets user input and executes the appropriate actions.
 * It interacts with the TaskList and Ui classes to manage tasks and provide feedback to the user.
 */
public class Parser {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Parser object with the specified TaskList and Ui.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The Ui object for user interaction.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param input The user's input as a string.
     * @return Duke's response as a string.
     */
    public String parse(String input) {
        if (input.equals("list")) {
            return ui.showTaskList(tasks.getTasks());
        } else if (input.startsWith("mark")) {
            return markTask(input);
        } else if (input.startsWith("unmark")) {
            return unmarkTask(input);
        } else if (input.startsWith("todo")) {
            return addTodo(input);
        } else if (input.startsWith("deadline")) {
            return addDeadline(input);
        } else if (input.startsWith("event")) {
            return addEvent(input);
        } else if (input.startsWith("delete")) {
            return deleteTask(input);
        } else if (input.startsWith("find")) {
            return handleFind(input);
        } else if (input.equals("bye")) {
            return ui.showGoodbyeMessage();
        } else if (input.startsWith("list sorted by date")) {
            List<Task> sortedByDate = tasks.getTasksSortedByDate();
            return ui.showTaskList(sortedByDate);
        } else if (input.startsWith("list sorted by description")) {
            List<Task> sortedByDescription = tasks.getTasksSortedByDescription();
            return ui.showTaskList(sortedByDescription);
        } else {
            return ui.showUnknownCommand();
        }
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param input The user's input indicating which task to mark as done.
     * @return The success or error message.
     */
    private String markTask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.getSize()) {
                tasks.get(index).markAsDone();
                return ui.showTaskMarked(tasks.get(index));
            } else {
                return ui.showErrorMessage("Oops! That task number doesn't exist. Please try again.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return ui.showSuggestion("mark <task number>");
        }
    }

    /**
     * Marks a task as not done based on user input.
     *
     * @param input The user's input indicating which task to unmark as done.
     * @return The success or error message.
     */
    private String unmarkTask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.getSize()) {
                tasks.get(index).markAsNotDone();
                return ui.showTaskUnmarked(tasks.get(index));
            } else {
                return ui.showErrorMessage("Oops! That task number doesn't exist. Please try again.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return ui.showSuggestion("unmark <task number>");
        }
    }

    /**
     * Adds a new ToDo task based on user input.
     *
     * @param input The user's input containing the description of the ToDo task.
     * @return The success or error message.
     */
    private String addTodo(String input) {
        if (input.length() <= 5) {
            return ui.showErrorMessage("Oops! The description of a todo cannot be empty.");
        } else {
            String description = input.substring(5).trim();
            tasks.add(new ToDo(description));
            if (description.isEmpty()) {
                return ui.showErrorMessage("Oops! The description of a todo cannot be empty.");
            } else {
                return ui.showTaskAdded(tasks.get(tasks.getSize() - 1), tasks.getSize());
            }
        }
    }

    /**
     * Adds a new Deadline task based on user input.
     *
     * @param input The user's input containing the description and deadline.
     * @return The success or error message.
     */
    private String addDeadline(String input) {
        String[] substrings = input.split(" /by ");
        if (substrings.length == 2) {
            if (substrings[0].length() <= 9) {
                return ui.showErrorMessage("Oops! The description of a deadline cannot be empty.");
            } else {
                String description = substrings[0].substring(9).trim();
                String by = substrings[1].trim();
                if (description.isEmpty()) {
                    return ui.showErrorMessage("Oops! The description of a deadline cannot be empty.");
                } else {
                    try {
                        tasks.add(new Deadline(description, by));
                        return ui.showTaskAdded(tasks.get(tasks.getSize() - 1), tasks.getSize());
                    } catch (DateTimeParseException e) {
                        return ui.showErrorMessage("Please enter the date and time in the format yyyy-MM-dd HHmm.");
                    }
                }
            }
        } else {
            return ui.showSuggestion("deadline <description> /by <date/time>");
        }
    }

    /**
     * Adds a new Event task based on user input.
     *
     * @param input The user's input containing the description, start time, and end time.
     * @return The success or error message.
     */
    private String addEvent(String input) {
        String[] substrings = input.split(" /from ");
        if (substrings.length == 2) {
            if (substrings[0].length() <= 6) {
                return ui.showErrorMessage("Oops! The description of an event cannot be empty.");
            } else {
                String description = substrings[0].substring(6).trim();
                String[] fromTo = substrings[1].split(" /to ");
                if (fromTo.length == 2) {
                    String from = fromTo[0].trim();
                    String to = fromTo[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        return ui.showErrorMessage("Oops! The description, start time, or end time of an event cannot be empty.");
                    } else {
                        try {
                            tasks.add(new Event(description, from, to));
                            return ui.showTaskAdded(tasks.get(tasks.getSize() - 1), tasks.getSize());
                        } catch (DateTimeParseException e) {
                            return ui.showErrorMessage("Please enter the date and time in the format yyyy-MM-dd HHmm.");
                        }
                    }
                } else {
                    return ui.showSuggestion("event <description> /from <start date/time> /to <end date/time>");
                }
            }
        } else {
            return ui.showSuggestion("event <description> /from <start date/time> /to <end date/time>");
        }
    }

    /**
     * Deletes a task based on user input.
     *
     * @param input The user's input indicating which task to delete.
     * @return The success or error message.
     */
    private String deleteTask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.getSize()) {
                Task removedTask = tasks.remove(index);
                return ui.showTaskDeleted(removedTask, tasks.getSize());
            } else {
                return ui.showErrorMessage("Oops! That task number doesn't exist. Please try again.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return ui.showSuggestion("delete <task number>");
        }
    }

    /**
     * Finds a task based on user input.
     *
     * @param input The user's input indicating which task to find.
     * @return The success or error message.
     */
    private String handleFind(String input) {
        try {
        String keyword = input.split(" ")[1];
        List<Task> foundTasks = tasks.findTasksByKeyword(keyword);
            return ui.showFoundTasks(foundTasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showSuggestion("find <keyword>");
        }
    }
}



