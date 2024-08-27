package Commands;

import Default.Ui;
import Exceptions.NedException;
import Tasks.Task;
import java.util.ArrayList;

public class DeleteCommand implements Command {
    private final String REGEX = "^delete.*";

    public DeleteCommand() {
    }

    @Override
    public void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException {
        String[] words = userInput.split(" ");
        try {
            if (words.length != 2) {
                throw new NedException("Sorry m'lord, you must give me a list index with the delete command. No more, no less");
            } else {
                String possibleIndex = words[1];
                int index = Integer.parseInt(possibleIndex) - 1;
                Task selectedTask = listOfTasks.get(index); //removes the index specified
                listOfTasks.remove(index);
                Ui.print("Noted m'lord. The following task has been removed:");
                Ui.print(Ui.INDENTATIONS + selectedTask);
                Ui.print(String.format("Now you've %d tasks in the list. Get to it then.", listOfTasks.size()));
            }
        } catch (NumberFormatException e) {
            throw new NedException("Sorry m'lord, your command must specify a valid number");
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, your command must specify an index within the bounds of the list " +
                    "size");
        }
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
