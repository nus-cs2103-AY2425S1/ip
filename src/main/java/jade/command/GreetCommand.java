package jade.command;

/**
 * Represents a command that greets the user.
 */
public class GreetCommand extends Command {

    @Override
    public String runForGui() {
        return displayGreetMessage(FOR_GUI);
    }

    @Override
    public String run() {
        return displayGreetMessage(FOR_TEXT_UI);
    }

    private String displayGreetMessage(boolean forGui) {
        StringBuilder message = new StringBuilder();
        message.append("Hello! I'm Jade!\n");
        indentIfNotGui(forGui, message);
        message.append("What can I do for you?");

        return displayMessage(forGui, message.toString());
    }
}
