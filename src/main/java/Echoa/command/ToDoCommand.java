package echoa.command;

import echoa.exception.InvalidToDoContentException;
import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;
import echoa.task.ToDo;

import java.io.IOException;

/**
 * ToDoCommand is a class which encapsulates ToDo Commands.
 * It extends from Command.
 */
public class ToDoCommand extends Command {
    public ToDoCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    /**
     * Adds a todo task.
     *
     * @param line input line given by user.
     */
    @Override
    public void execute(String line) throws InvalidToDoContentException, IOException {
        Object[] todo = parser.parseToDoTask(line);
        String todoDescription = (String) todo[0];
        taskList.addTask(new ToDo(todoDescription));
        storage.handleChange(taskList);
    }

    /**
     * Returns Echoa's reply to the adding the ToDo Command, the todo task.
     *
     * @return a String message of the added todo task.
     */
    @Override
    public String getMessage() {
        return ui.getAddTaskMessage(taskList);
    }
}
