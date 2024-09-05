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
        int size = taskList.getTasks().size();
        String stringToReturn;
        if (size == 0) {
            stringToReturn = "\t No scheduled missions. Rest up for the next battle, soldier!";
            System.out.println("\t No scheduled missions. Rest up for the next battle, soldier!");
            return stringToReturn;
        }

        System.out.println("\t Outstanding missions:");
        stringToReturn = "\t Outstanding missions:\n";
        for (int i = 0; i < size; i++) {
            String row = "\t "
                    + (i + 1)
                    + "."
                    + taskList.getTaskByIdx(i);
            System.out.println(row);
            stringToReturn += row + "\n";
        }
        return stringToReturn;
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
        String stringToReturn = "\t Mission marked as complete. Good job, soldier!" + "\n";
        stringToReturn += "\t\t" + taskMarked + "\n";
        System.out.println("\t Mission marked as complete. Good job, soldier!");
        System.out.println("\t\t" + taskMarked);
        return stringToReturn;
    }

    /**
     * Prints the relevant message when a task is marked as incomplete.
     *
     * @param taskMarked task which was marked as incomplete.
     * @return string that represents a task is marked as incomplete.
     */
    public String printTaskMarkedIncompleteMessage(Task taskMarked) {
        String stringToReturn = "\t Mission marked as incomplete." + "\n";
        stringToReturn += "\t\t" + taskMarked + "\n";
        System.out.println("\t Mission marked as incomplete.");
        System.out.println("\t\t" + taskMarked);
        return stringToReturn;
    }

    /**
     * Prints the relevant messages when a task was deleted.
     *
     * @param taskDeleted task which was deleted.
     * @param taskListSize current size of the task list.
     * @return string that represents a successful deletion of a task
     */
    public String printTaskDeletedMessage(Task taskDeleted, int taskListSize) {
        String stringToReturn = "\t OK. Mission aborted, retreat!" + "\n";
        stringToReturn += "\t  " + taskDeleted + "\n";
        stringToReturn += "\t " + taskListSize + " missions remaining. Destroy the enemy!" + "\n";
        System.out.println("\t OK. Mission aborted, retreat!");
        System.out.println("\t  " + taskDeleted);
        System.out.println("\t " + taskListSize + " missions remaining. Destroy the enemy!");
        return stringToReturn;
    }

    /**
     * Prints relevant message when a task is added.
     *
     * @param taskAdded task which was added.
     * @param taskListSize current size of task list.
     * @return string that represents a successful addition of a task.
     */
    public String printTaskAddedMessage(Task taskAdded, int taskListSize) {
        String stringToReturn = "\t Mission " + taskAdded.getName() + " added:" + "\n";
        stringToReturn += "\t   " + taskAdded + "\n";
        stringToReturn += "\t Total upcoming missions: " + taskListSize + "\n";
        System.out.println("\t Mission " + taskAdded.getName() + " added:");
        System.out.println("\t   " + taskAdded);
        System.out.println("\t Total upcoming missions: " + taskListSize);
        return stringToReturn;
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
