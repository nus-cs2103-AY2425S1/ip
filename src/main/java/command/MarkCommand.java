package command;

import exception.ParserException;
import exception.TaskListException;
import task.Task;
import tasklist.TaskList;

/**
 * Handles related issus to command mark
 */

public class MarkCommand extends Command {
    private int idx;
    /**
     * Constructor for the mark command from command line
     * @param cmdline The command line from user
     * @throws ParserException If invalid format found from command line
     */
    public MarkCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);
        if (args.length == 1) {
            throw new ParserException("Missing argument idx for mark command");
        }
        try {
            this.idx = Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ParserException("Invalid argument idx for mark command: " + args[1]);
        }
    }

    @Override
    public String getResponse(TaskList tasks) throws TaskListException {
        String response = "";
        Task task = tasks.mark(this.idx);
        response += "A task is marked\n";
        response += task;
        return response;
    }
}
