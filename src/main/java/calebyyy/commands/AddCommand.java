package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;
import calebyyy.exceptions.CalebyyyException;
import calebyyy.exceptions.InvalidArgumentException;
import calebyyy.exceptions.InvalidDateException;
import calebyyy.tasks.Deadline;
import calebyyy.tasks.Event;
import calebyyy.tasks.Task;
import calebyyy.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand.
     *
     * @param calebyyy The main Calebyyy object.
     * @param ui The Ui object responsible for user interaction.
     * @param taskList The TaskList object that stores the task list.
     */
    public AddCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    private Boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param input The user input.
     * @throws CalebyyyException If the user input is invalid.
     */
    @Override
    public void execute(String input) throws CalebyyyException {
        String[] parts = input.split(" ", 2);

        if (parts.length < 2 || parts[1].isBlank()) {
            throw new InvalidArgumentException();
        }
        String commandType = parts[0];
        String taskDetails = parts[1];
        
        Task task = null;
        if (commandType.equals("todo")) {
            task = new Todo(taskDetails);
        } else if (commandType.equals("deadline")) {
            String[] details = taskDetails.split(" /by ");
            if (details.length < 2 || !isValidDate(details[1])) {
                throw new InvalidDateException();
            }
            task = new Deadline(details[0], details[1]);
        } else if (commandType.equals("event")) {
            String[] details = taskDetails.split(" /from | /to ");
            if (details.length < 3 || !isValidDate(details[1]) || !isValidDate(details[2])) {
                throw new InvalidDateException();
            }
            task = new Event(details[0], details[1], details[2]);
        } else {
            throw new IllegalArgumentException("Unknown task type");
        }
        
        if (task != null) {
            try {
                taskList.addTask(task);
                ui.addTaskMessage(task, taskList.getTaskCount());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

