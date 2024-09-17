package command;

import parser.Parser;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents the To-Do command which creates a new
 * To-Do task.
 */
public class ToDoCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        try {
            Task toDoTask = parser.parseToDoTask(parts[1]);
            list.addTask(toDoTask);
            return ui.affirm() + toDoTask.getDescription() + "\n" +
                    String.format("Now you have %d tasks in the list\n", list.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Oh no! Please input a ToDo description!\n";
        }
    }
}
