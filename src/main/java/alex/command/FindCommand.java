package alex.command;

import java.util.ArrayList;
import java.util.Scanner;

import alex.Storage;
import alex.Ui;
import alex.task.TaskList;

/**
 * Represents a command to find tasks based on a search keyword.
 */
public class FindCommand extends Command {
    private Scanner lineScanner;

    /**
     * Constructs a FindCommand instance.
     *
     * @param lineScanner Scanner object used to read user input.
     */
    public FindCommand(Scanner lineScanner) {
        this.lineScanner = lineScanner;
    }

    /**
     * {@inheritDoc}
     *
     * Finds tasks that contain the search keyword and returns the result.
     *
     * @param tasks TaskList that holds the list of Tasks.
     * @param ui Ui object that displays messages to the user based on the action taken by the chatbot.
     * @param storage Storage object that saves changes to the file.
     * @return A string representing the search results.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> arrOfStr = new ArrayList<>();
        while (this.lineScanner.hasNext()) {
            arrOfStr.add(this.lineScanner.next());
        }

        return tasks.findWord(String.join(" ", arrOfStr), ui);
    }

    @Override
    public String getCommandType() {
        return "FindCommand";
    }
}

