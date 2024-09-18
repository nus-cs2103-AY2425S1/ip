package rainy.commands;

import rainy.database.Parser;
import rainy.database.UI;
import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.rainyexceptions.InvalidEventParametersException;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import rainy.tasks.TaskTracker;

/**
 * Represents the set of all command operations. Contains abstract method to be implemented by subclasses.
 */
public abstract class Command {
    protected Parser ps;
    protected UI ui;

    /**
     * Constructs a new <code>Command</code> object.
     * All <code>Command</code> objects have a <Code>Parser</Code> object and a <code>UI</code> object.
     */
    public Command() {
        this.ps = new Parser();
        this.ui = new UI();
    }

    public abstract TaskTracker getResponse() throws InvalidIndexException, InvalidMarkAndUnmarkException,
            InvalidDeadlineParametersException, InvalidEventParametersException;
}
