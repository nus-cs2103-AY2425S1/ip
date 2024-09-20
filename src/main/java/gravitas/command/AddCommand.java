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
        // Check for empty or whitespace-only data
        String[] trimmedData = data.trim().split("\\s+", 2);

        if (trimmedData.length < 2) {
            return false;
        }

        String taskType = trimmedData[0];
        String taskDetails = trimmedData[1];
        boolean isValidOrder;

        switch (taskType) {
        case "todo":
            isValidOrder = !taskDetails.trim().isEmpty();
            break;
        case "deadline":
            isValidOrder = taskDetails.contains("/by");
            break;
        case "event":
            int fromIndex = taskDetails.indexOf("/from");
            int toIndex = taskDetails.indexOf("/to");
            isValidOrder = fromIndex != -1 && toIndex != -1 && fromIndex < toIndex; // Checks /from before /to
            break;
        default:
            isValidOrder = false; // Invalid task type
        }

        return isValidOrder;
    }

    private String[] formatTaskData(String data) throws GravitasException {
        try {
            String[] trimmedData = data.split(" ", 2);
            String taskType = trimmedData[0];
            String[] formattedData;
            switch (taskType) {
            case "todo":
                formattedData = new String[]{trimmedData[1]};
                break;
            case "deadline":
                String[] formattedBy = trimmedData[1].split(" /by ", 2);
                formattedData = new String[]{trimmedData[1], formattedBy[1]};
                break;
            case "event":
                String eventDescription = trimmedData[1].split(" /from")[0];
                String formattedFrom = trimmedData[1].split(" /from ")[1].split(" /to ")[0];
                String formattedTo = trimmedData[1].split(" /to ")[1];
                formattedData = new String[]{eventDescription, formattedFrom, formattedTo};
                break;
            default:
                formattedData = new String[]{};
            }
            return formattedData;
        } catch (Exception e) {
            throw new GravitasException(INVALID_TASK_FORMAT);
        }
    }
}
