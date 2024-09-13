package bottleopener.myapp;

import bottleopener.command.AddCommand;
import bottleopener.command.ByeCommand;
import bottleopener.command.DeleteCommand;
import bottleopener.command.FindCommand;
import bottleopener.command.ListCommand;
import bottleopener.command.MarkCommand;
import bottleopener.task.Tasklist;
import bottleopener.ui.Ui;

/**
 * The {@code Parser} class interprets user input and executes appropriate
 * commands in the BottleOpener chatbot. It processes the input string,
 * determines the command to be executed, and interacts with the task list.
 */
public class Parser {
    private final String[] userInput;
    private final String instruction;
    private final Tasklist tasklist;
    private boolean isExitCalled;

    /**
     * Constructs a {@code Parser} with the given input and task list.
     * The input is split into a command and its arguments.
     *
     * @param inp The raw user input.
     * @param tasklist The list of tasks to be manipulated by the user commands.
     */
    public Parser(String rawInput, Tasklist tasklist) {
        this.userInput = rawInput.trim().split(" ", 2);
        this.instruction = this.userInput[0].toLowerCase();
        this.tasklist = tasklist;
        this.isExitCalled = false;

    }

    /**
     * Parses the task index from the user input. This is used for commands like
     * "mark", "unmark", and "delete" that require a task index to identify a specific task.
     *
     * @return The index of the task as an integer.
     * @throws IllegalArgumentException If the task index is not provided or is invalid.
     */
    private int parseTaskIndex() throws IllegalArgumentException {
        try {
            return Integer.parseInt(this.userInput[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException(Ui.showMissingInfoError());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(Ui.showAppropriateNumberError());
        }
    }

    /**
     * Checks whether the 'exit' command has been called.
     *
     * @return {@code true} if the exit command has been issued, otherwise {@code false}.
     */
    public boolean checkExit() {
        return this.isExitCalled;
    }

    /**
     * Processes the parsed user input and executes the appropriate command
     * based on the instruction. Commands like "bye", "list", "mark", "unmark",
     * "delete", "find", and task creation commands ("todo", "deadline", "event")
     * are handled here.
     *
     * @return A string containing the result of the executed command or an error message.
     */
    public String runParser() {
        switch (instruction) {
        case "bye":
            this.isExitCalled = true;
            return new ByeCommand().runCommand();
        case "list":
            return new ListCommand(tasklist).runCommand();
        case "mark":
            try {
                int taskIndex = parseTaskIndex();
                return new MarkCommand(tasklist, taskIndex, true).runCommand();
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        case "unmark":
            try {
                int taskIndex = parseTaskIndex();
                return new MarkCommand(tasklist, taskIndex, false).runCommand();
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        case "delete":
            try {
                int taskIndex = parseTaskIndex();
                return new DeleteCommand(tasklist, taskIndex).runCommand();
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        case "find":
            try {
                String keyword = this.userInput[1].trim().toLowerCase();
                return new FindCommand(tasklist, keyword).runCommand();
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return Ui.showMissingInfoError();
            }
        case "todo":
        case "deadline":
        case "event":
            try {
                String taskInformation = this.userInput[1];
                return new AddCommand(tasklist, instruction, taskInformation).runCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.showMissingInfoError();
            }
        default:
            return Ui.showCommandFormatError();
        }
    }

}
