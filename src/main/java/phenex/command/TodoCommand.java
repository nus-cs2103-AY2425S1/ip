package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.task.ToDo;
import phenex.ui.Ui;

/**
 * TodoCommand class which encapsulates a Command which creates a ToDo task.
 */
public class TodoCommand extends CreateTaskCommand {

    public TodoCommand() {
        super("");
    }

    public TodoCommand(String name) {
        super(name);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        ToDo toDo = new ToDo(super.name);
        if (taskList.containsTask(toDo)) {
            throw new PhenexException("Error: Duplicate Mission. Aborting!");
        }
        taskList.addTask(toDo);
        return ui.printTaskAddedMessage(toDo, taskList.getTasks().size());
    }
}
