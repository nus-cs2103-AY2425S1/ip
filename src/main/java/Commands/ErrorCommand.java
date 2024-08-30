package Commands;

import WindeBot.Reminder;
import WindeBot.Ui;

public class ErrorCommand extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) {
        ui.print("You inputted a wrong input stoopid");
        return false;
    }
}
