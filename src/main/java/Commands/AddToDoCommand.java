package Commands;

import Exceptions.NedException;
import Tasks.Task;
import Tasks.ToDo;
import Default.Ui;
import java.util.ArrayList;

public class AddToDoCommand implements Command{
    private final String REGEX = "^todo.*";

    @Override
    public void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException {
        String[] parsed_inputs = userInput.split("todo", 2);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a todo task with no description");
        }
        Task newTask = ToDo.createToDo(parsed_inputs[1].strip(), false);
        listOfTasks.add(newTask);
        Ui.print("Aye, I've added this task m'lord:");
        Ui.print(Ui.INDENTATIONS + newTask);
        Ui.print("Now you've " + listOfTasks.size() + " tasks left. Get to it then!");
    };
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
