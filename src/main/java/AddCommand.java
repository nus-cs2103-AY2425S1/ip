import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Adds new task into the array of tasks recorded and split them into todo, event and deadline
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param ui instance of a Ui class that interacts with the user
     * @param storage instance of a storage that contains tasks
     *                recorded previously
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws HenryException {
        int numOfTasks = taskList.getTasks().size();
        String[] words = this.input.split(" ");
        String task = words[0].toLowerCase();
        String activityAndTime = this.input.replaceFirst(words[0] + " ", "");
        String[] activityAndTimeList = activityAndTime.split(" /");
        String activity = activityAndTimeList[0];
        if (task.equals("todo")) {
            //check if todo description is valid
            if (words.length == 1 ) {
                throw new HenryException("The todo description is wrong!! " +
                        "Ensure that you have included the activity. " +
                        "Example: todo read book");
            }
            taskList.getTasks().add(new Todo(activity));
        } else if (task.equals("deadline")) {
            //check if deadline description is valid
            if (activityAndTimeList.length != 2 ) {
                throw new HenryException("The deadline description is wrong!! " +
                        "Ensure that you have included the activity, " +
                        "followed by the deadline. " +
                        "Example: deadline return book /by 2019-12-01 1900");
            }
            String dateTime = activityAndTimeList[1]
                    .replaceFirst("by ", "");
            String convertedDateTime = convertDateTime(dateTime);
            taskList.getTasks().add(new Deadline(activity, convertedDateTime));
        } else if (task.equals("event")) {
            //check if event description is valid
            if (activityAndTimeList.length != 3 ) {
                throw new HenryException("The event description is wrong!! " +
                        "Ensure that you have included the activity, " +
                        "followed by the start time and end time. " +
                        "Example: event project meeting /from Mon 2pm /to 4pm");
            }
            String startTime = activityAndTimeList[1]
                    .replaceFirst("from ", "");
            String endTime = activityAndTimeList[2]
                    .replaceFirst("to ", "");
            taskList.getTasks().add(new Event(activity, startTime, endTime));
        } else {
            //check for invalid input
            throw new HenryException("This is not a task!! " +
                    "To write a task, start with "
                    + "\"" + "todo" +"\","
                    + " \"" + "deadline" +"\" or"
                    + " \"" + "event" +"\"");
        }
        System.out.println("\nGot it. I've added this task:\n"
                + taskList.getTasks().get(numOfTasks).toString()
                + "\nNow you have "
                + (numOfTasks + 1)
                + (numOfTasks + 1 <= 1 ? " task" : " tasks")
                + " in the list.\n");

        save(taskList, storage);
    }

    /**
     * Returns date in the form of MMM dd yyyy
     *
     * @param dateTime date in the form of YYYY-MM-DD
     * @return date in the form of MMM dd yyyy
     */
    public static String convertDateTime(String dateTime) throws HenryException {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh.mm a");
            return localDateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new HenryException("Please write the date and time in " +
                    "the following format: YYYY-MM-DD HHmm");
        }
    }


}
