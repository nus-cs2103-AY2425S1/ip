package echoa.command;

import echoa.exception.InvalidDeadlineContentException;
import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;
import echoa.task.Deadline;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * DeadlineCommand is a class which encapsulates Deadline Commands.
 * It extends from Command.
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    /**
     * Adds a deadline task.
     *
     * @param line input line given by user.
     */
    @Override
    public void execute(String line) throws InvalidDeadlineContentException, IOException {
        Object[] deadline = parser.parseDeadlineTask(line);
        String deadlineDescription = (String) deadline[0];
        LocalDateTime dateAndTime = (LocalDateTime) deadline[1];
        taskList.addTask(new Deadline(deadlineDescription, dateAndTime));
        storage.handleChange(taskList);
    }

    /**
     * Returns Echoa's reply to the adding the Deadline Command, the deadline task.
     *
     * @return a String message of the added deadline task.
     */
    @Override
    public String getMessage() {
        return ui.getAddTaskMessage(taskList);
    }
}
