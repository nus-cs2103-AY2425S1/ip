package muller.command;

import muller.storage.Storage;
import muller.task.Task;
import muller.task.TaskList;
import muller.ui.Ui;

import java.time.LocalDate;

public class OnCommand extends Command {
    private LocalDate date;

    public OnCommand(String[] inputs) throws MullerException {
        if (inputs.length < 2) {
            throw new MullerException("Specify a date (e.g., 'on 2019-10-15')!");
        }
        try {
            this.date = LocalDate.parse(inputs[1].trim(), Task.INPUT_DATE_FORMATTER);
        } catch (Exception e) {
            throw new MullerException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15).");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Tasks on " + date.format(Task.OUTPUT_DATE_FORMATTER) + ":");
        boolean found = false;
        for (Task task : tasks.getTasks()) {
            if (task.isOnDate(date)) {
                System.out.println("  " + task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("  No tasks found.");
        }
        ui.showLine();
    }
}
