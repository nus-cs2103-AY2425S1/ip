package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JEFFException;

public class MarkCommand extends Command {
    private String args;

    public MarkCommand(String args) throws JEFFException {
        super();
        if (args.isEmpty() || !isNumeric(args)) {
            throw new JEFFException("You must provide one number after the command!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JEFFException {
        int i = Integer.parseInt(args);
        if (i <= 0 || i > tasks.size()) {
            throw new JEFFException("The number is outside the range!");
        }
        tasks.getTask(i - 1).markDone();
        storage.updateSave(tasks.getTasks(), i - 1);
        ui.showMessage("Alrighty, I marked this as done:");
        ui.showMessage("" + tasks.getTask(i - 1));
    }
}