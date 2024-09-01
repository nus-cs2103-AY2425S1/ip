package mummy.command;

import java.util.HashMap;

import mummy.task.TaskList;
import mummy.task.ToDo;
import mummy.ui.MummyException;
import mummy.ui.Ui;
import mummy.utility.Storage;

public class ToDoCommand extends Command {

    public ToDoCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MummyException {
        String description = this.getArgument("description", "");
        addTask(new ToDo(description), taskList, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
