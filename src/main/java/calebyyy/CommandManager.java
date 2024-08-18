package calebyyy;

import calebyyy.commands.AddCommand;
import calebyyy.commands.ByeCommand;
import calebyyy.commands.Command;
import calebyyy.commands.ListCommand;
import calebyyy.commands.MarkCommand;
import calebyyy.commands.UnmarkCommand;
import calebyyy.commands.DeleteCommand;
import calebyyy.exceptions.InvalidCommandException;
import calebyyy.exceptions.CalebyyyException;
import java.util.Scanner;

public class CommandManager {
    private Command addCommand;
    private Command listCommand;
    private Command markCommand;
    private Command unmarkCommand;
    private Command byeCommand;
    private Command deleteCommand;
    private Scanner scanner;

    public CommandManager(Calebyyy calebyyy) {
        addCommand = new AddCommand(calebyyy);
        listCommand = new ListCommand(calebyyy);
        markCommand = new MarkCommand(calebyyy);
        unmarkCommand = new UnmarkCommand(calebyyy);
        byeCommand = new ByeCommand(calebyyy);
        deleteCommand = new DeleteCommand(calebyyy);
        scanner = new Scanner(System.in);
    }

    public enum CommandType {
        ADD,
        LIST,
        MARK,
        UNMARK,
        BYE,
        DELETE
    }

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
            default:
                throw new IllegalArgumentException("Invalid command type");
        }
    }

    public void startCommandLoop() {
        while (true) {
            readAndExecuteCommand();
        }
    }

    private void readAndExecuteCommand() {
        String input = scanner.nextLine();
        try {
            executeCommand(input);
        } catch (CalebyyyException e) {
            System.out.println(e);
        }
    }

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
            default:
                throw new InvalidCommandException();
        }

        Command command = getCommand(commandType);
        command.execute(input);
    }
}