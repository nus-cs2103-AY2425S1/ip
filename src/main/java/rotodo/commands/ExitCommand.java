package rotodo.commands;

import java.io.IOException;

import rotodo.processes.Gui;
import rotodo.processes.Storage;
import rotodo.tasklist.TaskList;

/**
 * The ExitCommand class encapsulates the specific
 * type of Command that outputs the exit message.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) {
        gui.exit();
        try {
            storage.saveList(tasks);
        } catch (IOException e) {
            gui.addMessage("\nUnable to save list :(\nRoTodo is sorry");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
