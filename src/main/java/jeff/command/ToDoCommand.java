package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JEFFException;
import jeff.task.ToDo;

public class ToDoCommand extends Command {
    private String args;

    public ToDoCommand(String args) throws JEFFException {
        super();
        if (args.isEmpty()) {
            throw new JEFFException("You must provide one number after the command!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JEFFException {
        tasks.addTask(new ToDo(args));
        storage.saveTask(tasks.getTasks());
        ui.showMessage("added: " + tasks.getTask(tasks.size() - 1));
    }
}