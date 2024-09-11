package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskList;
import colress.TaskType;
import colress.Ui;
import colress.task.Deadline;
import colress.task.Event;
import colress.task.Task;
import colress.task.ToDo;

/**
 * Represents the add command that add a task to the list of tasks.
 */
public final class AddCommand extends Command {
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
        super("Okay. I have added this task to your list:\n");
        hasStartTime = false;
    }

    @Override
    public String start(Ui ui, TaskList taskList) {
        return ui.promptTaskType();
    }

    public void initialise(TaskType input) {
        taskType = input;
    }
    public void initialise(String input) {
        description = input;
    }
    public void initialise(LocalDate input) {
        date = input;
    }

    /**
     * If the command already has an initialised start time, initialise end time instead of the start time
     */
    public void initialise(LocalTime input) {
        if (hasStartTime) {
            this.endTime = input;
        } else {
            startTime = input;
            hasStartTime = true;
        }
    }

    public void initialise(int... input) {
    }

    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Facilitates adding a task to the provided TaskList as not done, using the provided Ui object to receive input
     * from the user regarding what type of task to add and the various fields of the task to be added.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
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
}
