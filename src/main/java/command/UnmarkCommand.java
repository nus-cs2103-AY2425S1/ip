package command;

import exception.ParserException;
import exception.TaskListException;
import task.Task;
import tasklist.TaskList;

/**
 * Handles related issus to command unmark
 */

public class UnmarkCommand extends Command {
    private int idx;
    /**
     * Constructor for the unmark command from command line
     * @param cmdline The command line from user
     * @throws ParserException If invalid format found from command line
     */
    public UnmarkCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);
        if (args.length == 1) {
            throw new ParserException("Missing argument idx for unmark command");
        }
        try {
            this.idx = Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ParserException("Invalid argument idx for unmark command: " + args[1]);
        }
    }

    @Override
    public String getResponse(TaskList tasks) throws TaskListException {
        String response = "";
        Task task = tasks.unmark(this.idx);
        response += "A task is unmarked\n";
        response += task + "\n";
        return response;
    }
}
