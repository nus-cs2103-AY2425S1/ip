package rotodo.commands;

import rotodo.processes.Gui;
import rotodo.processes.Storage;
import rotodo.tasklist.TaskList;

/**
 * The ListCommand class encapsulates the specific
 * type of Command that outputs the tasklist.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) {
        gui.addMessage(tasks.toString());
    }
}
