package jade.command;

import static jade.ui.Ui.INDENT;

/**
 * Represents a command that bids farewell to the user.
 */
public class ExitCommand extends Command {

    @Override
    public String run() {
        String message = INDENT + "Bye. Hope to see you again soon!";
        return displayMessage(message);
    }

    @Override
    public String runForGui() {
        return "Bye. Hope to see you again soon!";
    }
}
