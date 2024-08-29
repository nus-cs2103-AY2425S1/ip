package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JEFFException;
import jeff.task.Task;

import java.util.ArrayList;


public class FindCommand extends Command {
    private String args;

    public FindCommand(String args) throws JEFFException {
        super();
        if (args.isEmpty()) {
            throw new JEFFException("You must provide a search term!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JEFFException {
        ArrayList<Task> list = tasks.getTasks();
        int counter = 1;
        ui.showMessage("There are the following results that match: ");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).getDescription().contains(args)) {
                ui.showMessage("" + counter + ". " + tasks.getTask(i));
                counter++;
            }
        }

        if (counter == 1) {
            ui.showMessage("I guess nothing matched...");
        }

    }
}
