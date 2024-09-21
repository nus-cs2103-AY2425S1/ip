package command;

import exception.ParserException;
import exception.TaskListException;
import task.Task;
import tasklist.TaskList;

/**
 * Handles related issues to command delete
 */
public class DeleteCommand extends Command {
    private int idx;
    /**
     * Constructors for command delete from command line
     * @param cmdline The command line from user
     * @throws ParserException If invalid format found from the command line
     */
    public DeleteCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);
        if (args.length == 1) {
            throw new ParserException("Missing argument idx for delete command");
        }
        try {
            this.idx = Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ParserException("Invalid argument idx for delete command: " + args[1]);
        }
    }

    @Override
    public String getResponse(TaskList tasks) throws TaskListException {
        String response = "";
        Task task = tasks.delete(this.idx);
        response += "A task is deleted\n";
        response += task.toString() + "\n";
        return response;
    }
}
