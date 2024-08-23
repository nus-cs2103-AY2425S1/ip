/**
 * The ToDo class represents a task that only has a description and does not have a specific date or time associated with it.
 * This class extends the IndividualTask class.
 */
public class ToDo extends IndividualTask {

    /**
     * Constructs a ToDo task with the specified task description.
     *
     * @param taskDescription The description of the ToDo task.
     */
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns a string that represents the ToDo task in a format suitable for saving to a file.
     * The format is: "T | [SaveIcon] | [TaskDescription]".
     *
     * @return The formatted string representing the ToDo task for file storage.
     */
    @Override
    public String saveToFileFormat() {
        return "T | " + this.getSaveIcon() + " | " + this.getTaskDescription();
    }

    /**
     * Returns a string representation of the ToDo task.
     * The format is: "[T][ ] [TaskDescription]" or "[T][X] [TaskDescription]" depending on whether the task is marked as done.
     *
     * @return The string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
