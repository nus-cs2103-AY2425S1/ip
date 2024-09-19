package blacknut.ui;
import java.util.ArrayList;
import blacknut.ui.BlacknutExceptions.InvalidCommandException;
import blacknut.ui.BlacknutExceptions.EmptyDescriptionException;
import blacknut.ui.BlacknutExceptions.InvalidTaskNumberException;
import blacknut.ui.BlacknutExceptions.IncorrectFormatException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException, EmptyDescriptionException, InvalidTaskNumberException, IncorrectFormatException;
    public boolean isExit() {
        return false; // By default, commands do not cause the program to exit
    }
}
