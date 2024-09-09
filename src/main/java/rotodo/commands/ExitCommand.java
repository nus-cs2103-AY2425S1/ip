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
    public void execute(TaskList tl, Gui ui, Storage st) {
        assert ui != null;
        assert st != null;
        assert tl != null;
        ui.exit();
        try {
            st.saveList(tl);
        } catch (IOException e) {
            ui.addMessage("\nUnable to save list :(\n    RoTodo is sorry");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
