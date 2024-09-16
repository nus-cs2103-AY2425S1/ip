package bottleopener.myapp;

import bottleopener.command.AddCommand;
import bottleopener.command.ByeCommand;
import bottleopener.command.Command;
import bottleopener.command.DeleteCommand;
import bottleopener.command.ErrorCommand;
import bottleopener.command.FindCommand;
import bottleopener.command.ListCommand;
import bottleopener.command.MarkCommand;
import bottleopener.command.RemindCommand;
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
     * Constructs a {@code Parser} object to process user input and interact with the provided task list.
     *
     * <p>The constructor takes raw user input, ensures it is not empty, and splits it into
     * a command and arguments. The command is stored as the instruction, and the task list
     * is passed to the parser for task management operations. The input string is trimmed
     * of leading and trailing spaces before processing.
     *
     * <p>Assertions are used to ensure that the input is non-empty and that the provided
     * task list is not null.
     *
     * @param rawInput The raw input string from the user, which should contain a command
     *                 and any optional arguments.
     * @param tasklist The {@code Tasklist} object that contains the current list of tasks
     *                 to be managed by the parser. This must not be {@code null}.
     *
     * @throws AssertionError if {@code rawInput} is empty or {@code tasklist} is null.
     */
    public Parser(String rawInput, Tasklist tasklist) {
        assert !rawInput.trim().isEmpty() : "Input cannot be empty";
        assert tasklist != null : "Tasklist cannot be null";

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
     * Parses and returns the keyword from the user input.
     * This method expects the user input array to contain at least two elements,
     * with the second element being the keyword to be used. If the keyword is missing
     * or incorrectly formatted, it will return an error message via the UI.
     *
     * @return the parsed keyword in lowercase if present, otherwise returns an error message.
     * @throws IllegalArgumentException if the input is invalid.
     */
    private String parseKeyword() throws IllegalArgumentException {
        try {
            return this.userInput[1].trim().toLowerCase();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException(Ui.showMissingInfoError());
        }
    }

    /**
     * Parses and returns the task information from the user input.
     * This method expects the user input array to contain at least two elements,
     * with the second element being the task information. If the task information
     * is missing or the array is not properly indexed, it will throw an
     * {@code IllegalArgumentException} with an error message from the UI.
     *
     * @return the task information as a {@code String} if present.
     * @throws IllegalArgumentException if the task information is missing or the input format is incorrect.
     */
    private String parseTaskInformation() throws IllegalArgumentException {
        try {
            return this.userInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(Ui.showMissingInfoError());
        }
    }

    /**
     * Checks whether the "exit" command has been called.
     * This method is used to determine if the chatbot should terminate.
     *
     * @return {@code true} if the "exit" command was called, otherwise {@code false}.
     */
    public boolean checkExit() {
        return this.isExitCalled;
    }

    /**
     * Parses the user input and executes the corresponding command.
     * This method processes various commands such as "remind", "bye", "list", "mark",
     * "unmark", "delete", "find", "todo", "deadline", and "event". It calls the appropriate command
     * and returns the result. If an unknown or incorrectly formatted command is provided,
     * an error message is returned.
     *
     * @return the result of the executed command or an error message if the input is invalid.
     */
    public Command runParser() throws IllegalArgumentException {
        switch (instruction) {
        case "remind":
            return new RemindCommand(tasklist);
        case "bye":
            this.isExitCalled = true;
            return new ByeCommand();
        case "list":
            return new ListCommand(tasklist);
        case "mark":
            int markIndex = parseTaskIndex();
            return new MarkCommand(tasklist, markIndex, true);
        case "unmark":
            int unmarkIndex = parseTaskIndex();
            return new MarkCommand(tasklist, unmarkIndex, false);
        case "delete":
            int deleteIndex = parseTaskIndex();
            return new DeleteCommand(tasklist, deleteIndex);
        case "find":
            String keyword = parseKeyword();
            return new FindCommand(tasklist, keyword);
        case "todo":
        case "deadline":
        case "event":
            String taskInformation = parseTaskInformation();
            return new AddCommand(tasklist, instruction, taskInformation);
        default:
            return new ErrorCommand();
        }
    }

    /**
     * Executes the parsed command and returns the result as a string.
     *
     * <p>This method first attempts to run the parser and execute the corresponding command.
     * If the command is valid and successfully parsed, the result of the command execution
     * is returned as a string. In case of an invalid or unrecognized command, an
     * {@code IllegalArgumentException} is caught, and its message is returned.
     *
     * @return The result of the command execution as a string. If the command is invalid,
     *         it returns the error message from the thrown {@code IllegalArgumentException}.
     */
    public String executeCommand() {
        try {
            return this.runParser().runCommand();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

}
