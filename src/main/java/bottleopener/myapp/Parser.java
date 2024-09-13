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
     * This method processes various commands such as "bye", "list", "mark", "unmark",
     * "delete", "find", "todo", "deadline", and "event". It calls the appropriate command
     * and returns the result. If an unknown or incorrectly formatted command is provided,
     * an error message is returned.
     *
     * @return the result of the executed command or an error message if the input is invalid.
     */
    public String runParser() {
        try {
            switch (instruction) {
            case "bye":
                this.isExitCalled = true;
                return new ByeCommand().runCommand();
            case "list":
                return new ListCommand(tasklist).runCommand();
            case "mark":
                int markIndex = parseTaskIndex();
                return new MarkCommand(tasklist, markIndex, true).runCommand();
            case "unmark":
                int unmarkIndex = parseTaskIndex();
                return new MarkCommand(tasklist, unmarkIndex, false).runCommand();
            case "delete":
                int deleteIndex = parseTaskIndex();
                return new DeleteCommand(tasklist, deleteIndex).runCommand();
            case "find":
                String keyword = parseKeyword();
                return new FindCommand(tasklist, keyword).runCommand();
            case "todo":
            case "deadline":
            case "event":
                String taskInformation = parseTaskInformation();
                return new AddCommand(tasklist, instruction, taskInformation).runCommand();
            default:
                return Ui.showCommandFormatError();
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

}
