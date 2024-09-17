package command;

import exception.ParserException;
import task.Task;
import task.ToDo;
import tasklist.TaskList;
import ui.Ui;

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
    public void execute(TaskList tasks, Ui ui) {
        tasks.add(this.todo);
        ui.println("A task is added");
        ui.println(this.todo);
    }
}
