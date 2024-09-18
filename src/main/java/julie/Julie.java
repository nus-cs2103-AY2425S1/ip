package julie;

import java.util.ArrayList;
import java.util.List;

import julie.command.Command;
import julie.exception.JulieException;
import julie.misc.Parser;
import julie.misc.Storage;
import julie.task.Task;

/**
 * The Class that represents the julie.Julie Chat Bot.
 */
public class Julie {
    private static final String NAME = "Julie";
    private final List<Task> taskList = new ArrayList<>();
    private final Storage storage;

    /**
     * The public constructor for a Julie Chat bot.
     */
    public Julie() {
        storage = new Storage();
        storage.load(taskList);
    }
    /**
     * The public method that returns the response from the user input.
     *
     * @param input The user input given.
     * @return The corresponding Chat Bot Response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.run(taskList, storage);
        } catch (JulieException e) {
            return e.getMessage();
        }
    }
}
