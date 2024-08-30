package Commands;

import Tasks.Task;
import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;
import Exceptions.TooManyParametersException;

public class RemoveCommand extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            Task deleted = reminder.remove(Integer.parseInt(command[1]) - 1);
            ui.print("Noted. I've removed this task:");
            ui.print("    " + deleted.toString());
            ui.print("Now you have " + reminder.size() + " tasks in the list.");
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M DELETING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}

