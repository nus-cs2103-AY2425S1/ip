package Commands;

import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;
import Exceptions.TooManyParametersException;

public class AddMark extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            ui.print("Nice! I've marked this task as done:");
            reminder.mark(Integer.parseInt(command[1]) - 1);
            ui.print("    " + reminder.getTask(Integer.parseInt(command[1]) - 1).toString());
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M MARKING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}
