package commands;

import exceptions.EmptyDescriptionException;
import exceptions.TooManyParametersException;
import tasks.Task;
import windebot.History;
import windebot.Reminder;
import windebot.Ui;

/**
 * The FindCommand class represents a command to find tasks containing a specific keyword.
 * This command searches through the list of tasks and displays any tasks that match the keyword.
 */

public class FindCommand extends Command {

    /**
     * Executes the FindCommand, searching for tasks that contain the specified keyword.
     *
     * @param input The user input string containing the keyword to search for.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @param history The History object used to save the data
     * @return true if the command was executed successfully.
     * @throws EmptyDescriptionException If no keyword is provided in the input.
     * @throws TooManyParametersException If too many parameters are provided in the input.
     */

    public boolean execute(String input, Reminder reminder, Ui ui, History history)
            throws EmptyDescriptionException {
        try {
            String[] command = input.split(" ", 2);
            if (command.length < 2) {
                throw new EmptyDescriptionException();
            }
            String search = command[1].trim();
            if (!(search.equals(""))) {
                ui.print("Here are the matching tasks in your list:");
                String keyWord = command[1];
                boolean isTaskFound = true;
                for (Task task : reminder.getSchedule()) {
                    if (task.getAction().contains(keyWord)) {
                        ui.print(task.toString());
                        isTaskFound = false;
                    }
                }
                if (isTaskFound) {
                    ui.print("No words matches your query stoopid");
                }
            } else {
                throw new EmptyDescriptionException();
            }
        } catch (EmptyDescriptionException e) {
            ui.emptyDescriptionMessage();
        }
        return true;
    }

    /**
     * Gets the type of command: Add, ChangeMark or Delete
     */

    public String whatCommand() {
        return "";
    }
}
