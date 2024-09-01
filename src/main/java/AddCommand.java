import java.util.ArrayList;

public abstract class AddCommand implements JBotCommand {
    @Override
    public void run(String input) {
        int count = TaskList.size();
        System.out.printf("Now you have %1$s tasks in the list.\n", count);
    }
}
