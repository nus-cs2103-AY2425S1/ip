package gravitas.command;

import gravitas.exception.GravitasException;
import gravitas.storage.Storage;
import gravitas.task.Deadline;
import gravitas.task.Event;
import gravitas.task.Task;
import gravitas.task.Todo;
import gravitas.tasklist.TaskList;

/**
 * Represents the command to add a task to the tasklist.
 */
public class AddCommand extends Command {

    public static final String INVALID_TASK_FORMAT = "Invalid task format. Try using the help command!";
    private static final String ADDED = "Got it. I've added this task:";
    private String userInput;

    /**
     * Constructor for AddCommand class.
     */
    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parses the user input and save the user task into the tasklist.
     *
     * @throws GravitasException If the user input is invalid.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) throws GravitasException {
        String[] msgFrag = userInput.split(" ", 2);
        Task task;
        String taskType = msgFrag[0];
        if (!validateTaskData(userInput)) {
            throw new GravitasException(INVALID_TASK_FORMAT);
        }

        String[] description = formatTaskData(userInput);

        if (taskType.equals("deadline")) {
            assert description.length == 2 : "Description should not be empty";
            task = new Deadline(description[0], description[1]);
        } else if (taskType.equals("event")) {
            task = new Event(description[0], description[1], description[2]);
        } else if (taskType.equals("todo")) {
            task = new Todo(description[0]);
        } else {
            throw new GravitasException(INVALID_TASK_FORMAT);
        }
        taskList.addTask(task);
        System.out.println(ADDED);
        return (ADDED + "\n" + taskList.printTask(task));
    }

    private boolean validateTaskData(String data) {
        String[] trimmedData = data.split(" ");
        String taskType = trimmedData[0];
        boolean isValidOrder;
        //Check for whitespace
        for (String s : trimmedData) {
            boolean isEmptyData = s.trim().isEmpty();
            if (isEmptyData) {
                return false;
            }
        }
        //Assign order based on task type
        switch (taskType) {
        case "todo":
            isValidOrder = (trimmedData.length == 2);
            break;
        case "deadline":
            isValidOrder = (trimmedData.length == 5 && trimmedData[2].equals("/by"));
            break;
        case "event":
            isValidOrder = (trimmedData.length == 8 && trimmedData[2].equals("/from")
                    && trimmedData[5].equals("/to"));
            break;
        default:
            isValidOrder = false;
        }
        return isValidOrder;
    }

    private String[] formatTaskData(String data) {
        String[] trimmedData = data.split(" ");
        String taskType = trimmedData[0];
        String[] formattedData;
        switch (taskType) {
        case "todo":
            formattedData = new String[]{trimmedData[1]};
            break;
        case "deadline":
            formattedData = new String[]{trimmedData[1], trimmedData[3] + " " + trimmedData[4]};
            break;
        case "event":
            formattedData = new String[]{trimmedData[1], trimmedData[3] + " " + trimmedData[4],
                    trimmedData[6] + " " + trimmedData[7]};
            break;
        default:
            formattedData = new String[]{};
        }
        return formattedData;
    }
}
