package hamyo.misc;

import hamyo.tasks.Task;

/**
 * Deals with interactions with the user. Pushes texts to terminal.
 *
 * @author Han Yu
 */
public class Ui {

    public static final String LINE = "___________________________________________"
        + "_________________________________________________";
    public static final String LOGO = "$$   $$   $$$$    $$$$ $$$$   $$   $$  $$$$$$\n"
        + "$$   $$  $$  $$  $$  $$$  $$  $$   $$  $$  $$\n"
        + "$$$$$$$  $$$$$$  $$  $$$  $$  $$$$$$$  $$  $$\n"
        + "$$   $$  $$  $$  $$  $$$  $$       $$  $$  $$\n"
        + "$$   $$  $$  $$  $$  $$$  $$  $$$$$$   $$$$$$";
    private static String currString;

    /**
     * Get the response to be printed on terminal or GUI after command executes.
     */
    public static String getResponse() {
        return Ui.currString;
    }

    /**
     * Reset the String to an empty String
     */
    public static void resetResponse() {
        Ui.currString = "";
    }

    /**
     * Pushes greeting text to users' terminal.
     */
    public void greet() {
        Ui.currString = Ui.LINE + "\n";
        Ui.currString += Ui.LOGO + "\n";
        Ui.currString += "\nAnnyeonghaseyo! Hamyo here!\n"
                + "How may I assist you today?\n";
        Ui.currString += Ui.LINE;
        System.out.println(Ui.currString);
    }

    /**
     * Pushes goodbye text to users' terminal.
     */
    public void terminate() {
        Ui.currString = "Annyeong! Till we meet again. <3\n";
        Ui.currString += Ui.LINE;
        System.out.println(Ui.currString);
    }

    /**
     * Set current String after successful adding of task.
     *
     * @param task The added task.
     * @param size The size of the users' list of tasks after task addition.
     */
    public static void setStringAddTask(Task task, int size) {
        Ui.currString += "Got it. I've added this task:\n";
        Ui.currString += task.toString() + "\n";
        Ui.currString += "There are " + size + " tasks in the list now.\n";
        Ui.currString += Ui.LINE + "\n";
    }

    /**
     * Set current String after successful deletion of task.
     *
     * @param task The deleted task.
     * @param size The size of the users' list of tasks after task deletion.
     */
    public static void setStringDeleteTask(Task task, int size) {
        Ui.currString += "Noted. I've removed this task:\n";
        Ui.currString += task.toString() + "\n";
        Ui.currString += "There are " + size + " tasks in the list now.\n";
        Ui.currString += Ui.LINE + "\n";
    }

    /**
     * Set current String after successful listing of task.
     *
     * @param tasksList The list of the users' tasks in String format.
     */
    public static void setStringListTasks(String tasksList) {
        Ui.currString += "These are your tasks:\n";
        Ui.currString += tasksList.isEmpty() ? "No tasks found!\n" : tasksList + "\n";
        Ui.currString += Ui.LINE + "\n";
    }

    /**
     * Set current String after successful listing of task on the specified
     * date.
     *
     * @param tasksList The list of the users' tasks in String format.
     * @param formattedDate The specified date in String format.
     */
    public static void setStringListTasksByDate(String tasksList, String formattedDate) {
        Ui.currString += "These are your tasks on " + formattedDate + ".\n";
        Ui.currString += tasksList + "\n";
        Ui.currString += Ui.LINE + "\n";
    }

    /**
     * Set current String after successful listing of task with the specified
     * keyword.
     *
     * @param tasksList The list of the users' tasks in String format.
     * @param keyword The keyword to filter users' tasks by.
     */
    public static void setStringListTasksByKeyword(String tasksList, String keyword) {
        Ui.currString += "Here are the matching tasks in your list for " + keyword.toUpperCase() + ".\n";
        Ui.currString += tasksList + "\n";
        Ui.currString += Ui.LINE + "\n";
    }

    /**
     * Set current String after successful marking of task.
     *
     * @param task The marked task.
     */
    public static void setStringMarkTask(Task task) {
        Ui.currString += "Yay! This task has been marked as completed.\n";
        Ui.currString += task.toString() + "\n";
        Ui.currString += Ui.LINE + "\n";
    }

    /**
     * Set current String after successful unmarking of task.
     *
     * @param task The unmarked task.
     */
    public static void setStringUnmarkTask(Task task) {
        Ui.currString += "Oki! This task has been marked as incomplete.\n";
        Ui.currString += task.toString() + "\n";
        Ui.currString += Ui.LINE + "\n";
    }

    /**
     * Set current String when executing mass operation command.
     *
     * @param command The mass operation command.
     * @param count The number of tasks operated on.
     */
    public static void setStringMassOps(String command, int count) {
        Ui.currString = "Aight Bet! I am " + command + " " + count + " tasks!\n";
        Ui.currString += Ui.LINE + "\n";
    }

    /**
     * Set current String to Exception message when Exception is encountered.
     *
     * @param e The Exception encountered.
     */
    public static void setStringException(Exception e) {
        Ui.currString += e.toString() + "\n";
        Ui.currString += Ui.LINE + "\n";
    }
}
