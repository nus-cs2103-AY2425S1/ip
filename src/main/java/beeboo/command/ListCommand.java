package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;

public class ListCommand extends Command{

    /**
     *
     * @param tasks tasklist of chatbot
     * @param ui ui of the chatbot
     * @param storage storage function of the chatbot
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.produceList(tasks.produceList());
    }

    /**
     *
     * @return true if is exit command false if not exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
