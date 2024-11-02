package botty.tasks;

import botty.commands.ParsedInput;

/**
 * Holds the data that defines a {@code Todo}
 */
public class TodoData extends TaskData {
    /**
     * Constructor for a {@code TodoData}
     * @param parsedInput the data that defines the {@code Todo} obtained through user input
     */
    public TodoData(ParsedInput parsedInput) {
        super(parsedInput);
    }

    /**
     * Constructor for a {@code TodoData}
     * @param description the description of the {@code Todo}
     */
    public TodoData(String description) {
        super(description);
    }
}
