package jade.command;

/**
 * Represents a command that bids farewell to the user.
 */
public class ExitCommand extends Command {

    @Override
    public String runForGui() {
        return displayExitMessage(FOR_GUI);
    }

    @Override
    public String run() {
        return displayExitMessage(FOR_TEXT_UI);
    }

    private String displayExitMessage(boolean forGui) {
        return displayMessage(forGui, "Bye. Hope to see you again soon!");
    }
}
