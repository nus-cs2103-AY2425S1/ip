package moimoi.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.InvalidCommandException;
import moimoi.exception.InvalidDateTimeException;
import moimoi.exception.MissingArgumentException;
import moimoi.exception.MoiMoiException;
import moimoi.task.Deadline;
import moimoi.task.Event;
import moimoi.task.Task;
import moimoi.task.Todo;

public class AddCommand extends Command {

    CommandEnum command;
    String arguments;

    public AddCommand(CommandEnum command, String arguments) {
        super(false);
        this.command = command;
        this.arguments = arguments;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws MoiMoiException {

        Task task;

        switch (this.command) {
        case TODO:
            task = new Todo(this.arguments);
            break;

        case DEADLINE:
            try {
                String[] descBy = this.arguments.split(" /by ", 2);
                String desc = descBy[0];
                String byString = descBy[1];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime by = LocalDateTime.parse(byString, formatter);

                task = new Deadline(desc, by);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingArgumentException();
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException("date-time");
            }
            break;

        case EVENT:
            try {
                String[] descFromTo = this.arguments.split(" /from ", 2);
                String desc = descFromTo[0];
                String[] fromTo = descFromTo[1].split(" /to ", 2);
                String fromString = fromTo[0];
                String toString = fromTo[1];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime from = LocalDateTime.parse(fromString, formatter);
                LocalDateTime to = LocalDateTime.parse(toString, formatter);

                task = new Event(desc, from, to);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingArgumentException();
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException("date-time");
            }
            break;

        default:
            throw new InvalidCommandException();
        }

        tasks.add(task);
        ui.showCompletionMessage("Aight! New task added: " + task.stringUI()
                + "\nWe have " + tasks.size() + " tasks in the bag~");

    }

}
