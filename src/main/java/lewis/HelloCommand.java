package lewis;

/**
 * This class tells Lewis to greet the user
 */
public class HelloCommand extends Command{
    /** Private constructor for a HelloCommand */
    private HelloCommand() {
    }
    /** The singular instance of the HelloCommand */
    private static final HelloCommand HELLO_COMMAND = new HelloCommand();

    /** The greeting that Lewis will give */
    private final String HELLO_MESSAGE = """
                Hello there! I am Lewis, a simple chatbot and I'm here to help you.
                In future versions, I'll be able to handle task scheduling and other services to make your life easier.
                Isn't that swell?""";
    /** Factory method to produce a HelloCommand */
    public static HelloCommand of() {
        return HELLO_COMMAND;
    }

    /**
     * Returns a description for the user on the usage of this command
     * @return a string description
     */
    public static String getHelpDescription() {
        return "Greets the user, and directs them to the help directory";
    }

    /**
     * Execute this command, outputting the hello message to the standard output stream
     */
    public void execute() {
        Ui.printString(HELLO_MESSAGE);
    }
}
