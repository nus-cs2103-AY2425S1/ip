package Commands;

import Tasks.Task;
import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;
import Exceptions.TooManyParametersException;

public class FindCommand extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            ui.print("Here are the matching tasks in your list:");
            String keyWord = command[1];
            boolean taskNotFound = true;
            for (Task task : reminder.getSchedule()) {
                if (task.getAction().contains(keyWord)) {
                    ui.print(task.toString());
                    taskNotFound = false;
                }
            }
            if (taskNotFound) {
                ui.print("No words matches your query stoopid");
            }
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M MARKING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}
