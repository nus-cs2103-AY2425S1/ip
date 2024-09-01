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
        String[] parts = input.split(" ", 2);
        String commandName = parts[0];

        CommandType commandType;
        switch (commandName) {
        case "list":
            commandType = CommandType.LIST;
            break;
        case "mark":
            commandType = CommandType.MARK;
            break;
        case "unmark":
            commandType = CommandType.UNMARK;
            break;
        case "todo":
        case "deadline":
        case "event":
            commandType = CommandType.ADD;
            break;
        case "delete":
            commandType = CommandType.DELETE;
            break;
        case "bye":
            commandType = CommandType.BYE;
            break;
        case "find":
            commandType = CommandType.FIND;
            break;
        default:
            throw new InvalidCommandException();
        }

        Command command = getCommand(commandType);
        command.execute(input);
        storage.saveTasks(taskList);
    }
}
