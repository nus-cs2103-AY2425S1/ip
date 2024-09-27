package zaibot;

import zaibot.command.Command;
import zaibot.exception.ZaibotException;
import zaibot.utils.Parser;
import zaibot.utils.Storage;
import zaibot.utils.TaskList;

/**
 * The class that represents the bot.
 */
public class Zaibot {

    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage(taskList);

    /**
     * This method takes in an input and runs the command.
     * @param input Any command
     * @return The result string.
     */
    public String runCommand(String input) {
        String endMessage;
        try {
            Command command = Parser.parse(input);
            endMessage = command.execute(taskList, storage);

        } catch (ZaibotException e) {
            endMessage = e.getMessage();
        }
        return endMessage;
    }
}
