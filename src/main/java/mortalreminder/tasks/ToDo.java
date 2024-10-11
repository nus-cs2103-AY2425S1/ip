package mortalreminder.tasks;

// Class and constructor JavaDocs was autocompleted using ChatGPT with minor edits

import mortalreminder.errorhandling.MortalReminderException;

/**
 * Represents a simple task without any specific time constraints.
 * <p>
 * The {@code ToDo} class extends the {@code Task} class.
 * It represents tasks that need to be done, but without a specific deadline or time frame.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the specified description.
     *
     * @param description the description of the task.
     */
    public ToDo(String description) throws MortalReminderException {
        super(description);
        this.description = description;
        this.type = "T"; // short for todo
    }

    // the following constructor and javadoc was created using ChatGPT autocomplete with minor edits

    /**
     * Constructs a new {@code ToDo} task with the specified description and completion status.
     * This method is only called when loading from a file. This method also assumes the values
     * are always valid as it has already been checked during the adding to file process.
     *
     * @param description the description of the task.
     * @param isDone      whether the task is marked as done.
     */
    public ToDo(String description, boolean isDone) throws MortalReminderException {
        super(description);
        this.type = "T";
        this.description = description.trim();
        this.isDone = isDone;
    }

    /**
     * Method to get the description factor of the task.
     * The description is not always the same as the description value passed into the this.description variable
     * especially for the deadline and event classes. This method assumes that the variables have been properly
     * initialised in the constructor.
     *
     * @return string of the description after processing.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the class into a string to be added to the storage file.
     * Converts all the variables from their respective object types to a string format which can be stored
     * inside the storage file and later be recognised by the parser to convert back into a task type.
     *
     * @return string of object summary to be placed into the storage text file.
     */
    @Override
    public String convertToFileFormat() {
        return this.type + "|" + this.isDone + "|" + this.description;
    }

}
