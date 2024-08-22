import java.util.ArrayList;

public class ListCommand implements JBotCommand {

    private static final ListCommand instance = new ListCommand();
    private ListCommand() {
    };
    public static ListCommand getInstance() {
        return ListCommand.instance;
    }
    @Override
    public void run(String input, ArrayList<Task> taskList) {
        int counter = 1;

        System.out.println("Here are the tasks in your list:");

        for (Task task : taskList) {
            System.out.printf(
                    "%1$s. %2$s%n",
                    counter,
                    task
            );
            counter++;
        }
    }
}
