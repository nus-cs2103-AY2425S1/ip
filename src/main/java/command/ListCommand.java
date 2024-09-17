package command;

import exception.ParserException;
import tasklist.TaskList;
import ui.Ui;

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
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.isEmpty()) {
            ui.println("Oops, you have no task");
        } else {
            ui.println("You have " + tasks.size() + " tasks as follow:");
            for (int i = 0; i < tasks.size(); ++i) {
                ui.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
