package mendel.metacognition;

import java.util.ArrayList;
import java.util.List;

import mendel.datetime.DateTimeManager;
import mendel.discretetask.Task;
import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;

/**
 * Represents a list of tasks with functionality to manage and interact with the tasks.
 * Provides operations to add, remove, mark, unmark, and find tasks within the list.
 */
public class TaskList extends Command {
    private final List<Task> messages;
    private int counter;

    /**
     * Constructs a new TaskList object with an empty list of tasks and a counter set to 0.
     */
    public TaskList() {
        this.messages = new ArrayList<>();
        this.counter = 0;
    }

    /**
     * Retrieves a task by its serial number.
     *
     * @param serial The serial number of the task to retrieve.
     * @return The Task object at the specified serial number.
     * @throws MendelException If the serial number is out of bounds.
     */
    public Task getTask(int serial) {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        return messages.get(serial);
    }

    /**
     * Checks if a task with the specified serial number exists in the list.
     *
     * @param serial The serial number of the task to check.
     * @return true if the task exists, false otherwise.
     */
    public boolean hasTask(int serial) {
        return serial >= 0 && serial < this.counter;
    }

    /**
     * Checks if the list contains exactly one task.
     *
     * @return true if there is only one task in the list, false otherwise.
     */
    public boolean isFirstTask() {
        return this.counter == 1;
    }

    /**
     * Marks a task as done by its serial number.
     *
     * @param serial The serial number of the task to mark as done.
     * @return A message indicating that the task has been marked as done.
     * @throws MendelException If the serial number is out of bounds.
     */
    public String marker(int serial) throws MendelException {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        Task task = this.messages.get(serial);
        task.markAsDone();
        return String.format("Nice! I've marked this task as done:\n  %s",
                task);
    }

    /**
     * Marks a task as not done by its serial number.
     *
     * @param serial The serial number of the task to mark as not done.
     * @return A message indicating that the task has been marked as not done.
     * @throws MendelException If the serial number is out of bounds.
     */
    public String unMarker(int serial) throws MendelException {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        Task task = this.messages.get(serial);
        task.markAsUnDone();
        return String.format("OK, I've marked this task as not done yet:\n  %s",
                task);
    }

    /**
     * Deletes a task by its serial number.
     *
     * @param serial The serial number of the task to delete.
     * @return A message indicating that the task has been removed.
     * @throws MendelException If the serial number is out of bounds.
     */
    public String delete(int serial) throws MendelException {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        Task task = this.messages.remove(serial);
        this.counter--;
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task,
                this.counter);
    }

    /**
     * Adds a new task to the list.
     *
     * @param element The Task object to be added.
     * @return A message indicating that the task has been added.
     */
    public String add(Task element) {
        this.messages.add(element);
        this.counter++;
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                element, this.counter);
    }

    /**
     * Adds a new task to the list without message.
     *
     * @param element The task to be added.
     */
    public void silencedAdd(Task element) {
        this.messages.add(element);
        this.counter++;
    }

    /**
     * Finds tasks with deadlines matching the specified date of type String.
     *
     * @param inputInformation The information containing
     * date to search for in the format understood by {@code DateTimeManager}.
     * @return A message listing all tasks with deadlines on the specified date.
     */
    public String find(String inputInformation) {
        String rawDate = inputInformation.split("remind ")[1];
        String formattedDate = new DateTimeManager(rawDate).toString();
        return String.format("Here are the tasks with deadlines by %s", formattedDate)
                + draftItemList(formattedDate);
    }
    private String draftItemList(String formattedDate) {
        String finalMessage = "";
        int increment = 0;
        for (int i = 0; i < counter; i++) {
            if (this.messages.get(i).isIncompleteWithinTargetDueDate(formattedDate)) {
                increment++;
                finalMessage += String.format("\n%d.%s", increment, this.messages.get(i).toString());
            }
        }
        return finalMessage;
    }

    /**
     * Finds tasks that match the description provided in the command string.
     *
     * @param fullCommand The full command string containing the description to search for.
     * @return A message listing all tasks that match the description.
     */
    public String findDescription(String fullCommand) {
        String[] segments = fullCommand.split("find ");
        String matchString = stringMatcher(segments);
        String finalMessage = "Here are the matching tasks in your list" + draftDescriptionList(matchString);
        return finalMessage;
    }

    private String draftDescriptionList(String matchString) {
        String finalMessage = "";
        int increment = 0;
        for (int i = 0; i < counter; i++) {
            if (this.messages.get(i).isMatchingDescription(matchString)) {
                increment++;
                finalMessage += String.format("\n%d.%s", increment, this.messages.get(i).toString());
            }
        }
        return finalMessage;
    }

    private String stringMatcher(String[] segments) {
        String matchString = "";
        for (int i = 1; i < segments.length; i++) {
            if (i == 1) {
                matchString += segments[i];
            } else {
                matchString += "find " + segments[i];
            }
        }
        return matchString;
    }

    /**
     * Lists all tasks in the list.
     *
     * @return A message listing all tasks in the current list.
     */
    public String speak() {
        return String.format("Here are the tasks in your list:\n%s", this);
    }

    /**
     * Returns a string representation of all tasks in the list.
     *
     * @return A string listing all tasks with their serial numbers.
     */
    @Override
    public String toString() {
        String finalMessage = "";
        if (counter > 0) {
            finalMessage = String.format("1.%s", this.messages.get(0).toString());
        }
        for (int i = 1; i < counter; i++) {
            int increment = i + 1;
            finalMessage += String.format("\n%d.%s", increment, this.messages.get(i).toString());
        }
        return finalMessage;
    }
}
