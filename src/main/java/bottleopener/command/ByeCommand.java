package bottleopener.command;

import bottleopener.ui.Ui;

/**
 * The {@code ByeCommand} class handles the "bye" command in the BottleOpener chatbot.
 * It is responsible for signaling the termination of the session and displaying a goodbye message.
 */
public class ByeCommand implements Command {

    /**
     * Constructs a {@code ByeCommand} object.
     * This class does not require any specific parameters for instantiation.
     */
    public ByeCommand() {
    }

    /**
     * Executes the "bye" command by displaying a goodbye message to the user.
     *
     * @return A string containing the goodbye message.
     */
    @Override
    public String runCommand() {
        return Ui.showGoodbye();
    }
}
