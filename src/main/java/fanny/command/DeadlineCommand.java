package fanny.command;

import fanny.ui.Ui;
import fanny.task.TaskList;
import fanny.task.Task;
import fanny.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String description;

    public DeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        try {
            String[] cmdDeadline = description.split("/by ", 2);
            LocalDateTime time = LocalDateTime.parse(cmdDeadline[1], super.formatter);
            String description = cmdDeadline[0];
            Task deadline = new Deadline(description, time);
            list.add(deadline);
            ui.showMessage("Fanny:\nGot it. I've added this task:");
            ui.showMessage(deadline.toString());
            ui.showMessage("Now you have " + list.getLength() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Task description and deadline cannot be empty");
        } catch (DateTimeParseException e) {
            ui.showMessage("Please enter a valid date and time: YYYY-MM-DD HH:MM:SS");
        } finally {
            ui.showHorizontalLine();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
