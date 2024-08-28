package rotodo.commands;

import java.io.IOException;

import rotodo.processes.Storage;
import rotodo.processes.Ui;
import rotodo.tasklist.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tl, Ui ui, Storage st) {
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
