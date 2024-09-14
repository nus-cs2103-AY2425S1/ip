package calebyyy;

import java.util.Scanner;

import calebyyy.commands.AddCommand;
import calebyyy.commands.ByeCommand;
import calebyyy.commands.Command;
import calebyyy.commands.DeleteCommand;
import calebyyy.commands.FindCommand;
import calebyyy.commands.ListCommand;
import calebyyy.commands.MarkCommand;
import calebyyy.commands.UnmarkCommand;
import calebyyy.exceptions.CalebyyyException;
import calebyyy.exceptions.InvalidCommandException;

/**
 * Represents a parser that reads and executes commands.
 */
public class Parser {
    private Command addCommand;
    private Command listCommand;
    private Command markCommand;
    private Command unmarkCommand;
    private Command byeCommand;
    private Command deleteCommand;
    private Command findCommand;
    private Scanner scanner;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for Parser.
     *
     * @param calebyyy The main Calebyyy object.
     * @param taskList The TaskList object responsible for storing tasks.
     * @param storage The Storage object responsible for loading and saving tasks.
     * @param ui The Ui object responsible for user interaction.
     */
    public Parser(Calebyyy calebyyy, TaskList taskList, Storage storage, Ui ui) {
        assert calebyyy != null : "Calebyyy object cannot be null";
        assert taskList != null : "TaskList object cannot be null";
        assert storage != null : "Storage object cannot be null";
        assert ui != null : "Ui object cannot be null";

        this.taskList = taskList;
        this.storage = storage;
        addCommand = new AddCommand(calebyyy, ui, taskList);
        listCommand = new ListCommand(calebyyy, ui, taskList);
        markCommand = new MarkCommand(calebyyy, ui, taskList);
        unmarkCommand = new UnmarkCommand(calebyyy, ui, taskList);
        byeCommand = new ByeCommand(calebyyy, ui, taskList);
        deleteCommand = new DeleteCommand(calebyyy, ui, taskList);
        findCommand = new FindCommand(calebyyy, ui, taskList);
        scanner = new Scanner(System.in);
    }

    /**
     * Represents the different types of commands.
     */
    public enum CommandType {
        ADD,
        LIST,
        MARK,
        UNMARK,
        BYE,
        DELETE,
        FIND
    }

    /**
     * Returns the command based on the command type.
     *
     * @param commandType The type of command.
     * @return The command.
     */
    public Command getCommand(CommandType commandType) {
        assert commandType != null : "CommandType cannot be null";

        switch (commandType) {
        case ADD:
            return addCommand;
        case LIST:
            return listCommand;
        case MARK:
            return markCommand;
        case UNMARK:
            return unmarkCommand;
        case BYE:
            return byeCommand;
        case DELETE:
            return deleteCommand;
        case FIND:
            return findCommand;
        default:
            throw new IllegalArgumentException("Invalid command type");
        }
    }

    /**
     * Starts the command loop.
     */
    public void startCommandLoop() {
        while (true) {
            readAndExecuteCommand();
        }
    }

    /**
     * Reads and executes a command.
     */
    private void readAndExecuteCommand() {
        String input = scanner.nextLine();
        try {
            executeCommand(input);
        } catch (CalebyyyException e) {
            System.out.println(e);
        }
    }

    /**
     * Executes a command.
     *
     * @param input The user input.
     * @throws CalebyyyException If an error occurs during execution.
     */
    public void executeCommand(String input) throws CalebyyyException {
        assert input != null && !input.trim().isEmpty() : "Input cannot be null or empty";
        CommandType commandType = parseCommandType(input);
        Command command = getCommand(commandType);
        assert command != null : "Command cannot be null";
        command.execute(input);
        storage.saveTasks(taskList);
    }

    /**
     * Parses the command type from the user input.
     *
     * @param input The user input.
     * @return The command type.
     * @throws InvalidCommandException If the command is invalid.
     */
    private CommandType parseCommandType(String input) throws InvalidCommandException {
        String[] parts = input.split(" ", 2);
        String commandName = parts[0];

        switch (commandName) {
        case "list":
            return CommandType.LIST;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "todo":
        case "deadline":
        case "event":
            return CommandType.ADD;
        case "delete":
            return CommandType.DELETE;
        case "bye":
            return CommandType.BYE;
        case "find":
            return CommandType.FIND;
        default:
            throw new InvalidCommandException();
        }
    }
}