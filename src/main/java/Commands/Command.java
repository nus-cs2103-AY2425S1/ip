package Commands;

import WindeBot.History;
import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;
import Exceptions.TooManyParametersException;

public abstract class Command {
    public abstract boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException;

    public void exit(History history, Reminder reminder, Ui ui) {
        ui.print("Bye. Hope to see you again soon!");
        history.save(reminder.getSchedule());
    }
}