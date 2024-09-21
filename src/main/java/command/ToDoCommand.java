package command;

import exception.ParserException;
import task.Task;
import task.ToDo;
import tasklist.TaskList;

/**
 * Handles related issus to command todo
 */
public class ToDoCommand extends Command {
    private Task todo;
    /**
     * Constructor for the todo command from command line
     * @param cmdline The command line from user
     * @throws ParserException If invalid format found from command line
     */
    public ToDoCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);
        if (args.length == 1) {
            throw new ParserException("Missing arguments for todo command");
        }
        String description = args[1];
        this.todo = new ToDo(description);
    }

    @Override
    public String getResponse(TaskList tasks) {
        String response = "";
        tasks.add(this.todo);
        response += "A task is added\n";
        response += this.todo + "\n";
        return response;
    }
}
