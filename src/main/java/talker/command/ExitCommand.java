package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents exit command to end current instance of chatbot
 */
public class ExitCommand extends Command {

    /**
     * Executes exit command
     *
     * @param list list of tasks to write into file
     * @param ui ui object to print output
     * @param storage storage object to write list into
     * @throws TalkerException if unable to write into file
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        storage.writeFile(list);
        ui.printGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
