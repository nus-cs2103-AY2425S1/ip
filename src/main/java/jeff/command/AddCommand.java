package jeff.command;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.*;
import jeff.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents an "Add task" command.
 */
public class AddCommand extends Command {
    /**
     * Constructor for AddCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public AddCommand(String input) {
        super(input);
    }

    /**
     * Categorises the task based on the user's input and adds it to the task list.
     * Also prints out a statement to tell the user the task has been added.
     *
     * @param tasks Task list.
     * @param ui UI to print statements.
     * @param storage Place to get and write the task list to the tasks text file.
     * @throws JeffException if the user's input is in the wrong format or if the task number does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        // Check if the input is in the correct format
        if (this.getInput().matches("todo .+") ||
                this.getInput().matches("deadline .+") ||
                this.getInput().matches("event .+")) {

            // Split the string to obtain the task type and task description
            String[] taskParts = this.getInput().split(" ", 2);
            String taskType = taskParts[0];
            String taskDescription = taskParts[1];

            // Initialise the task
            Task targetTask = null;

            // Check the task type and initialise accordingly
            switch (taskType) {
            case "todo":
                targetTask = new ToDoTask(taskDescription);
                break;

            case "deadline":
                // Split the description into content and deadline
                String[] deadlineParts = taskDescription.split(" /by ", 2);
                String deadlineContent = deadlineParts[0];
                String deadlinePeriod = deadlineParts.length > 1 ? deadlineParts[1] : "";

                // Check if the format is correct
                try {
                    targetTask = new DeadlineTask(deadlineContent, Parser.getLocalDateTime(deadlinePeriod));
                } catch (DateTimeParseException e) {
                    throw new JeffException(
                            "The format is wrong! It should be \"deadline xx /by yyyy-mm-dd HH:mm or hh:mm AM/PM\"!");
                }
                break;

            case "event":
                // Split the description into content, start and end
                String[] eventParts = taskDescription.split(" /from ", 2);
                String eventContent = eventParts[0];
                String eventPeriod = eventParts.length > 1 ? eventParts[1] : "";
                String[] eventPeriodParts = eventPeriod.split(" /to ", 2);
                String startPeriod = eventPeriodParts[0];
                String endPeriod = eventPeriodParts.length > 1 ? eventPeriodParts[1] : "";

                // Check if the format is correct
                try {
                    targetTask = new EventTask(
                            eventContent, Parser.getLocalDateTime(startPeriod), Parser.getLocalDateTime(endPeriod));
                } catch (DateTimeParseException e) {
                    throw new JeffException("The format is wrong! "
                            + "It should be \"event xx /from yyyy-mm-dd HH:mm /to yyyy-mm--dd HH:mm\"!");
                }
                break;

            default:
                break;
            }

            if (targetTask != null) {
                // Add the task to the taskList and storage
                tasks.add(targetTask);
                storage.writeTaskList(tasks);

                // Print out the statement
                ui.printText("Got it. I've added this task:\n   " + targetTask.toString()
                        + "\n Now you have " + tasks.size() + " tasks in the list.");

            } else {
                throw new JeffException();
            }

        } else {
            // Check if the input is just the command without any description
            if (this.getInput().equals("todo") || this.getInput().equals("todo ")) {
                throw new JeffException("Sorry, the description of a todo cannot be empty!");
            } else if (this.getInput().equals("deadline") || this.getInput().equals("deadline ")) {
                throw new JeffException("Sorry, the description of a deadline cannot be empty!");
            } else if (this.getInput().equals("event") || this.getInput().equals("event ")) {
                throw new JeffException("Sorry, the description of a event cannot be empty!");
            } else {
                throw new JeffException();
            }
        }
    }
}
