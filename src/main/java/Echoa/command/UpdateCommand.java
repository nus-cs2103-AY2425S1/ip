package echoa.command;

import echoa.exception.DateFormatException;
import echoa.exception.InvalidIndexInputException;
import echoa.exception.TimeFormatException;
import echoa.exception.UpdateFormatException;
import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;
import echoa.task.Deadline;
import echoa.task.Event;
import echoa.task.Task;
import echoa.task.ToDo;

import java.io.IOException;

/**
 * UpdateCommand is a class which encapsulates Update Commands.
 * It extends from Command.
 */
public class UpdateCommand extends Command {

    private int index;
    private Task task;

    public UpdateCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    /**
     * Updates the task at the label given with their updated details.
     *
     * @param line input line given by user.
     */
    @Override
    public void execute(String line) throws InvalidIndexInputException, UpdateFormatException, IOException, DateFormatException, TimeFormatException {
        index = parser.parseUpdateIndex(line);
        task = taskList.getSpecificTask(index);
        String updateDetails = Parser.removeFirstOccurrence(line, String.valueOf (index + 1));
        Object[] details = {};

        assert task instanceof ToDo ||
                task instanceof Deadline ||
                task instanceof Event;

        if (task instanceof ToDo) {
            details = parser.parseToDoUpdate(updateDetails);
        } else if (task instanceof Deadline) {
            details = parser.parseDeadlineUpdate(updateDetails);
        } else if (task instanceof Event) {
            details = parser.parseEventUpdate(updateDetails);
        }
        task.update(details);
        storage.handleChange(taskList);
    }


    /**
     * Returns Echoa's reply to the Update Command, the updated task.
     *
     * @return a String message of the updated task.
     */
    @Override
    public String getMessage() {
        return ui.getUpdateTaskMessage(task, index + 1);
    }
}
