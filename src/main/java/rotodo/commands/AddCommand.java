package rotodo.commands;

import rotodo.exception.IncompleteInputException;
import rotodo.exception.InvalidInputException;
import rotodo.processes.Storage;
import rotodo.processes.Ui;
import rotodo.tasklist.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command{
    public enum TaskType {
        TODO, DEADLINE, EVENT
    };

    private TaskType type;
    private String value;
    private LocalDateTime from;
    private LocalDateTime byto;
    private boolean status;

    public AddCommand(TaskType type, String ...value) throws InvalidInputException {
        this.type = type;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            switch (type) {
            case TODO:
                this.value = value[0];
                break;

            case DEADLINE:
                if (value.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n"
                        + "  RoTodo needs a task description and deadline");
                }
                this.value = value[0];
                this.byto = LocalDateTime.parse(value[1], formatter);
                break;

            case EVENT:
                if (value.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n"
                        + "  RoTodo needs a task description, from and to date/time");
                }
                this.value = value[0];
                LocalDateTime t1 = LocalDateTime.parse(value[1], formatter);
                LocalDateTime t2 = LocalDateTime.parse(value[2], formatter);
                if (t1.isBefore(t2)) {
                    this.from = t1;
                    this.byto = t2;
                } else {
                    this.from = t2;
                    this.byto = t1;
                }
                break;
            }
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(
                        "  Whaaaatt? RoTodo has no idea what date that is\n"
                        + "RoTodo needs valid date/time in the form:\n"
                        + "  dd/MM/yyyy HHmm");
        }
        
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage st) {
        String msg = "";
        tl.setNextStatus(status);
        switch (this.type) {
        case TODO:
            msg = tl.addTask(value);
            break;
        
        case DEADLINE:
        msg = tl.addTask(value, byto);
            break;

        case EVENT:
            msg = tl.addTask(value, from, byto);
            break;
        }
        if (ui != null) {
            ui.addMessage(msg);
        }
    }
}
