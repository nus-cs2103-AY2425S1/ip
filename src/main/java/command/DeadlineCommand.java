package command;

import exception.ParserException;
import task.Converter;
import task.Deadline;
import task.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import tasklist.TaskList;
import ui.Ui;

public class DeadlineCommand implements Command {
    private Task deadline;
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
            deadline = Converter.InputToDateTime(args2[1]);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid format for argument deadline");
        }
        this.deadline = new Deadline(description, deadline);
    }

    public void execute(TaskList tasks, Ui ui) {
        tasks.add(this.deadline);
        ui.println("A task is added");
        ui.println(this.deadline);
    }

    public boolean isExit() { return false; }
}
