package hoshi.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import hoshi.exception.HoshiException;
import hoshi.task.Deadline;
import hoshi.task.Event;
import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.task.Todo;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * AddCommand where the logic for adding a task is handled.
 */
public class AddCommand implements Command {

    /**
     * stores the constant value 2 for description index.
     */
    private static final int DESCRIPTION_INDEX = 2;

    /**
     * Stores the user input after splitting by empty space.
     */
    private final String[] splitInput;

    /**
     * Constructs a new instance of AddCommand.
     *
     * @param splitInput the user input after splitting by empty space.
     */
    public AddCommand(String[] splitInput) {
        this.splitInput = splitInput;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            String taskType = splitInput[1].toLowerCase();
            String desc = getDescription(splitInput, taskType);

            switch (taskType) {
            case "todo":
                return handleAddTask(new Todo(desc), tasks, ui, storage, desc);
            case "deadline":
                return handleAddDeadline(tasks, ui, storage, splitInput, desc);
            case "event":
                return handleAddEvent(tasks, ui, storage, splitInput, desc);
            default:
                return ui.displayError("Hoshi doesn't understand, try a different input?");
            }
        } catch (IndexOutOfBoundsException e) {
            return ui.displayError("Hoshi wants you to try specifying the task!");
        } catch (HoshiException e) {
            return ui.displayError(e.getMessage());
        }
    }

    /**
     * Adds a normal task to the taskList
     *
     * @param task     String that represents general user input before add task details are required.
     * @param tasks    TaskList of 3 types of tasks that will be added to in this method.
     * @param storage  Storage that handles all input output of Hoshi.
     * @param ui       Ui responsible for displaying text to the user.
     * @param desc     Description of the task to be added.
     */
    private String handleAddTask(Task task, TaskList tasks, Ui ui, Storage storage, String desc) {
        tasks.add(task);
        CommandUtils.handleSave(tasks, storage, ui);
        return ui.displayTaskAdded(desc, task.getClass().getSimpleName());
    }

    /**
     * Adds a deadline to the taskList
     *
     * @param taskList   TaskList of 3 types of tasks that will be added to in this method.
     * @param ui         Ui responsible for displaying text to the user.
     * @param splitInput String that represents general user input before add task details are required.
     * @param desc       Description of the task to be added.
     */
    private String handleAddDeadline(TaskList taskList, Ui ui, Storage storage, String[] splitInput, String desc) {
        try {
            int splitInputLength = splitInput.length;
            // Parse datetime and create deadline to be added
            LocalDate dateTime = LocalDate.parse(splitInput[splitInputLength - 1]);
            Deadline deadline = new Deadline(desc, dateTime);

            // Add deadline to taskList
            return handleAddTask(deadline, taskList, ui, storage, desc);
        } catch (DateTimeParseException e) {
            return ui.displayError("Hoshi doesn't understand! Try YYYY-MM-DD format for the deadline.");
        }
    }

    /**
     * Adds an event to the taskList
     *
     * @param taskList   TaskList of 3 types of tasks that will be added to in this method.
     * @param ui         Ui responsible for displaying text to the user.
     * @param splitInput String that represents general user input before add task details are required.
     * @param desc       Description of the task to be added.
     */
    private String handleAddEvent(TaskList taskList, Ui ui, Storage storage, String[] splitInput, String desc) {
        try {
            int splitInputLength = splitInput.length;
            // Parse datetime and create event to be added
            LocalDate dateTimeStart = LocalDate.parse(splitInput[splitInputLength - 1]);
            LocalDate dateTimeEnd = LocalDate.parse(splitInput[splitInputLength - 2]);

            // Add event to taskList
            return handleAddTask(new Event(desc, dateTimeStart, dateTimeEnd), taskList, ui, storage, desc);
        } catch (DateTimeParseException e) {
            return ui.displayError("Hoshi doesn't understand! Try YYYY-MM-DD format for the event.");
        }
    }

    /**
     * Gets the description from the splitInput for use in handleAdd.
     *
     * @param splitInput list where each element represents a word in the split input.
     * @param taskType the string indicating what task to get the description for.
     */
    private String getDescription(String[] splitInput, String taskType) throws HoshiException {
        try {
            String desc = "";
            int splitInputLength = splitInput.length;

            switch (taskType) {
            case "todo":

                desc = processDescription(splitInput, splitInputLength);
                break;
            case "deadline":
                if (splitInputLength - 1 == DESCRIPTION_INDEX) {
                    throw new HoshiException("Hoshi doesn't understand! Try YYYY-MM-DD format for the deadline.");
                }
                // splitInput - 1 to ignore the last date field of Deadline
                desc = processDescription(splitInput, splitInputLength - 1);
                break;
            case "event":
                if (splitInputLength - 1 == DESCRIPTION_INDEX) {
                    throw new HoshiException("Hoshi doesn't understand! Try YYYY-MM-DD format for the event.");
                }
                // splitInput - 2 to ignore the last 2 date fields of Event
                desc = processDescription(splitInput, splitInputLength - 2);
                break;
            default:
                throw new HoshiException("Hoshi doesn't understand! Unknown task type.");
            }
            if (desc.isEmpty()) {
                throw new HoshiException("Hoshi doesn't understand! The task description is empty.");
            }
            return desc;
        } catch (IllegalArgumentException e) {
            throw new HoshiException("Hoshi doesn't understand! The task description is empty.");
        }
    }

    /**
     * Processes the description in the splitInput array by concatenating the relevant indices.
     *
     * @param splitInput list where each element represents a word in the split input.
     * @param endIndex   the index indicating where to end the description.
     */
    private String processDescription(String[] splitInput, int endIndex) {
        //Solution below adapted from https://www.geeksforgeeks.org/java-util-arrays-copyofrange-java/
        // Concatenate elements from description index to end index
        return String.join(" ", Arrays.copyOfRange(splitInput, DESCRIPTION_INDEX, endIndex));
    }

}
