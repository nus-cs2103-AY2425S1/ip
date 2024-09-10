package joe.command;

import java.io.IOException;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;

/**
 * Represents the command when the user exits the app with 'bye'.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        ui.printExit();
        try {
            storage.saveData(taskList);
        } catch (IOException e) {
            throw new JoeException("Failed to save");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }

    public static String getHelp() {
        return "To exit, try: bye";
    }
}
