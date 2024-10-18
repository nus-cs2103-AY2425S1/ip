package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.task.Tasks;

/**
 * Represents a command to find a task to the chatbot's task list.
 */
public class FindCommand extends Command {

    /**
     * Constructs an AddCommand.
     *
     * @param command The command string that contains the task description and details.
     */
    public FindCommand(String command) {
        super(command);
    }
    /**
     * @param tasks tasklist of chatbot
     * @param ui ui of the chatbot
     * @param storage storage function of the chatbot
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String list = "Here are the matching results: \n";
        for (int i = 0; i < tasks.getSize(); i++) {
            Tasks task = tasks.get(i);
            if (task.toString().contains(super.command)) {
                list = list + task + "\n";
            }
        }

        return ui.produceList(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
