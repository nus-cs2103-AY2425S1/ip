package Tuesday.command;

import Tuesday.util.Storage;
import Tuesday.task.Task;
import Tuesday.util.Ui;

public class ExitCommand extends Command{

    /**
     * Constructor for ExitCommand
     *
     * @param command Description for the command
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Prints the bye message
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Use to exit the program
     *
     * @return true and do exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
