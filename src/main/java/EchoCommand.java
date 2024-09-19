public class EchoCommand extends Command{

    private final String echoString;

    public EchoCommand(String input) throws LewisException {
        String[] tokens = input.split("echo", 2);
        this.echoString = tokens[1].trim();
        if (this.echoString.isEmpty()) {
            throw new LewisException("Hey, you have to give me something to echo!");
        }
    }

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
