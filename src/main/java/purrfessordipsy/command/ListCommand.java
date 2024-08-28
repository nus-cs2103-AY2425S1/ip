package purrfessordipsy.command;

import purrfessordipsy.exception.InvalidDateException;
import purrfessordipsy.task.Task;
import purrfessordipsy.tasklist.TaskList;
import purrfessordipsy.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ListCommand extends Command {


    public ListCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    public void execute() throws InvalidDateException {
        String[] parts = userInput.trim().split("\\s+");
        if (parts.length == 1) {
            // Case where input is 'list'
            ui.printListOfTasks(tasks.getTasks());
        } else if (parts.length == 2) {
            // Case where input is 'list <date>'
            try {
                LocalDate date = LocalDate.parse(parts[1]);
                ui.printListOfTasks(filterTasksByDate(date));
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        }
    }


    private ArrayList<Task> filterTasksByDate(LocalDate date) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            if (date.equals(t.getRelevantDate())) {
                res.add(t);
            }
        }
        return res;
    }
}
