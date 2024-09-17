package echoa;

import java.io.IOException;

public class ToDoCommand extends Command {
    public ToDoCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    @Override
    public void execute(String line) throws InvalidToDoContentException, IOException {
        Object[] todo = parser.parseToDoTask(line);
        String todoDescription = (String) todo[0];
        taskList.addTask(new ToDo(todoDescription));
        storage.handleChange(taskList);
    }

    @Override
    public String getMessage() {
        return ui.getAddTaskMessage(taskList);
    }
}
