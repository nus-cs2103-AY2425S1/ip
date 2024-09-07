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
     * Checks if the description for the deadline task inputted by the user is empty or not.
     *
     * @return true if the user input does not have a description and false otherwise.
     */
    private boolean isDescriptionEmpty() {
        return !this.getInput().matches("deadline .+");
    }

    /**
     * Returns a newly created deadline task based on the user input.
     *
     * @return Deadline task based on user input.
     */
    private DeadlineTask createTask() throws JeffException {
        assert !this.isDescriptionEmpty() : "Deadline task description should not be empty when creating the task";

        // Split the description into content and deadline
        String taskDescription = this.getTaskDescription();

        String[] deadlineParts = taskDescription.split(" /by ", 2);
        String deadlineContent = deadlineParts[0];
        String deadlinePeriod = deadlineParts.length > 1 ? deadlineParts[1] : "";

        // Check if the format is correct
        try {
            return new DeadlineTask(deadlineContent, Parser.getLocalDateTime(deadlinePeriod));
        } catch (DateTimeParseException e) {
            throw new JeffException(
                    "The format is wrong! It should be \"deadline xx /by yyyy-mm-dd HH:mm or hh:mm AM/PM\"!"
            );
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
        if (this.isDescriptionEmpty()) {
            throw new JeffException("Sorry, the description of a deadline cannot be empty!");
        }

        Task targetTask = this.createTask();
        tasks.addTask(targetTask);
        storage.updateTaskListInDatabase(tasks);

        return this.getResponse(targetTask, tasks);
    }
}
