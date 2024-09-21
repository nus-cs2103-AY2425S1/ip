package command;

import exception.ParserException;
import tasklist.TaskList;

/**
 * Handles related issus to command list
 */
public class ListCommand extends Command {
    /**
     * Constructor for the list command from command line
     * @param cmdline The command line from user
     * @throws ParserException If invalid format found from command line
     */
    public ListCommand(String cmdline) throws ParserException {
        if (!cmdline.equals("list")) {
            throw new ParserException("Too much arguments for list command");
        }
    }

    @Override
    public String getResponse(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Oops, you have no task\n";
        } else {
            String response = "You have " + tasks.size() + " tasks as follow:\n";
            for (int i = 0; i < tasks.size(); ++i) {
                response += (i + 1) + ". " + tasks.get(i) + "\n";
            }
            return response;
        }
    }
}
