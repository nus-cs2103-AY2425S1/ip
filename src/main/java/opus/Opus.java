package opus;

import opus.exceptions.OpusException;
import opus.commands.Command;
import opus.commands.CommandResult;

/**
 * Represents the main class of the program.
 */
public class Opus {

    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a new instance of Opus.
     *
     * @param filePath The file path of the storage file.
     */
    public Opus(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Gets the response to the user input.
     *
     * @param input The user input.
     * @return The response to the user.
     */
    public CommandResult getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(taskList, storage);
            boolean isExit = command.isExit();
            return new CommandResult(response, isExit);
        } catch (OpusException e) {
            return new CommandResult(e.getMessage(), false);
        }
    }
}
