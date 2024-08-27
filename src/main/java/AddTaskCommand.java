import java.time.format.DateTimeParseException;

/**
 * This class is responsible for handling addition of tasks to the task list,
 * based on what the user entered
 */
public class AddTaskCommand extends Command{
    
    /** A list of all possible task types */
    private enum TaskTypes {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Creates a AddTaskCommand object.
     */
    public AddTaskCommand() {
       
    }

    @Override
    public void execute(Quack quack, TaskList taskList, Storage storage){
        // Get task type from user
        System.out.println("What type of task do you want to add");
        String taskType = quack.sc.nextLine();
        try {
            checkTaskType(taskType);
            this.getTaskDetails(quack, taskList, taskType.toUpperCase());
        } catch (InvalidTaskTypeException taskTypeError) {
            System.out.println(taskTypeError.getMessage());
        }

    }

    /**
     * Checks if the task type given by the user is a valid one.
     * @param taskType The type of tasks to be created.
     * @throws InvalidTaskTypeException If the user inputs a invalid task type.
     */
    private void checkTaskType(String taskType) throws InvalidTaskTypeException{
        String upperCasedTaskType = taskType.toUpperCase();
        for (TaskTypes tasktypes : TaskTypes.values()) {
            if (tasktypes.name().equals(upperCasedTaskType)) {
                return;
            }
        }
        throw new InvalidTaskTypeException(taskType);
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
    private void getTaskDetails(Quack quack, TaskList taskList, String taskType) {
        
        String taskDescription = null;
        String startDate = null;
        String endDate = null;

        // Retrieve task details based on the task type
        switch (taskType) {
        case "TODO":
            System.out.println("What is the TODO task description?");
            taskDescription = quack.sc.nextLine();
            break;
            
        case "DEADLINE":
            System.out.println("What is the deadline task description?");
            taskDescription = quack.sc.nextLine();

            System.out.println("When is the deadline due?");
            startDate = quack.sc.nextLine();
            break;

        case "EVENT":
            System.out.println("What is the event task description?");
            taskDescription = quack.sc.nextLine();

            System.out.println("When does the event start?");
            startDate = quack.sc.nextLine();

            System.out.println("When does the event end?");
            endDate = quack.sc.nextLine();
            break;
        }

        String[] information = {taskType, taskDescription, startDate, endDate};

        try {
            Task newTask = Task.createTask(information);
            taskList.addTask(newTask);
            System.out.println(newTask.toString());

        } catch (DateTimeParseException dateParseError) {
            System.out.println("Im sorry but the date input is invalid try DD/MM/YYYY HH:MM:SS");
        } catch (InvalidDateTimeException dateTimeError) {
            System.out.println(dateTimeError.getMessage());
        } 
    }
}
