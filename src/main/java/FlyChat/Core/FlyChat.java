package flychat.core;

import java.util.InputMismatchException;

import flychat.command.AddTaskCommand;
import flychat.command.ByeCommand;
import flychat.command.Command;
import flychat.command.DeleteCommand;
import flychat.command.FindCommand;
import flychat.command.InvalidCommand;
import flychat.command.ListCommand;
import flychat.command.MarkCommand;
import flychat.command.TagCommand;
import flychat.command.UnmarkCommand;

/**
 * Contains the main methods and components for FlyChat.
 */
public class FlyChat {

    private final Ui ui = new Ui();
    private final Storage storage = new Storage();
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser();

    /**
     * Enumerates the different types of commands that can be processed by FlyChat.
     */
    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, TAG, BYE, INVALID
    }

    public FlyChat() {
        assert ui != null : "Ui object is not initialized";
        assert storage != null : "Storage object is not initialized";
        assert taskList != null : "TaskList object is not initialized";
        assert parser != null : "Parser object is not initialized";
    }

    /**
     * Links the FlyChat instance to a save file.
     */
    public void startUp() {
        storage.findSaveFile("./data/save.txt");
        storage.loadSaveFile(taskList);
    }

    /**
     * Greets the user.
     *
     * @return The greeting message.
     */
    public static String greetUser() {
        return Ui.greetUser();
    }

    /**
     * Shuts down the FlyChat instance.
     *
     * @return The shutdown message.
     */
    public String shutDown() {
        return ui.bye();
    }

    public String getResponse(String inputString) {
        try {
            return processCommands(inputString);
        } catch (InputMismatchException e) {
            return ui.announceString("I'm not sure what task you want me to do :((");
        }
    }

    /**
     * Processes the input string and returns the response.
     *
     * @param inputString The input string from the user.
     * @return The response string.
     * @throws InputMismatchException If the input string is invalid.
     */
    private String processCommands(String inputString) throws InputMismatchException {
        CommandType commandType = getCommandType(parser.parseCommand(inputString));
        Command command = getCommand(commandType);
    
        String finalString = command.execute(taskList, ui, parser, inputString);
        storage.writeToSave(taskList.formatSaveString());
        return finalString;
    }

    private CommandType getCommandType(String commandString) {
        switch (commandString) {
        case "list":
            return CommandType.LIST;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "todo":
        case "deadline":
        case "event":
            return CommandType.TODO;
        case "delete":
            return CommandType.DELETE;
        case "tag":
            return CommandType.TAG;
        case "find":
            return CommandType.FIND;
        case "bye":
            return CommandType.BYE;
        default:
            return CommandType.INVALID;
        }
    }
    
    private Command getCommand(CommandType commandType) {
        switch (commandType) {
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand();
        case UNMARK:
            return new UnmarkCommand();
        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddTaskCommand();
        case DELETE:
            return new DeleteCommand();
        case FIND:
            return new FindCommand();
        case TAG:
            return new TagCommand();
        case BYE:
            return new ByeCommand();
        default:
            return new InvalidCommand();
        }
    }

}
