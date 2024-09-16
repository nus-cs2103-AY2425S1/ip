package elysia.command;

import java.util.ArrayList;

import elysia.task.Task;
import elysia.ui.Ui;

/**
 * Represents a print list command.
 */
public class PrintListCommand extends Command {
    public PrintListCommand() {
        super();
    }

    /**
     * Prints the list to user.
     *
     * @param tasks
     * @return
     */
    @Override
    public String execute(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        return ui.printList(tasks);
    }
}
