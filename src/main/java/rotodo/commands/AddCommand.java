package rotodo.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import rotodo.exception.IncompleteInputException;
import rotodo.exception.InvalidInputException;
import rotodo.processes.Gui;
import rotodo.processes.Storage;
import rotodo.tasklist.TaskList;

/**
 * The AddCommand class encapsulates the specific
 * type of Command that executes an add action. The
 * type of task added depends on the initialised state
 * of the command.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class AddCommand extends Command {
    /** Enum of TaskType */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    };

    private TaskType type;
    private String value;
    private LocalDateTime from;
    private LocalDateTime byOrTo;
    private boolean isDone;

    /**
     * Initialise AddCommand to be executed. Accepts
     * a TaskType and performs rudimentary checks on
     * validity of values provided.
     *
     * @param type TaskType to be added
     * @param value values (including datetime) of task
     * @throws InvalidInputException
     */
    public AddCommand(TaskType type, String ...value) throws InvalidInputException {
        this.type = type;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            switch (type) {
            case TODO:
                this.value = value[0];
                break;

            case DEADLINE:
                if (value.length < 2) {
                    throw new IncompleteInputException(
                            "RoTodo can't read your mind, otherwise "
                            + "RoTodo's creator would be rich!\n"
                            + "  RoTodo needs a task description and deadline");
                }
                this.value = value[0];
                this.byOrTo = LocalDateTime.parse(value[1], formatter);
                break;

            case EVENT:
                if (value.length < 3) {
                    throw new IncompleteInputException(
                            "RoTodo can't read your mind, otherwise "
                            + "RoTodo's creator would be rich!\n"
                            + "  RoTodo needs a task description, from and to date/time");
                }
                this.value = value[0];
                LocalDateTime t1 = LocalDateTime.parse(value[1], formatter);
                LocalDateTime t2 = LocalDateTime.parse(value[2], formatter);
                if (t1.isBefore(t2)) {
                    this.from = t1;
                    this.byOrTo = t2;
                } else {
                    this.from = t2;
                    this.byOrTo = t1;
                }
                break;

            default:
                break;
            }
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(
                    "Whaaaatt? RoTodo has no idea what date that is\n"
                    + "RoTodo needs valid date/time in the form:\n"
                    + "  dd/MM/yyyy HHmm");
        }
    }

    /** Set Status of Task to be added */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    @Override
    public void execute(TaskList tl, Gui ui, Storage st) {
        assert tl != null;
        String msg = "";
        tl.setNextStatus(isDone);
        switch (this.type) {
        case TODO:
            msg = tl.addTask(value);
            break;

        case DEADLINE:
            msg = tl.addTask(value, byOrTo);
            break;

        case EVENT:
            msg = tl.addTask(value, from, byOrTo);
            break;

        default:
            break;
        }
        if (ui != null) {
            ui.addMessage(msg);
        }
    }
}
