package command;

import exception.ParserException;
import exception.TaskListException;
import task.Task;
import tasklist.TaskList;

/**
 * Handle related issues to untag command
 */
public class UntagCommand implements Command {
    private int idx;
    private String tag;
    /**
     * Construtor for the tag command
     * @param cmdline Command line from user to describe the command
     * @throws ParserException If invalid format for the command
     */
    public UntagCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 3);
        if (args.length < 3) {
            throw new ParserException("Not enough arguments for untag command");
        }
        try {
            idx = Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ParserException("Invalid argument idx for untag command: " + args[1]);
        }

        tag = args[2];
        if (!tag.startsWith("#") || tag.length() == 1) {
            throw new ParserException("Invalid argument tag for untag command: " + tag);
        }
    }

    @Override
    public String getResponse(TaskList tasks) throws TaskListException {
        String response = "";
        Task task = tasks.untag(idx, tag);
        response += "A task is untagged:\n";
        response += task + "\n";
        return response;
    }
}
