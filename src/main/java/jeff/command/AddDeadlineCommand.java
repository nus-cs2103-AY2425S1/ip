package jeff.command;

import java.time.format.DateTimeParseException;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.DeadlineTask;
import jeff.task.Task;
import jeff.task.TaskList;

/**
 * Represents an "Add Deadline task" command.
 */
public class AddDeadlineCommand extends AddCommand {
    private static final String WRONG_FORMAT_ERROR =
            "The format is wrong! It should be \"deadline(or dl) xx /by yyyy-mm-dd HH:mm(or hh:mm AM/PM)\"!";

    /**
     * Constructor for AddDeadlineCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public AddDeadlineCommand(String input) {
        super(input);
    }

    /**
     * Checks if the format of the user input is wrong or not.
     *
     * @return true if the user input is in the wrong format and false otherwise.
     */
    private boolean isFormatWrong() {
        return !this.getInput().matches("deadline .+") && !this.getInput().matches("dl .+");
    }

    /**
     * Returns a newly created deadline task based on the user input.
     *
     * @return Deadline task based on user input.
     */
    private DeadlineTask createTask() throws JeffException {
        assert !this.isFormatWrong() : "Deadline task should not be in the wrong format when creating the task";

        // Split the description into content and deadline
        String taskDescription = this.getTaskDescription();

        String[] deadlineParts = taskDescription.split(" /by ", 2);
        String deadlineContent = deadlineParts[0];
        String deadlinePeriod = deadlineParts.length > 1 ? deadlineParts[1] : "";

        // Check if the format is correct
        try {
            return new DeadlineTask(deadlineContent, Parser.getLocalDateTime(deadlinePeriod));
        } catch (DateTimeParseException e) {
            throw new JeffException(WRONG_FORMAT_ERROR);
        }
    }

    /**
     * Returns the string representation of the response by the chatbot Jeff
     * when a deadline task is added to the task list.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the task description is empty or the format is wrong.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        if (this.isFormatWrong()) {
            throw new JeffException(WRONG_FORMAT_ERROR);
        }

        Task targetTask = this.createTask();
        assert targetTask != null : "Target task should not be null";

        tasks.addTask(targetTask);
        assert tasks.contains(targetTask) : "Task list should contain the newly added task";

        storage.updateTaskListInDatabase(tasks);

        return this.getResponse(targetTask, tasks);
    }
}
