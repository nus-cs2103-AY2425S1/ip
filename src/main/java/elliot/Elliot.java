package elliot;

import command.Command;
import command.CommandType;
import exception.ElliotException;
import utility.Storage;
import utility.Strip;
import utility.TaskList;
import utility.Ui;

/**
 * The Elliot class is the main driver class and central component for the whole chatbot.
 */
public class Elliot {
    private Storage storage;
    private TaskList taskList;
    private CommandType commandType = CommandType.LIST;

    /**
     * Creates Elliot obect with storage and taskList loaded
     */
    public Elliot() {
        storage = new Storage("./data/ElliotTaskList.ser");
        taskList = storage.loadTaskList();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Ui.flushOutputString();
        input = input.strip();
        String[] commandString = Strip.stripStringArray(input.toLowerCase().split(" ", 2));
        Ui.say("");
        try {
            commandType = CommandType.parseStringToCommand(commandString[0]);
            Command command = commandType.getCommand();
            command = command.parseArguments(commandString.length < 2
                    ? ""
                    : commandString[1]);
            taskList = command.runCommand(taskList, storage);
        } catch (ElliotException e) {
            Ui.say(e.getMessage());
        }
        return Ui.getOutputString();
    }

    /**
     * Returns the command type of the parsed command.
     */
    public CommandType getCommandType() {
        return commandType;
    }

}
