package Commands;

import Tasks.Todos;
import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;

public class AddTodo extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            Todos todoTask = new Todos(command[1]);
            reminder.addTodo(todoTask);
            ui.print("Got it. I've added this task:");
            ui.print("    " + todoTask.toString());
            ui.print("Now you have " + reminder.size() + " tasks in the list.");
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M TODO-ING!");
        }
        return true;
    }
}
