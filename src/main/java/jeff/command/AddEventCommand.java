package jeff.command;

import java.time.format.DateTimeParseException;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.EventTask;
import jeff.task.Task;
import jeff.task.TaskList;

/**
 * Represents an "Add Event task" command.
 */
public class AddEventCommand extends AddCommand {
    /**
     * Constructor for AddEventCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public AddEventCommand(String input) {
        super(input);
    }

    /**
     * Checks if the description for the event task inputted by the user is empty or not.
     *
     * @return true if the user input does not have a description and false otherwise.
     */
    private boolean isDescriptionEmpty() {
        return !this.getInput().matches("event .+");
    }

    /**
     * Returns a newly created event task based on the user input.
     *
     * @return Event task based on user input.
     */
    private EventTask createTask() throws JeffException {
        assert !this.isDescriptionEmpty() : "Event task description should not be empty when creating the task";

        // Split the description into content, start and end
        String taskDescription = this.getTaskDescription();

        String[] eventParts = taskDescription.split(" /from ", 2);
        String eventContent = eventParts[0];
        String eventPeriod = eventParts.length > 1 ? eventParts[1] : "";

        String[] eventPeriodParts = eventPeriod.split(" /to ", 2);
        String startPeriod = eventPeriodParts[0];
        String endPeriod = eventPeriodParts.length > 1 ? eventPeriodParts[1] : "";

        // Check if the format is correct
        try {
            return new EventTask(
                    eventContent,
                    Parser.getLocalDateTime(startPeriod),
                    Parser.getLocalDateTime(endPeriod)
            );
        } catch (DateTimeParseException e) {
            throw new JeffException("The format is wrong! "
                    + "It should be \"event xx /from yyyy-mm-dd HH:mm /to yyyy-mm--dd HH:mm\" or "
                    + "\"event xx /from yyyy-mm-dd hh:mm AM/PM /to yyyy-mm--dd hh:mm AM/PM\"!"
            );
        }
    }

    /**
     * Returns the string representation of the response by the chatbot Jeff
     * when an event task is added to the task list.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the task description is empty or the format is wrong.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        if (this.isDescriptionEmpty()) {
            throw new JeffException("Sorry, the description of an event cannot be empty!");
        }

        Task targetTask = this.createTask();
        tasks.addTask(targetTask);
        storage.updateTaskListInDatabase(tasks);

        return this.getResponse(targetTask, tasks);
    }
}
