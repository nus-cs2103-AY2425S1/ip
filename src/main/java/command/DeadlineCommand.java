package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import exception.ParserException;
import task.Converter;
import task.Deadline;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * Handles related issues of command deadline
 */
public class DeadlineCommand extends Command {
    private Task deadline;
    /**
     * Construtor for the deadline command
     * @param cmdline Command line from user to describe the command
     * @throws ParserException If invalid format for the command
     */
    public DeadlineCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);

        if (args.length == 1) {
            throw new ParserException("Missing argument description for deadline command");
        }

        String[] args2 = args[1].split(" /by ");
        String description = args2[0];
        LocalDateTime deadline;

        if (args2.length == 1) {
            throw new ParserException("Missing argument deadline for deadline command");
        }
        if (args2.length >= 3) {
            throw new ParserException("Too much arguments for deadline command");
        }
        try {
            deadline = Converter.inputToDateTime(args2[1]);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid format for argument deadline");
        }

        this.deadline = new Deadline(description, deadline);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.add(this.deadline);
        ui.println("A task is added");
        ui.println(this.deadline);
    }
}
