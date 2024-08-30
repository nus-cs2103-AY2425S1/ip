package Commands;

import WindeBot.Reminder;
import WindeBot.Ui;

public class ListCommand extends Command {
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
