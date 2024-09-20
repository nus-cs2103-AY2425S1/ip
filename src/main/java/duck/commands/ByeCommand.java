package duck.commands;

public class ByeCommand extends Command {
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    @Override
    public String executeCommand() {
        return GOODBYE;
    }
}
