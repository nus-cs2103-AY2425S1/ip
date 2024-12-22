package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;

import colress.Parser;
import colress.TaskList;
import colress.TaskType;
import colress.Ui;
import colress.UiAdvanced;
import colress.UiBeginner;
import colress.exception.EmptyInputException;
import colress.exception.EndTimeException;
import colress.exception.InvalidCommandFormatException;
import colress.exception.UnknownTaskTypeException;
import colress.task.Deadline;
import colress.task.Event;
import colress.task.Task;
import colress.task.ToDo;

/**
 * Represents the add command that add a task to the list of tasks.
 */
public final class AddCommand extends Command {
    public static final String COMMAND_FORMAT = "add TASK TYPE, DESCRIPTION, [DATE], [START TIME], [END TIME]";
    public static final int EXPECTED_ARG_NUMBER_TODO = 2;
    public static final int EXPECTED_ARG_NUMBER_DEADLINE = 3;
    public static final int EXPECTED_ARG_NUMBER_EVENT = 5;
    private TaskType taskType;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean hasStartTime;

    /**
     * Constructs an AddCommand with the given successfulExecutionMessage. The AddCommand field has
     * no start time field to begin with by default
     */
    public AddCommand() {
        super("Splendid! I have added this task to your list:\n");
        hasStartTime = false;
    }

    /**
     * Constructs an AddCommand with the given successfulExecutionMessage and arguments. The AddCommand field has
     * no start time field to begin with by default
     */
    public AddCommand(String[] arguments) {
        super("Splendid! I have added this task to your list:\n", arguments);
        hasStartTime = false;
    }

    /**
     * Constructs an AddCommand with the given fields.
     */
    public AddCommand(String[] arguments, TaskType taskType, String description,
                      LocalDate date, LocalTime startTime, LocalTime endTime) {
        super("Splendid! I have added this task to your list:\n", arguments);
        this.taskType = taskType;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        hasStartTime = false;
    }

    @Override
    public String start(UiBeginner ui, TaskList taskList) {
        return ui.promptTaskType();
    }

    public void initialise(TaskType input) {
        taskType = input;
    }

    public void initialise(String input) {
        description = input;
    }

    public void initialise(int... input) {
    }

    public void initialise(LocalDate input) {
        date = input;
    }

    /**
     * Initialises start time and end time of an event.
     */
    public void initialise(LocalTime input) {
        if (hasStartTime) {
            this.endTime = input;
        } else {
            startTime = input;
            hasStartTime = true;
        }
    }

    public boolean isInvalidEndTime(LocalTime endTime) {
        return !endTime.isAfter(startTime);
    }

    /**
     * Facilitates adding a task to the provided TaskList as not done, using the provided Ui object to receive input
     * from the user regarding what type of task to add and the various fields of the task to be added.
     */
    @Override
    public String execute(UiBeginner ui, TaskList taskList) {
        Task task;
        switch (taskType) {
        case DEADLINE:
            task = new Deadline(description, date);
            break;
        case EVENT:
            task = new Event(description, date, startTime, endTime);
            break;
        default:
            task = new ToDo(description);
        }

        return ui.printConfirmationMessage(taskList,
                getSuccessfulExecutionMessage() + taskList.addTask(task));
    }

    @Override
    public String execute(UiAdvanced ui, TaskList taskList) throws InvalidCommandFormatException {
        String[] args = getArguments();
        Task task;
        try {
            checkMinimumNumberOfArgs(args);
            ui.parseTaskType(args[0]);
            ui.parseDescription(args[1]);
            switch (taskType) {
            case DEADLINE:
                checkNumberOfArgs(args, EXPECTED_ARG_NUMBER_DEADLINE, COMMAND_FORMAT);
                ui.parseDate(args[2]);
                task = new Deadline(description, date);
                break;
            case EVENT:
                checkNumberOfArgs(args, EXPECTED_ARG_NUMBER_EVENT, COMMAND_FORMAT);
                ui.parseDate(args[2]);
                ui.parseStartTime(args[3]);
                ui.parseEndTime(args[4]);
                task = new Event(description, date, startTime, endTime);
                break;
            default:
                assert taskType == TaskType.TODO;
                task = new ToDo(description);
            }
        } catch (UnknownTaskTypeException | EmptyInputException | EndTimeException e) {
            ui.setCommandType("error");
            return e.getMessage();
        } catch (DateTimeParseException e) {
            ui.setCommandType("error");
            return Ui.MESSAGE_NOT_A_VALID_DATE_TIME_ERROR;
        }
        return ui.printConfirmationMessage(taskList,
                getSuccessfulExecutionMessage() + taskList.addTask(task));
    }

    private void checkMinimumNumberOfArgs(String[] args)
            throws InvalidCommandFormatException {
        if (args.length < EXPECTED_ARG_NUMBER_TODO) {
            throw new InvalidCommandFormatException(String.format(MESSAGE_INVALID_FORMAT, COMMAND_FORMAT));
        }
    }

    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return Parser.COMMAND_ADD;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return Arrays.equals(getArguments(), otherAddCommand.getArguments())
                && Objects.equals(taskType, otherAddCommand.taskType)
                && Objects.equals(description, otherAddCommand.description)
                && Objects.equals(date, otherAddCommand.date)
                && Objects.equals(startTime, otherAddCommand.startTime)
                && Objects.equals(endTime, otherAddCommand.endTime);
    }
}
