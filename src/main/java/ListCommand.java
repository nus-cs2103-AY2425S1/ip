import java.util.ArrayList;

public class ListCommand implements JBotCommand {

    private static final ListCommand instance = new ListCommand();
    private ListCommand() {
    };
    public static ListCommand getInstance() {
        return ListCommand.instance;
    }
    @Override
    public void run(String input) {

        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < TaskList.size(); i++) {
            System.out.printf(
                    "%1$s. %2$s%n",
                    i + 1,
                    TaskList.get(i)
            );
        }
    }
}
