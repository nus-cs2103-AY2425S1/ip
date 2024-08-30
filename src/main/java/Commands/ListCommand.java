package Commands;

import WindeBot.Reminder;
import WindeBot.Ui;

/**
 * The ListCommand class represents a command to list all tasks in the reminder list.
 * This command displays all the tasks that are currently in the list.
 */

public class ListCommand extends Command {

    /**
     * Executes the ListCommand, displaying all tasks in the reminder list.
     *
     * @param input The user input string (not used in this command).
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @return true if the command was executed successfully.
     */

    public boolean execute(String input, Reminder reminder, Ui ui) {
        ui.print("Here are the tasks in your list:");
        if (reminder.size() == 0) {
            ui.print("Hurray you got nothing to do!");
        } else {
            for (int i = 1; i <= reminder.size(); i++) {
                ui.print(i + "." + " " + reminder.getTask(i - 1).toString());
            }
        }
        return true;
    }
}
