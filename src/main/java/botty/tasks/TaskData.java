package botty.tasks;

import botty.commands.ParsedInput;

/**
 * Holds the data that defines a {@code Task}
 */
public class TaskData {
    // The description of the task
    private String description;

    /**
     * Constructor for a {@code TaskData}
     * @param parsedInput the data of the {@code Task} obtained from user input
     */
    public TaskData(ParsedInput parsedInput) {
        this.description = parsedInput.getArgumentOrNull("desc");
    }

    /**
     * Constructor for a {@code TaskData}
     * @param description the description of the {@code Task}
     */
    public TaskData(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the {@code Task}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if the description is not null and not empty
     */
    public boolean hasDescription() {
        return description != null && !description.isEmpty();
    }
}
