package Commands;

import Tasks.Task;
import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;
import Exceptions.TooManyParametersException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskDateCommand extends Command {
    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(command[1], formatter);
            ui.print("These are the tasks you have for " + date.toString());
            ArrayList<Task> taskList = reminder.getTasksOnDate(date);
            if (taskList != null) {
                for (Task task : taskList) {
                    ui.print(task.toString());
                }
            } else {
                ui.print("Hurray! No tasks on: " + date.toString());
            }
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW THE DATE!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}