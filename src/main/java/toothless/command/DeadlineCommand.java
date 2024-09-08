package toothless.command;

import toothless.exceptions.NoDescriptionExceptions;
import toothless.exceptions.NoTimelineExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.Deadline;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {

    private String description;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description Description of the deadline task.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if (description.isEmpty()) {
            throw new NoDescriptionExceptions("deadline", "deadline <description> /by <timing>");
        } else if (!description.contains("/by")) {
            throw new NoTimelineExceptions("deadline", "deadline <description> /by <timing>");
        }
        String[] splitDeadline = description.split("/by");
        if (splitDeadline.length != 2) {
            throw new NoTimelineExceptions("deadline", "deadline <description> /by <timing>");
        }
        String response = taskList.addTask(new Deadline(splitDeadline[0], splitDeadline[1]), ui, taskList);
        storage.saveTask(taskList.getList());
        return response;
    }
}
