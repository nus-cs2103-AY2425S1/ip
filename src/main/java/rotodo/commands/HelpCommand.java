package rotodo.commands;

import rotodo.processes.Gui;
import rotodo.processes.Storage;
import rotodo.tasklist.TaskList;

/**
 * The HelpCommand class encapsulates the specific
 * type of Command that outputs the help message.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) {
        gui.help();
    }
}
