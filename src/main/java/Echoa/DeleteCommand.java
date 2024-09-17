package echoa;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int index;
    private Task task;

    public DeleteCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    @Override
    public void execute(String line) throws InvalidIndexInputException, ListOutOfBoundsException, IOException {
        index = parser.parseIndex(line);
        task = taskList.getSpecificTask(index);
        taskList.deleteTask(index);
        storage.handleChange(taskList);
    }

    public String getMessage() {
        return ui.getDeleteTaskMessage(taskList, task);
    }
}
