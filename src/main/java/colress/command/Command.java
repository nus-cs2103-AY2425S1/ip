package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskList;
import colress.TaskType;
import colress.Ui;

/**
 * Encapsulates the behaviour of a command.
 */
public abstract class Command {
    private final String successfulExecutionMessage;

    public Command(String successfulExecutionMessage) {
        this.successfulExecutionMessage = successfulExecutionMessage;
    }

    public String getSuccessfulExecutionMessage() {
        return successfulExecutionMessage;
    }

    public abstract String start(Ui ui, TaskList taskList);
    public abstract void initialise(String input);
    public abstract void initialise(TaskType input);
    public abstract void initialise(int... input);
    public abstract void initialise(LocalDate input);
    public abstract void initialise(LocalTime input);

    public abstract String execute(Ui ui, TaskList taskList);
}
