package lewis;

/**
 * This command signals for Lewis to repeat the user input back to them.
 */
public class EchoCommand extends Command {

    /** The string to be echoed back to the user */
    private final String echoString;

    /**
     * Public constructor for this class
     * @param input a string input propagated from the command line
     * @throws LewisException if the user tries to echo nothing
     */
    public EchoCommand(String input) throws LewisException {
        String[] tokens = input.split("echo", 2);
        this.echoString = tokens[1].trim();
        if (this.echoString.isEmpty()) {
            throw new LewisException("Hey, you have to give me something to echo!");
        }
    }

    /**
     * Returns a description for the user on the usage of this command
     * @return a string description
     */
    public static String getHelpDescription() {
        return "Echoes the line input back to the user.\nUsage: echo <string>";
    }

    /**
     * Echoes the user input back to the user terminal, excluding the command name echo.
     */
    @Override
    public void execute() {
        Ui.printString(this.echoString);
    }
}
