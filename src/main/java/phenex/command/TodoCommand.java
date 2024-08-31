package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.task.ToDo;
import phenex.ui.Ui;

public class TodoCommand extends CreateTaskCommand {

    public TodoCommand() {
        super("");
    }

    public TodoCommand(String name) {
        super(name);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        ToDo toDo = new ToDo(super.name);
        taskList.addTask(toDo);
        ui.printTaskAddedMessage(toDo, taskList.getTasks().size());
    }
}
