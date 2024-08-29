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
     * Create a Todo in tasklist.
     * @param tasklist
     * @param ui
     * @param storage
     * @throws ReminderebotException
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ToDo todo = new ToDo(command);
        tasklist.addTask(todo);
        ui.addTask(todo, tasklist.length());
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
