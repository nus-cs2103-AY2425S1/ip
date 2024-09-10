package phenex.ui;

import phenex.task.Task;
import phenex.task.TaskList;

/**
 * Ui class encapsulating the user interface of Phenex.
 */
public class Ui {

    private static final String LINE = "\t____________________________________________________________\n";
    private static final String LOGO = "  _____    _    _   ______   _   _   ______  __   __\n"
            + " |  __ \\  | |  | | |  ____| | \\ | | |  ____| \\ \\ / /\n"
            + " | |__) | | |__| | | |__    |  \\| | | |__     \\ V /\n"
            + " | |      | |  | | | |____  | |\\  | | |____   / . \\\n"
            + " |_|      |_|  |_| |______| |_| \\_| |______| /_/ \\_\\\n";
    private static final String MSG_GREET = "Hello! I'm\n"
            + LOGO
            + "Your favourite solid gold mobile suit!\n"
            + LINE
            + "\tWhat can I do for you?\n"
            + LINE;
    private static final String MSG_FAREWELL = "\t Goodbye. Extinguish the Zeon forces on your way out!\n" + LINE;

    public void greet() {
        System.out.print(MSG_GREET);
    }

    /**
     * Says farewell.
     * @return the farewell message.
     */
    public String sayFarewell() {
        System.out.println(MSG_FAREWELL);
        return MSG_FAREWELL;
    }

    public void printLine() {
        System.out.print(LINE);
    }

    /**
     * Prints all tasks in the taskList.
     *
     * @param taskList the task list to be printed
     * @return String of the task list printed.
     */
    public String printTaskList(TaskList taskList) {
        String stringToPrint = generateTaskListString(taskList);
        System.out.println(stringToPrint);
        return stringToPrint;
    }

    /**
     * Generates a string which represents a given task list.
     * @param taskList the task list to have its string representation.
     * @return a string which is the string representation of the task list.
     */
    public String generateTaskListString(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = taskList.getTasks().size();
        if (size == 0) {
            stringBuilder.append("\t No scheduled missions. Rest up for the next battle, soldier!");
            return stringBuilder.toString();
        }

        stringBuilder.append("\t Outstanding missions:\n");
        for (int i = 0; i < size; i++) {
            String row = "\t " + (i + 1) + ". " + taskList.getTaskByIdx(i) + "\n";
            stringBuilder.append(row);
        }
        return stringBuilder.toString();
    }

    /**
     * Prints exception message.
     *
     * @param e Exception which contains the message to print.
     * @return the error message.
     */
    public static String printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }

    /**
     * Prints the relevant messages when a task is marked as complete.
     *
     * @param taskMarked task which was marked as complete
     * @return string that represents a task is marked as complete.
     */
    public String printTaskMarkedCompleteMessage(Task taskMarked) {
        String stringToPrint = generateTaskMarkedCompleteMessage(taskMarked);
        System.out.println(stringToPrint);
        return stringToPrint;
    }

    /**
     * Generates a task marked complete message.
     * @param taskMarked the task which was marked complete.
     * @return string which represents the message.
     */
    public String generateTaskMarkedCompleteMessage(Task taskMarked) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t Mission marked as complete. Good job, soldier!\n");
        stringBuilder.append("\t\t" + taskMarked + "\n");
        return stringBuilder.toString();
    }

    /**
     * Prints the relevant message when a task is marked as incomplete.
     *
     * @param taskMarked task which was marked as incomplete.
     * @return string that represents a task is marked as incomplete.
     */
    public String printTaskMarkedIncompleteMessage(Task taskMarked) {
        String stringToPrint = generateTaskMarkedIncompleteMessage(taskMarked);
        System.out.println(stringToPrint);
        return stringToPrint;
    }

    /**
     * Generates a task marked incomplete message.
     * @param taskMarked the task which was marked incomplete.
     * @return string which represents the message.
     */
    public String generateTaskMarkedIncompleteMessage(Task taskMarked) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t Mission marked as incomplete.\n");
        stringBuilder.append("\t\t" + taskMarked + "\n");
        return stringBuilder.toString();
    }

    /**
     * Prints the relevant messages when a task was deleted.
     *
     * @param taskDeleted task which was deleted.
     * @param taskListSize current size of the task list.
     * @return string that represents a successful deletion of a task.
     */
    public String printTaskDeletedMessage(Task taskDeleted, int taskListSize) {
        String stringToPrint = generateTaskDeletedMessage(taskDeleted, taskListSize);
        System.out.println(stringToPrint);
        return stringToPrint;
    }

    /**
     * Generates a task deleted message.
     * @param taskDeleted the task which was deleted.
     * @param taskListSize the current size of task list.
     * @return string that represents a successful deletion of a task.
     */
    public String generateTaskDeletedMessage(Task taskDeleted, int taskListSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t OK. Mission aborted, retreat!\n");
        stringBuilder.append("\t  " + taskDeleted + "\n");
        stringBuilder.append("\t " + taskListSize + " missions remaining. Destroy the enemy!\n");
        return stringBuilder.toString();
    }

    /**
     * Prints relevant message when a task is added.
     *
     * @param taskAdded task which was added.
     * @param taskListSize current size of task list.
     * @return string that represents a successful addition of a task.
     */
    public String printTaskAddedMessage(Task taskAdded, int taskListSize) {
        String stringToPrint = generateTaskAddedMessage(taskAdded, taskListSize);
        System.out.println(stringToPrint);
        return stringToPrint;
    }

    /**
     * Generates a task added message.
     * @param taskAdded the task which was added.
     * @param taskListSize the current size of task list.
     * @return string that represents a successful addition of a task.
     */
    public String generateTaskAddedMessage(Task taskAdded, int taskListSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t Mission " + taskAdded.getName() + " added:\n");
        stringBuilder.append("\t   " + taskAdded + "\n");
        stringBuilder.append("\t Total upcoming missions: " + taskListSize + "\n");
        return stringBuilder.toString();
    }

    /**
     * Prints relevant message when memory initialised.
     *
     */
    public static void printMemoryInitialisedMessage() {
        System.out.println("\tMemory successful initialised.");
    }

    /**
     * Prints relevant message when memory fails to initialise.
     *
     */
    public static void printMemoryInitialisingFailureMessage() {
        System.out.println("\tCorrupted memory detected. Aborting sequence!");
    }
}
