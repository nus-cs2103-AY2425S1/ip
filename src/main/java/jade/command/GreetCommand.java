package jade.command;

import static jade.ui.Ui.INDENT;

/**
 * Represents a command that greets the user.
 */
public class GreetCommand extends Command {

    @Override
    public String run() {
        String message = INDENT + "Hello! I'm Jade!\n"
                + INDENT + "What can I do for you?";
        return displayMessage(message);
    }

    @Override
    public String runForGui() {
        return "Hello! I'm Jade!\nWhat can I do for you?";
    }
}
