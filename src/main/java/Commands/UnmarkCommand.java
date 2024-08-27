package Commands;

import Default.Ui;
import Exceptions.NedException;
import java.util.ArrayList;
import Tasks.Task;

public class UnmarkCommand implements Command {
    private final String REGEX = "^unmark.*";
    public UnmarkCommand() {}
    @Override
    public void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException {
        String[] words = userInput.split(" ");
        if (words.length != 2) {
            throw new NedException("Sorry m'lord, you must give me a list index with the mark command. No more, no " +
                    "less");
        }
        String possibleIndex = words[1];
        try {
            int index = Integer.parseInt(possibleIndex) - 1;
            Task selectedTask = listOfTasks.get(index);
            selectedTask.markAsNotDone();
            Ui.print("Oh no. One back up, more to go!");
            Ui.print(selectedTask.toString());
        } catch (NumberFormatException e) {
            throw new NedException("Sorry m'lord, your command must specify a valid number");
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, seems the item number you specified is not valid");
        }
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
