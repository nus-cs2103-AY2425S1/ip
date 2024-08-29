package quack.command;

import java.time.format.DateTimeParseException;
import quack.TaskList;
import quack.Ui;
import quack.tasks.Task;
import quack.exception.InvalidTaskTypeException;
import quack.exception.InvalidDateTimeException;

/**
 * This class is responsible for handling addition of tasks to the task list,
 * based on what the user entered
 */
public class AddTaskCommand extends Command{
    
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a AddTaskCommand object.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param ui The ui object that handles user interface requests.
     */
    public AddTaskCommand(TaskList taskList, Ui ui) {
       this.taskList = taskList;
       this.ui = ui;
    }

    @Override
    public void execute(){

        try {
            String taskType = ui.requestTaskType();
            this.getTaskDetails(taskType.toUpperCase());
        } catch (InvalidTaskTypeException taskTypeError) {
            ui.printExceptionMessage(taskTypeError);
        }
    }

    /**
     * Retrieves the tasks details based on the task type the user input.
     * <p>
     * After retrieving the task details, the task will be created and added,
     * into the tasklist
     * @param taskType The type of tasks to be created. 
     */
    private void getTaskDetails(String taskType) {
        
        String taskDescription = ui.requestTaskDescription(taskType);
        String startDate = null;
        String endDate = null;

        switch (taskType) {
        case "EVENT":
            startDate = ui.requestStartDate(taskType);
            endDate = ui.requestEndDate(taskType);
            break;
        case "DEADLINE":
            startDate = ui.requestEndDate(taskType);
            break;
        }

        String[] information = {taskType, taskDescription, startDate, endDate};

        try {
            Task newTask = Task.createTask(information);
            taskList.addTask(newTask);
            ui.printUpdateSuccessfulMessage(newTask, "add", taskList);

        } catch (DateTimeParseException dateParseError) {
            ui.printExceptionMessage(new InvalidDateTimeException("Im sorry but the date input is invalid ensure that it is in this format: DD/MM/YYYY HH:MM:SS"));
        } catch (InvalidDateTimeException dateTimeError) {
           ui.printExceptionMessage(dateTimeError);
        } 
    }
}
