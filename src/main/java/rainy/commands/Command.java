package rainy.commands;

import rainy.database.Parser;
import rainy.database.UI;
import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.rainyexceptions.InvalidEventParametersException;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import rainy.tasks.TaskTracker;

public abstract class Command {
    protected Parser ps;
    protected UI ui;

    public Command() {
        this.ps = new Parser();
        this.ui = new UI();
    }

    public abstract TaskTracker getResponse() throws InvalidIndexException, InvalidMarkAndUnmarkException, InvalidDeadlineParametersException, InvalidEventParametersException;
}