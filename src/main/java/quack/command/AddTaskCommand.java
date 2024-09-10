package quack.command;

import java.time.format.DateTimeParseException;

import quack.TaskList;
import quack.Ui;
import quack.exception.InvalidDateTimeException;
import quack.exception.InvalidTaskTypeException;
import quack.tasks.Task;


/**
 * This class is responsible for handling addition of tasks to the task list,
 * based on what the user entered.
 */
public class AddTaskCommand extends Command {

    /** List to store all tasks by Quack */
    private TaskList taskList;
    /** List to store all tasks by Quack */
    private Ui ui;
    /** Type of task*/
    private TaskTypes taskType;
    /** Description of the task*/
    private String taskDescription;
    /** Start Date of the task*/
    private String startDate;
    /** End Date of the task*/
    private String endDate;
    /** Store next prompt */
    private PromptTypes nextPrompt;
    /** Store the types of prompts */
    private enum PromptTypes {
        TASKTYPE,
        DESCRIPTION,
        STARTDATE,
        ENDDATE,
        DONE
    }
    /** A list of all possible task types */
    private enum TaskTypes {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Creates a AddTaskCommand object.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param ui The ui object that handles user interface requests.
     */
    public AddTaskCommand(TaskList taskList, Ui ui) {
        super();
        this.taskList = taskList;
        this.ui = ui;
        this.nextPrompt = PromptTypes.TASKTYPE;
    }

    @Override
    public void prompt() {
        this.execute("");
    }

    @Override
    public void execute(String input) {

        // Ensures that the input is not null as if it is null then Quack is not getting the input from the user
        assert(input != null);

        if (this.nextPrompt == PromptTypes.TASKTYPE) {
            ui.requestTaskType();
            this.nextPrompt = PromptTypes.DESCRIPTION;
        } else if (this.nextPrompt == PromptTypes.DESCRIPTION) {
            try {
                this.checkTaskType(input);
                this.ui.requestTaskDescription(this.taskType.toString());
            } catch (InvalidTaskTypeException taskTypeError) {
                ui.printExceptionMessage(taskTypeError);
                this.completeCommand();
                return;
            }

            switch (this.taskType) {
            case DEADLINE:
                this.nextPrompt = PromptTypes.ENDDATE;
                break;
            case EVENT:
                this.nextPrompt = PromptTypes.STARTDATE;
                break;
            default:
                this.nextPrompt = PromptTypes.DONE;
                break;
            }

        } else if (this.nextPrompt == PromptTypes.STARTDATE) {

            this.taskDescription = input;
            ui.requestStartDate(this.taskType.toString());
            this.nextPrompt = PromptTypes.ENDDATE;

        } else if (this.nextPrompt == PromptTypes.ENDDATE) {

            if (this.taskType == TaskTypes.DEADLINE) {
                this.taskDescription = input;
                System.out.println(input);
            } else if (this.taskType == TaskTypes.EVENT) {

                this.startDate = input;
            }

            ui.requestEndDate(this.taskType.toString());
            this.nextPrompt = PromptTypes.DONE;
        } else {

            if (this.taskType == TaskTypes.TODO) {

                this.taskDescription = input;
            } else {
                this.endDate = input;
            }
            createTask();
        }
    }

    /**
     * Retrieves the tasks that have been gathered and creates a task object.
     * <p>
     * After retrieving the task details, the task will be created and added,
     * into the tasklist.
     */
    private void createTask() {

        try {
            Task newTask = null;
            switch (taskType) {
            case TODO:
                newTask = Task.createTask(taskType.toString(), taskDescription);
                break;
            case DEADLINE:
                newTask = Task.createTask(taskType.toString(), taskDescription, endDate);
                break;
            case EVENT:
                newTask = Task.createTask(taskType.toString(), taskDescription, startDate, endDate);
                break;
            default:
                break;
            }

            if (newTask != null) {
                taskList.addTask(newTask);
                ui.printUpdateSuccessfulMessage(newTask, "add", taskList);
            }
        } catch (DateTimeParseException dateParseError) {
            ui.printExceptionMessage(new InvalidDateTimeException("Im sorry but the date input is invalid, "
                + "ensure that it is in this format: DD/MM/YYYY HH:MM:SS"));
        } catch (InvalidDateTimeException dateTimeError) {
            ui.printExceptionMessage(dateTimeError);
        } finally {
            this.completeCommand();
        }
    }

    /**
     * Checks if the task type given by the user is a valid one.
     * @param taskType The type of tasks to be created.
     * @throws InvalidTaskTypeException If the user inputs a invalid task type.
     */
    private void checkTaskType(String taskType) throws InvalidTaskTypeException {

        String upperCasedTaskType = taskType.toUpperCase();
        for (TaskTypes tasktypes : TaskTypes.values()) {
            if (tasktypes.name().equals(upperCasedTaskType)) {
                this.taskType = tasktypes;
                return;
            }
        }
        throw new InvalidTaskTypeException(taskType);
    }
}
