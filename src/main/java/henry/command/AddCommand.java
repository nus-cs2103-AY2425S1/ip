package henry.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import henry.HenryException;
import henry.task.Deadline;
import henry.task.Event;
import henry.task.Todo;
import henry.util.TaskList;
import henry.util.Ui;

/**
 * Deals with adding task into TaskList
 */
public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Returns a string telling user that task is added
     *
     * @param taskList Instance of a TaskList class that contains
     *                 an array of tasks.
     * @param ui Instance of an Ui class that interacts with the user.
     * @return Statement telling user that task is added.
     * @throws HenryException If user input is invalid.
     */
    public String execute(TaskList taskList, Ui ui) throws HenryException {
        int numOfTasks = taskList.getTasks().size();

        String[] words = this.input.split(" ");
        String taskCommand = words[0].toLowerCase();

        String[] activityAndTimeList = getActivityAndTimeList(words);

        switch (taskCommand) {
        case "todo" -> addToDo(taskList, words, activityAndTimeList[0]);

        case "deadline" -> addDeadline(taskList, activityAndTimeList);

        case "event" -> addEvent(taskList, activityAndTimeList);

        default -> addInvalidTask();
        }
        return getTaskAddedConfirmation(taskList, numOfTasks);
    }

    /**
     * Returns an array containing the activity and time of activity.
     *
     * @param words A list containing words of the user's input.
     * @return An array containing the activity and time of activity.
     */
    private String[] getActivityAndTimeList(String[] words) {
        String activityAndTime = this.input.replaceFirst(words[0] + " ", "");
        return activityAndTime.split(" /");
    }

    /**
     * Returns a summary of the task added.
     *
     * @param taskList Instance of a TaskList class that contains
     *                 an array of tasks.
     * @param numOfTasks Number of tasks in taskList.
     * @return A summary of the task added.
     */
    private static String getTaskAddedConfirmation(TaskList taskList, int numOfTasks) {
        return "Got it. I've added this task:\n"
                + taskList.getTasks().get(numOfTasks).toString()
                + "\nNow you have "
                + (numOfTasks + 1)
                + (numOfTasks + 1 <= 1 ? " task" : " tasks")
                + " in the list.\n";
    }

    /**
     * Informs user that input added is not a task.
     *
     * @throws HenryException If user input is not a task.
     */
    private static void addInvalidTask() throws HenryException {
        //check for invalid input
        throw new HenryException("This is not a task!! "
                + "To write a task, start with "
                + "\"" + "todo" + "\","
                + " \"" + "deadline" + "\" or"
                + " \"" + "event" + "\"");
    }

    /**
     * Adds Event object into taskList.
     *
     * @param taskList Instance of a TaskList class that contains
     *                 an array of tasks.
     * @param activityAndTimeList An array containing the activity and time of activity.
     * @throws HenryException If event description is wrong.
     */
    private static void addEvent(TaskList taskList, String[] activityAndTimeList) throws HenryException {
        //check if event description is valid
        if (activityAndTimeList.length != 3) {
            throw new HenryException("The event description is wrong!! "
                    + "Ensure that you have included the activity, "
                    + "followed by the start time and end time. "
                    + "Example: event project meeting /from Mon 2pm /to 4pm");
        }
        String activity = activityAndTimeList[0];
        String startTime = activityAndTimeList[1]
                .replaceFirst("from ", "");
        String endTime = activityAndTimeList[2]
                .replaceFirst("to ", "");
        taskList.getTasks().add(new Event(activity, startTime, endTime));
    }

    /**
     * Adds Deadline object into taskList.
     *
     * @param taskList Instance of a TaskList class that contains
     *                 an array of tasks.
     * @param activityAndTimeList An array containing the activity and time of activity.
     * @throws HenryException If deadline description is wrong.
     */
    private static void addDeadline(TaskList taskList, String[] activityAndTimeList) throws HenryException {
        //check if deadline description is valid
        if (activityAndTimeList.length != 2) {
            throw new HenryException("The deadline description is wrong!! "
                    + "Ensure that you have included the activity, "
                    + "followed by the deadline. "
                    + "Example: deadline return book /by 2019-12-01 1900");
        }
        String activity = activityAndTimeList[0];
        String dateTime = activityAndTimeList[1]
                .replaceFirst("by ", "");
        String convertedDateTime = convertDateTime(dateTime);
        taskList.getTasks().add(new Deadline(activity, convertedDateTime));
    }

    /**
     * Adds Todo object into taskList.
     *
     * @param taskList Instance of a TaskList class that contains
     *                 an array of tasks.
     * @param words A list containing words of the user's input.
     * @param activity Name of the activity.
     * @throws HenryException If todo description is wrong.
     */
    private static void addToDo(TaskList taskList, String[] words, String activity) throws HenryException {
        //check if todo description is valid
        if (words.length == 1) {
            throw new HenryException("The todo description is wrong!! "
                    + "Ensure that you have included the activity. "
                    + "Example: todo read book");
        }
        taskList.getTasks().add(new Todo(activity));
    }

    /**
     * Returns date in the form of MMM dd yyyy.
     *
     * @param dateTime Date in the form of YYYY-MM-DD.
     * @return Date in the form of MMM dd yyyy.
     * @throws HenryException If date and time format is wrong.
     */
    public static String convertDateTime(String dateTime) throws HenryException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh.mm a");
            return localDateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new HenryException("Please write the date and time in "
                    + "the following format: YYYY-MM-DD HHmm");
        }
    }
}
