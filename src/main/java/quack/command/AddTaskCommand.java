package quack.command;

import java.time.format.DateTimeParseException;

import quack.exception.InvalidDateTimeException;
import quack.exception.InvalidTaskTypeException;
import quack.tasks.Task;
import quack.util.TaskList;
import quack.util.Ui;

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
            this.getTaskTypeFromUser();
        } else if (this.nextPrompt == PromptTypes.DESCRIPTION) {
            this.getTaskDescriptionFromUser(input);
        } else if (this.nextPrompt == PromptTypes.STARTDATE) {
            this.getStartDateFromUser(input);
        } else if (this.nextPrompt == PromptTypes.ENDDATE) {
            this.getEndDateFromUser(input);
        } else if (this.nextPrompt == PromptTypes.DONE) {
            this.finaliseCommand(input);
            this.createTask();
        } else {
            throw new AssertionError();
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

    /**
     * Requests the type of task the user wants to add into their tasklist.
     * <p>
     * Progress the state of the command to retrieve the task description
     * on the next execution.
     */
    private void getTaskTypeFromUser() {
        ui.requestTaskType();
        this.nextPrompt = PromptTypes.DESCRIPTION;
    }

    /**
     * Requests for the description of the task.
     * <p>
     * Depending on the task type the next state will differ.
     * <p>
     * For todo tasks the next state will be the finished state.
     * <p>
     * For deadline tasks the next state will be to request for a end date.
     * <p>
     * For event tasks the next state will be to request for a start date.
     * @param input The task type the user inputed.
     * @throws AssertionError In the event next prompt is not assigned a value in the ennum
     */
    private void getTaskDescriptionFromUser(String input) {

        try {
            this.checkTaskType(input);
            this.ui.requestTaskDescription(this.taskType.toString());
        } catch (InvalidTaskTypeException taskTypeError) {
            // Since the input is invalid we need to mark the task as complete to
            // remove it from the command queue in the MainWindow class
            ui.printExceptionMessage(taskTypeError);
            this.completeCommand();
            return;
        }

        // Not all tasks require the same additional information
        // thus depends on the task type the next promot is decided
        switch (this.taskType) {
        case DEADLINE:
            this.nextPrompt = PromptTypes.ENDDATE;
            break;
        case EVENT:
            this.nextPrompt = PromptTypes.STARTDATE;
            break;
        case TODO:
            this.nextPrompt = PromptTypes.DONE;
            break;
        default:
            throw new AssertionError();
        }
    }

    /**
     * Requests the start date for the task.
     * <p>
     * Progress the state of the command to retrieve the end date
     * on the next execution.
     * @param input The description of the task.
     */
    private void getStartDateFromUser(String input) {

        this.taskDescription = input;
        ui.requestStartDate(this.taskType.toString());
        this.nextPrompt = PromptTypes.ENDDATE;
    }

    /**
     * Requests the end date for the task.
     * <p>
     * Progress the state of the command to be in a finished state to create and add the task.
     * <p>
     * For deadline task the input will be a description.
     * <p>
     * For event task the input will be a start date.
     * @param input The input to the next field.
     */
    private void getEndDateFromUser(String input) {

        // We need to know if the task is a event or a deadline task
        // to correctly assign the input to the correct field
        if (this.taskType == TaskTypes.DEADLINE) {
            this.taskDescription = input;
            System.out.println(input);
        } else if (this.taskType == TaskTypes.EVENT) {

            this.startDate = input;
        }

        ui.requestEndDate(this.taskType.toString());
        this.nextPrompt = PromptTypes.DONE;
    }

    /**
     * Saves the final input into the correct field.
     * <p>
     * For todo task the input will be a description.
     * <p>
     * For deadline or event the input will be a end date.
     * <p>
     * @param input The input to the next field.
     */
    private void finaliseCommand(String input) {

        if (this.taskType == TaskTypes.TODO) {
            this.taskDescription = input;
        } else {
            this.endDate = input;
        }
    }

    /**
     * Retrieves the tasks that have been gathered and creates a task object.
     * <p>
     * After retrieving the task details, the task will be created and added,
     * into the tasklist.
     * @throws AssertionError In the event next prompt is not assigned a value in the ennum
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
                throw new AssertionError();
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
        } catch (InvalidTaskTypeException taskTypeError) {
            ui.printExceptionMessage(taskTypeError);
        } finally {
            this.completeCommand();
        }
    }
}
