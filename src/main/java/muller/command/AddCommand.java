package muller.command;

import java.time.LocalDate;

import muller.storage.Storage;
import muller.task.Task;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * Command to add a task (Todo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {
    private String[] commandInputs;
    private String taskType;

    /**
     * Constructs an AddCommand with the specified inputs and task type.
     *
     * @param commandInputs The command inputs.
     * @param taskType   The type of task to add (T, D, E).
     */
    public AddCommand(String[] commandInputs, String taskType) {
        this.commandInputs = commandInputs;
        this.taskType = taskType;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
        if (commandInputs.length < 2) {
            throw new MullerException(taskType.equals("T") ? "Todo what?"
                    : taskType.equals("D") ? "Deadline for what?" : "Event for what?");
        }
        String details = commandInputs[1].trim();
        Task task;

        switch (taskType) {
        case "T":
            task = new Task(details);
            task.setType("T");
            break;
        case "D":
            task = parseDeadline(details);
            break;
        case "E":
            task = parseEvent(details);
            break;
        default:
            throw new MullerException("Unknown task type!");
        }

        tasks.addTask(task);
        // Save the updated task list to the storage
        storage.saveTasks(tasks);
        return ui.showTaskAdded(task, tasks);
    }

    /**
     * Parses the details of a Deadline task.
     *
     * @param details The task details.
     * @return A Task object representing the Deadline.
     * @throws MullerException If the date format is invalid.
     */
    private Task parseDeadline(String details) throws MullerException {
        String[] detailParts = details.split("/by", 2);
        if (detailParts.length < 2) {
            throw new MullerException("Oops, you didn't specify the deadline!");
        }
        Task task = new Task(detailParts[0].trim());
        task.setType("D");
        LocalDate date = parseDate(detailParts[1].trim());
        task.setDate(date);
        return task;
    }

    /**
     * Parses the details of an Event task.
     *
     * @param details The task details.
     * @return A Task object representing the Event.
     * @throws MullerException If the date format is invalid.
     */
    private Task parseEvent(String details) throws MullerException {
        String[] detailParts = details.split("/from", 2);
        if (detailParts.length < 2) {
            throw new MullerException("Oops, you didn't specify the start date!");
        }
        String[] dateParts = detailParts[1].split("/to", 2);
        if (dateParts.length < 2) {
            throw new MullerException("You missed out either the start or end date!");
        }
        Task task = new Task(detailParts[0].trim());
        task.setType("E");
        LocalDate startDate = parseDate(dateParts[0].trim());
        LocalDate endDate = parseDate(dateParts[1].trim());
        task.setDateRange(startDate, endDate);
        return task;
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateStr The date string to parse.
     * @return A LocalDate object representing the parsed date.
     * @throws MullerException If the date format is invalid.
     */
    private LocalDate parseDate(String dateStr) throws MullerException {
        try {
            return LocalDate.parse(dateStr, Task.INPUT_DATE_FORMATTER);
        } catch (Exception e) {
            throw new MullerException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15).");
        }
    }
}

