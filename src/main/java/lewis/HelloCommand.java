package lewis;

public class HelloCommand extends Command{
    private HelloCommand() {

    }
    private static final HelloCommand HELLO_COMMAND = new HelloCommand();

    private final String HELLO_MESSAGE = """
                Hello there! I am Lewis, a simple chatbot and I'm here to help you.
                In future versions, I'll be able to handle task scheduling and other services to make your life easier.
                Isn't that swell?""";
    public static HelloCommand of() {
        return HELLO_COMMAND;
    }

    public static String getHelpDescription() {
        return "Greets the user, and directs them to the help directory";
    }

    public void execute() {
        Ui.printString(HELLO_MESSAGE);
    }
}
