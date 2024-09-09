package quack.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import quack.exception.InvalidDateTimeException;

/**
 * This is an abstract class to define a task object.
 */
public abstract class Task {

    /** Date time format for printing LocalDateTime objects */
    protected static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    /** Description of the task */
    protected String description;
    /** Determine if the task is marked or not */
    protected boolean isChecked;

    /**
     * Creates a Task object with the given description.
     */
    Task(String description) {
        this.description = description;
        isChecked = false;
    }

    /**
     * Creates a task object based on its task type.
     * <p>
     * This is a factory method used to create a task based
     * on its corrosponding given task type.
     * @param taskDetails A list of task information used to create the task object.
     * @return A task object of its given task type.
     * @throws InvalidDateTimeException If the date time string is invalid.
     */
    public static Task createTask(String ... taskDetails) throws InvalidDateTimeException {

        Task task = null;

        LocalDateTime startDate;
        LocalDateTime endDate;

        switch (taskDetails[0]) {
        case "TODO":
            task = new ToDoTask(taskDetails[1]);
            break;

        case "DEADLINE":
            startDate = LocalDateTime.parse(taskDetails[2], Task.DATE_FORMAT);

            task = new DeadlineTask(taskDetails[1], startDate);
            break;

        case "EVENT":
            startDate = LocalDateTime.parse(taskDetails[2], Task.DATE_FORMAT);
            endDate = LocalDateTime.parse(taskDetails[3], Task.DATE_FORMAT);

            if (endDate.isBefore(startDate)) {
                throw new InvalidDateTimeException("The end date before start date? "
                    + "Please enter a start date before the end date!");
            }

            task = new EventTask(taskDetails[1], startDate, endDate);
            break;

        default:
            break;
        }

        return task;
    }

    /**
     * Retrieves the task description of the specfic task.
     * @return A string representation of the task description.
     */
    public String getDescription() {

        return this.description;
    }

    /**
     * Retrieves status of the task weather is it marked or not.
     * @return The boolean representation of the task status.
     */
    protected boolean getIsChecked() {

        return this.isChecked;
    }

    /**
     * Updates the task status to be marked.
     * <p>
     * The function checks if the task is already marked.
     */
    public boolean mark() {

        // Check if the task is already marked or not
        if (!isChecked) {
            this.isChecked = true;
            return true;
        }

        return false;
    }

    /**
     * Updates the task to be unmarked.
     * <p>
     * The function checks if the task is already unmarked.
     */
    public boolean unmark() {

        // Check if the task is already unmarked
        if (isChecked) {
            this.isChecked = false;
            return true;
        }

        return false;
    }

    /**
     * Converts a task into a csv format.
     * <p>
     * The task object will be converted into a string format where its information
     * is seperated by a comma.
     *
     * @return A string representation of the task to be saved into a csv file.
     */
    public String toCsvFormat() {

        return this.description;
    }

    @Override
    public String toString() {
        if (isChecked) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
