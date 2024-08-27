import java.time.format.DateTimeParseException;

/**
 * This class is responsible for handling addition of tasks to the task list,
 * based on what the user entered
 */
public class AddTaskCommand extends Command{
    

    /**
     * Creates a AddTaskCommand object.
     */
    public AddTaskCommand() {
       
    }

    @Override
    public void execute(Quack quack, TaskList taskList, Storage storage, Ui ui){

        try {
            String taskType = ui.requestTaskType();
            this.getTaskDetails(quack, taskList, taskType.toUpperCase(), ui);
        } catch (InvalidTaskTypeException taskTypeError) {
            ui.printExceptionMessage(taskTypeError);
        }
    }

    /**
     * Retrieves the tasks details based on the task type the user input.
     * <p>
     * After retrieving the task details, the task will be created and added,
     * into the tasklist
     * @param quack The chatbot object quack.
     * @param taskList A list that stores all the tasks tracked by Quack. 
     * @param taskType The type of tasks to be created. 
     */
    private void getTaskDetails(Quack quack, TaskList taskList, String taskType, Ui ui) {
        
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
