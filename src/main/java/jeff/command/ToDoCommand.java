package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JeffException;
import jeff.task.ToDo;

/**
 * Represents a command that creates a new "to-do" task and adds it to the task list.
 * This command is initialised with a string argument that provides the description of the to-do task.
 */
public class ToDoCommand extends Command {
    private String args;

    /**
     * Constructs a {@code ToDoCommand} with the specified description for the to-do task.
     *
     * @param args the description of the to-do task. It must not be empty.
     * @throws JeffException if {@code args} is empty, which is essential to create a valid task.
     */
    public ToDoCommand(String args) throws JeffException {
        super();
        if (args.isEmpty()) {
            throw new JeffException("One number after the command, you must provide!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        tasks.addTask(new ToDo(args));
        storage.saveTask(tasks.getTasks());
        ui.showMessage("added: " + tasks.getTask(tasks.size() - 1));
    }
}
