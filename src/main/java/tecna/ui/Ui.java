package tecna.ui;

import tecna.collection.TaskList;
import tecna.task.Task;

/**
 * Handles Tecna's responses' interface.
 *
 * @author DennieDan.
 */
public class Ui {
    /**
     * Prints the logo.
     *
     * @return A String containing a logo.
     */
    public String printLogo() {
        String logo =
                " **          **\n" +
                "*  *        *  *\n" +
                "*   *      *   *\n" +
                "*    *    *    *\n" +
                "*     *  *     *\n" +
                " *     **     *\n" +
                "  *    **    *\n" +
                "   *   **   *\n" +
                "    *  **  *\n" +
                "     ******\n" +
                "      ****\n" +
                "     * ** *\n" +
                "    *  **  *\n" +
                "    ***  ***\n";

        System.out.println(logo);
        return logo;
    }

    /**
     * Prints a hello message.
     *
     * @return a string with a hello message.
     */
    public String printHelloMsg() {
        System.out.println("I'm Tecna!\nHow can I help you?");
        printSectionLine();
        return "I'm Tecna!\nHow can I help you?";
    }

    /**
     * Prints a goodbye message.
     *
     * @return a string with a goodbye message.
     */
    public String printGoodbyeMsg() {
        String msg = "Pleased to help you! See you again ^_^";
        System.out.println(msg);
        printSectionLine();
        return msg;
    }

    /**
     * Prints all items in the list.
     *
     * @param tasks The current TaskList in use.
     * @return A string containing the response.
     */
    public String printItems(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
        sb.append(tasks.toString());
        sb.append("\n>> Now you have " + tasks.getSize() + (tasks.getSize() > 1 ? " tasks" : " task") + " in the list.");
        return sb.toString();
    }

    /**
     * Prints a section line, used for text ui.
     */
    public void printSectionLine() {
        System.out.println("----------------------------------------------");
    }

    /**
     * Prints a message responded when a mark command is executed successfully.
     *
     * @param markedTask The task which has just marked.
     * @return A String of response.
     */
    public String printMarkMsg(Task markedTask) {
        StringBuilder sb = new StringBuilder("Nice job! I've mark this as done. You deserve a short break <3\n");
        sb.append(markedTask);
        String msg = sb.toString();
        System.out.println(msg);
        printSectionLine();
        return msg;
    }

    /**
     * Prints a message responded when an unmark command is executed successfully.
     *
     * @param unmarkedTask The task which has just been unmarked.
     * @return A String of response.
     */
    public String printUnmarkMsg(Task unmarkedTask) {
        StringBuilder sb = new StringBuilder("I've mark this as undone. Keep going, my friend!\n");
        sb.append(unmarkedTask);
        String msg = sb.toString();
        System.out.println(msg);
        return msg;
    }

    /**
     * Prints the message responded when the delete command is executed successfully.
     *
     * @param tasks The current TaskList in use.
     * @param index Index of the task needs to be deleted.
     * @return A string of response.
     */
    public String printDeleteItemMsg(TaskList tasks, int index) {
        return tasks.deleteItem(index);
    }

    /**
     * Prints a message responded when a find command is executed successfully.
     *
     * @param tasks The current TaskList in use.
     * @param keyword Keyword used to search.
     * @return A string contains all the matched tasks.
     */
    public String printFindTasksMsg(TaskList tasks, String keyword) {
        return tasks.findTasks(keyword);
    }

    /**
     * Prints a message responded when the todo/deadline/event command is executed successfully.
     *
     * @param tasks Current TaskList in use.
     * @param item Keyword used to search.
     * @return A string of response.
     */
    public String printAddItemMsg(TaskList tasks, Task item) {
        StringBuilder sb = new StringBuilder("Sure! I've added this tasks:\n");
        sb.append(item + "\n");
        sb.append(">> Now you have " + tasks.getSize() + (tasks.getSize() > 1 ? " tasks" : " task") + " in the list.\n");
        String response = sb.toString();
        System.out.println(response);
        return response;
    }

    /**
     * Prints a response if the command is invalid.
     *
     * @return A string of response.
     */
    public String printInvalidCmdError() {
        String msg = "Oops! Your request sounds strange for me. Please enter a valid request ^^";
        System.out.println(msg);
        return msg;
    }

    /**
     * Prints an error message.
     *
     * @param error An error.
     * @return A string of alert.
     */
    public String printError(String error) {
        StringBuilder sb = new StringBuilder("Oops! \n" + error);
        String msg = sb.toString();
        System.out.println(msg);
        return msg;
    }

    /**
     * Prints an error for task duplication.
     *
     * @param duplicateTask Duplicated task.
     * @return A string of alert.
     */
    public String printTaskDuplicateError(Task duplicateTask) {
        String msg =  printError("This task already exists!\n") + duplicateTask;
        return msg;
    }
}
