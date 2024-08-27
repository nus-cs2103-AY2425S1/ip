package duke.parser;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * The Parser class interprets user input and executes the appropriate actions.
 * It interacts with the TaskList and Ui classes to manage tasks and provide feedback to the user.
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

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
     */
    public void parse(String input) {
        if (input.equals("list")) {
            ui.showTaskList(tasks.getTasks());
        } else if (input.startsWith("mark")) {
            markTask(input);
        } else if (input.startsWith("unmark")) {
            unmarkTask(input);
        } else if (input.startsWith("todo")) {
            addTodo(input);
        } else if (input.startsWith("deadline")) {
            addDeadline(input);
        } else if (input.startsWith("event")) {
            addEvent(input);
        } else if (input.startsWith("delete")) {
            deleteTask(input);
        } else if (input.equals("bye")) {
            ui.showGoodbyeMessage();
        } else {
            ui.showUnknownCommand();
        }
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param input The user's input indicating which task to mark as done.
     */
    private void markTask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsDone();
                ui.showTaskMarked(tasks.get(index));
            } else {
                ui.showErrorMessage("Oops! That task number doesn't exist. Please try again.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            ui.showSuggestion("mark <task number>");
        }
    }

    /**
     * Marks a task as not done based on user input.
     *
     * @param input The user's input indicating which task to unmark as done.
     */
    private void unmarkTask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsNotDone();
                ui.showTaskUnmarked(tasks.get(index));
            } else {
                ui.showErrorMessage("Oops! That task number doesn't exist. Please try again.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            ui.showSuggestion("unmark <task number>");
        }
    }

    /**
     * Adds a new ToDo task based on user input.
     *
     * @param input The user's input containing the description of the ToDo task.
     */
    private void addTodo(String input) {
        if (input.length() <= 5) {
            ui.showErrorMessage("Oops! The description of a todo cannot be empty.");
        } else {
            String description = input.substring(5).trim();
            tasks.add(new ToDo(description));
            if (description.isEmpty()) {
                ui.showErrorMessage("Oops! The description of a todo cannot be empty.");
            } else {
                ui.showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());
            }
        }
    }

    /**
     * Adds a new Deadline task based on user input.
     *
     * @param input The user's input containing the description and deadline.
     */
    private void addDeadline(String input) {
        String[] substrings = input.split(" /by ");
        if (substrings.length == 2) {
            if (substrings[0].length() <= 9) {
                ui.showErrorMessage("Oops! The description of a deadline cannot be empty.");
            } else {
                String description = substrings[0].substring(9).trim();
                String by = substrings[1].trim();
                if (description.isEmpty()) {
                    ui.showErrorMessage("Oops! The description of a deadline cannot be empty.");
                } else {
                    try {
                        tasks.add(new Deadline(description, by));
                        ui.showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());
                    } catch (DateTimeParseException e) {
                        ui.showErrorMessage("Please enter the date and time in the format yyyy-MM-dd HHmm.");
                    }
                }
            }
        } else {
            ui.showSuggestion("deadline <description> /by <date/time>");
        }
    }

    /**
     * Adds a new Event task based on user input.
     *
     * @param input The user's input containing the description, start time, and end time.
     */
    private void addEvent(String input) {
        String[] substrings = input.split(" /from ");
        if (substrings.length == 2) {
            if (substrings[0].length() <= 6) {
                ui.showErrorMessage("Oops! The description of an event cannot be empty.");
            } else {
                String description = substrings[0].substring(6).trim();
                String[] fromTo = substrings[1].split(" /to ");
                if (fromTo.length == 2) {
                    String from = fromTo[0].trim();
                    String to = fromTo[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        ui.showErrorMessage("Oops! The description, start time, or end time of an event cannot be empty.");
                    } else {
                        try {
                            tasks.add(new Event(description, from, to));
                            ui.showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());
                        } catch (DateTimeParseException e) {
                            ui.showErrorMessage("Please enter the date and time in the format yyyy-MM-dd HHmm.");
                        }
                    }
                } else {
                    ui.showSuggestion("event <description> /from <start date/time> /to <end date/time>");
                }
            }
        } else {
            ui.showSuggestion("event <description> /from <start date/time> /to <end date/time>");
        }
    }

    /**
     * Deletes a task based on user input.
     *
     * @param input The user's input indicating which task to delete.
     */
    private void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.remove(index);
                ui.showTaskDeleted(removedTask, tasks.size());
            } else {
                ui.showErrorMessage("Oops! That task number doesn't exist. Please try again.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            ui.showSuggestion("delete <task number>");
        }
    }
}



