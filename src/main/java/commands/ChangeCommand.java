package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.EmptyDescriptionException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import windebot.History;
import windebot.Reminder;
import windebot.Ui;

/**
 * The ChangeCommand class represents a command to edit a task to the reminder list.
 * This command parses the input to extract the task description and date.
 * If the input is invalid or incomplete, an exception is thrown.
 */

public class ChangeCommand extends Command {
    /**
     * Executes the ChangeCommand command, edits the task in the schedule.
     *
     * @param input The user input string containing the task description and deadline.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @param history The History object used to save the data
     * @return true if the command was executed successfully.
     * @throws EmptyDescriptionException If the input is incomplete or incorrectly formatted.
     */

    public boolean execute(String input, Reminder reminder, Ui ui, History history)
            throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        assert(command.length == 2);
        int toChange = reminder.getSelected();
        Task task = reminder.getSchedule().get(toChange);
        if (command[0].equalsIgnoreCase("change")) {
            toChange = Integer.parseInt(command[1]) - 1;
            reminder.changeSelected(toChange);
            task = reminder.getSchedule().get(toChange);
            ui.print("Got it. You want to edit this task:");
            ui.print("    " + task.toString());
            ui.print("To change the task description, input: action (task description)");
            if (task.getClass() == Deadline.class) {
                ui.print("To change the deadline, input: cutoff (new deadline)");
            } else if (task.getClass() == Event.class) {
                ui.print("To change the start date, input: start (new start)");
                ui.print("To change the end date, input: end (new end)");
            }
        } else if (command[0].equalsIgnoreCase("action")) {
            task.changeAction(command[1]);
            ui.print("Got it. The task has been changed to:");
            ui.print("    " + task.toString());
            ui.print("Now you have " + reminder.size() + " tasks in the list.");
        } else if (command[0].equalsIgnoreCase("cutoff")
                && task.getClass() == Deadline.class) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime deadline = LocalDateTime.parse(command[1], formatter);
            task.changeDate(deadline);
            ui.print("Got it. The task has been changed to:");
            ui.print("    " + task.toString());
            ui.print("Now you have " + reminder.size() + " tasks in the list.");
            history.save(reminder.getSchedule());
        } else if (command[0].equalsIgnoreCase("start")
                && task.getClass() == Event.class) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime start = LocalDateTime.parse(command[1], formatter);
            task.changeStartDate(start);
            ui.print("Got it. The task has been changed to:");
            ui.print("    " + task.toString());
            ui.print("Now you have " + reminder.size() + " tasks in the list.");
            history.save(reminder.getSchedule());
        } else if (command[0].equalsIgnoreCase("end")
                && task.getClass() == Event.class) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime end = LocalDateTime.parse(command[1], formatter);
            task.changeEndDate(end);
            ui.print("Got it. The task has been changed to:");
            ui.print("    " + task.toString());
            ui.print("Now you have " + reminder.size() + " tasks in the list.");
            history.save(reminder.getSchedule());
        } else {
            throw new EmptyDescriptionException("PUT IN PARAMETERS");
        }
        return true;
    }

    /**
     * Gets the type of command: Add, ChangeMark or Delete
     */

    public String whatCommand() {
        return "";
    }
}
