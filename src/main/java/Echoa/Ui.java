package Echoa;

/**
 * Ui is a class that encapsulate the print methods that are used in the program.
 */
public class Ui {
    public static final String[] INSTRUCTION_LIST = {"todo", "deadline", "event", "mark", "unmark", "delete", "list", "bye"};

    /**
     * The method prints the greeting to the user when the program is run.
     */
    public void greetUserStart() {
        System.out.println("Hello, I'm Echoa!");
        System.out.println("What can I do for you?\n");
    }

    /**
     * The method prints the greeting to the user when the program is closed.
     */
    public void greetUserEnd() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * The method prints the necessary information regarding the task added.
     *
     * @param taskList taskList which the task is added into.
     */
    public void printAddTaskMessage(TaskList taskList) {
        System.out.println("Task added!");
        System.out.println(taskList.getSpecificTask(taskList.getSize() - 1).toString());
    }

    /**
     * The method prints the necessary information regarding the task deleted.
     *
     * @param taskList taskList which the task is deleted from.
     * @param index index of task to be deleted.
     */
    public void printDeleteTaskMessage(TaskList taskList, int index) {
        System.out.println("Task deleted :/");
        System.out.println(taskList.getSpecificTask(index).toString() + "\n");
        System.out.println("Now you have " + (taskList.getSize()) + " task(s).\n");
    }

    /**
     * The method prints the necessary information regarding the task marked.
     *
     * @param taskList taskList which the task is marked from.
     * @param index index of task to be marked.
     */
    public void printMarkTaskMessage(TaskList taskList, int index) {
        System.out.println("Task marked :)");
        System.out.println(taskList.getSpecificTask(index).toString() + "\n");
    }

    /**
     * The method prints the necessary information regarding the task unmarked.
     *
     * @param taskList taskList which the task is unmarked from.
     * @param index index of task to be unmarked.
     */
    public void printUnmarkTaskMessage(TaskList taskList, int index) {
        System.out.println("Task unmarked :(");
        System.out.println(taskList.getSpecificTask(index).toString() + "\n");
    }

    /**
     * The method prints the number of tasks present in the taskList.
     *
     * @param taskList taskList which requires the number of tasks printed.
     */
    public void informNumberOfTasks(TaskList taskList) {
        System.out.println("Now you have " + (taskList.getSize()) + " task(s).\n");
    }

    /**
     * The method prints information to ask user to try again.
     */
    public void askUserToTryAgain() {
        System.out.println("Please try again.");
    }

    /**
     * The method prints the InvalidInstructionException message.
     *
     * @param e Exception that requires its message to be printed.
     */
    public void printInvalidInstructionExceptionMessage(InvalidInstructionException e) {
        System.out.println(e.getMessage());
    }

    /**
     * The method prints the InvalidContentException message.
     * @param e Exception that requires its message to be printed.
     */
    public void printInvalidContentException(InvalidTaskContentException e) {
        System.out.println(e.getMessage());
    }

    /**
     * The method prints the InvalidToDoContentException message.
     * @param e  Exception that requires its message to be printed.
     */
    public void printInvalidToDoContentException(InvalidToDoContentException e) {
        System.out.println(e.getMessage());
    }

    /**
     * The method prints the InvalidDeadlineContentException message.
     * @param e  Exception that requires its message to be printed.
     */
    public void printInvalidDeadlineContentException(InvalidDeadlineContentException e) {
        System.out.println(e.getMessage());
    }

    /**
     * The method prints the InvalidEventContentException message.
     * @param e  Exception that requires its message to be printed.
     */
    public void printInvalidEventContentException(InvalidEventContentException e) {
        System.out.println(e.getMessage());
    }

    /**
     * The method prints the ListOutOfBoundsException message.
     * @param e  Exception that requires its message to be printed.
     */
    public void printListOutOfBoundsException(ListOutOfBoundsException e) {
        System.out.println(e.getMessage());
    }


}
