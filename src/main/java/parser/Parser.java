package parser;

import commands.*;
import exceptions.GrokInvalidUserInputException;
import taskList.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {
    public Parser() {

    }

    /**
     * Parse a single line of user input into an executable command
     * @param userInput - the raw string input by the user
     * @param tasks - the current task list. Required for input validation of index arguments, like mark
     * @return an executable command if the user input is valid
     * @throws GrokInvalidUserInputException if the user format does not any command
     */
    public Command parseUserInput(String userInput, TaskList tasks) throws GrokInvalidUserInputException {
        if (userInput.isEmpty()) {
            throw new GrokInvalidUserInputException("Please enter your command.");
        } else if (userInput.contains("|")){
            // required so that | can be used to delimit different items in storage reliably.
            throw new GrokInvalidUserInputException("Message cannot contain the restricted character '|'!");
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.contains("unmark")) {
            if (userInput.length() < 8) {
                throw new GrokInvalidUserInputException("Please indicate the index to unmark: e.g. unmark 1");
            }

            int taskIndex;
            try {
                taskIndex = Integer.parseInt(userInput.substring(7));
            } catch (NumberFormatException e) {
                throw new GrokInvalidUserInputException("Please enter the task index number to unmark.");
            }

            if (taskIndex <= 0 || taskIndex > tasks.length()) {
                throw new GrokInvalidUserInputException("Index provided is out of bounds of the current task list.");
            }

            return new UnmarkCommand(taskIndex - 1);
        } else if (userInput.contains("mark")) {
            if (userInput.length() < 6) {
                throw new GrokInvalidUserInputException ("Please indicate the index to mark: e.g. mark 1");
            }

            int taskIndex;
            try {
                taskIndex = Integer.parseInt(userInput.substring(5));
            } catch (NumberFormatException e) {
                throw new GrokInvalidUserInputException("Please enter the task index number to mark.");
            }

            if (taskIndex <= 0 || taskIndex > tasks.length()) {
                throw new GrokInvalidUserInputException("Please enter a valid task index to mark.");
            }
            return new MarkCommand(taskIndex - 1);
        } else if (userInput.contains("todo")) {
            if (userInput.length() < 6) {
                throw new GrokInvalidUserInputException("Todo command usage: todo (task description here)");
            }

            // potentially throws an invalid user input exception of its own.
            Task newTask = new Todo(userInput.substring(5));
            return new AddCommand(newTask);
        } else if (userInput.contains("deadline")) {
            String deadlineUsage = "Deadline command usage: deadline (task description here) " +
                    "/by (due date and time)";

            if (userInput.length() < 10 || !userInput.contains("/by")) {
                throw new GrokInvalidUserInputException(deadlineUsage);
            }

            String[] components = userInput.split("/by");

            if (components.length != 2) {
                throw new GrokInvalidUserInputException(deadlineUsage);
            }

            String description = components[0].substring(9);
            String due = components[1].substring(1);

            // potentially throws an invalid user input exception of its own.
            Task newTask = new Deadline(description, due);
            return new AddCommand(newTask);
        } else if (userInput.contains("event")) {
            String eventUsage = "Event command usage: event (task description here) " +
                    "/from (start date and time) /to (end date and time)";
            if (userInput.length() < 7 || !userInput.contains("/from") || !userInput.contains("/to")) {
                throw new GrokInvalidUserInputException(eventUsage);
            }

            String[] components = userInput.split("/from");
            if (components.length != 2) {
                throw new GrokInvalidUserInputException(eventUsage);
            }

            String[] subcomponents = components[1].split("/to");
            if (subcomponents.length != 2) {
                throw new GrokInvalidUserInputException(eventUsage);
            }

            String description = components[0];
            String from = subcomponents[0].substring(1);
            String to = subcomponents[1].substring(1);

            // potentially throws an invalid user input exception of its own.
            Task newTask = new Event(description, from, to);
            return new AddCommand(newTask);
        } else if (userInput.contains("delete")) {
            if (userInput.length() < 8) {
                throw new GrokInvalidUserInputException("Delete command usage: delete (task index to delete)");
            }

            int taskIndex;
            try {
                taskIndex = Integer.parseInt(userInput.substring(7));
            } catch (NumberFormatException e) {
                throw new GrokInvalidUserInputException("Please enter the task index number to delete.");
            }

            if (taskIndex <= 0 || taskIndex > tasks.length()) {
                throw new GrokInvalidUserInputException("Please enter a valid task index to delete.");
            }

            return new DeleteCommand(taskIndex);
        } else {
            throw new GrokInvalidUserInputException(
                    "OOPS! Sorry, I don't recognize your input :(\n" +
                            "Available commands are: " +
                            "bye, list, mark, unmark, todo, deadline, event, delete"
            );
        }
    }
}
