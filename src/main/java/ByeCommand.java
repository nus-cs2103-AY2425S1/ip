import java.util.ArrayList;

public class ByeCommand implements JBotCommand {

    private static final ByeCommand instance = new ByeCommand();
    private ByeCommand() {
    };
    public static ByeCommand getInstance() {
        return ByeCommand.instance;
    }
    @Override
    public void run(String input, ArrayList<Task> taskList) {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
