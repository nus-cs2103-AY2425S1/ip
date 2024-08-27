package Commands;

import Tasks.Task;
import Default.Ui;
import java.util.ArrayList;

public class ListCommand implements Command {
    private final String REGEX = "list";

    @Override
    public void execute(String userInput, ArrayList<Task> listOfTasks) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            String task = Ui.INDENTATIONS + String.format("%d.%s \n", i + 1, listOfTasks.get(i));
            Ui.print(task);
        };
    }
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
