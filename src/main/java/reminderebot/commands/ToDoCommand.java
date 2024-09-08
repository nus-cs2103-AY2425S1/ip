package reminderebot.commands;

import reminderebot.ReminderebotException;
import reminderebot.Storage;
import reminderebot.TaskList;
import reminderebot.Ui;
import reminderebot.task.ToDo;

/**
 * The ToDoCommand class represents a command to create a ToDo.
 */
public class ToDoCommand extends Command {
    private final String command;

    /**
     * Create a ToDoCommand.
     * @param command
     */
    public ToDoCommand(String command) {
        this.command = command;
    }

    /**
     * Create a ToDo in tasklist.
     * @param tasklist
     * @param ui
     * @param storage
     * @return String representing ToDo command
     * @throws ReminderebotException
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        ToDo todo = new ToDo(command);
        tasklist.addTask(todo);
        return ui.addTask(todo, tasklist.length());
    }

    /**
     * ToDo does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
