package wansbot.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import wansbot.tasks.Task;
import wansbot.tasks.TaskList;

/**
 * This class handles all the UI that will be outputted to user.
 */
public class UI {
    private static final String HR = "----------------------------------------------------------------------";

    public UI() {
    }

    /**
     * Method that displays Bot message when bot first starts.
     */
    public void introduceToUser() {
        String logo = "                 __"
                + "\n|  |  /\\  |\\ | /__` "
                + "\n|/\\| /~~\\ | \\| .__/\n";

        System.out.println(HR + "\nWans:\n"
                + "Hey, I'm\n"
                + logo
                + "\nCan I help? (I can only manage a todo list so...)\n" + HR);
    }

    /**
     * Prints to console when invalid comments are input.
     */
    public void handleInvalidCommands(String userInput) {
        System.out.println(HR + "\nWans:\n"
                + "You need to input something after " + userInput
                + "!\n" + HR);
    }


    /**
     * Prints to console when input isn't recognised by WansBot.
     */
    public void handleUnrecognisedInput(String userInput) {
        System.out.println(HR + "\nWans: \n"
                + "I'm sorry I'm not that useful I don't know what "
                + userInput + " means!!!" + "\n" + HR);
    }

    /**
     * Prints to console when wrong date amd time format is input.
     */
    public void handleDateTimeException() {
        System.out.println(HR + "\nWans:\n"
                + "Your date needs to be in the form YYYY-MM-DD! It needs to be a valid \ndate too please!"
                + "\n" + HR);
    }

    /**
     * Prints to console when deadline date format is wrong.
     */
    public void handleDeadlineFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input deadline, followed by /by, then the deadline!"
                + "\n" + HR);
    }

    /**
     * Prints to console when event date format is wrong
     */
    public void handleEventFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input event, followed by /from, then your start time, then /to, then "
                + "your end time!"
                + "\n" + HR);
    }

    /**
     * Prints to console the list of tasks in current userTaskList
     */
    public void handleListingTask(TaskList taskList) {
        System.out.println(HR + "\nWans:"
                + "\nHere are your tasks!\n"
                + taskList.toString());
        System.out.println("You have " + taskList.numOfTasks() + " tasks!" + "\n" + HR);
    }

    /**
     * Prints to console when marking is successful.
     *
     * @param taskList Current useTaskList.
     * @param posTask Postion of task user wants to remove.
     */
    public void handleSuccesfulMarking(TaskList taskList, int posTask) {
        System.out.println(HR + "\nWans:"
                + "\nNice! I've marked\n"
                + taskList.getTask(posTask).toString()
                + " as completed\n" + HR);
    }

    /**
     * Prints to console when user inputs wrong format for mark.
     */
    public void handleMarkingFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input a single space, followed by a number after mark"
                + "!\n" + HR);
    }

    /**
     * Prints to console when task is unmarked successfully, along with the task deleted.
     */
    public void handleSuccesfulUnmarking(TaskList taskList, int posTask) {
        System.out.println(HR + "\nWans:"
                + "\nOkay, so you lied! I've marked\n"
                + taskList.getTask(posTask).toString()
                + " as uncompleted\n" + HR);
    }

    /**
     * Prints to console when unmarking format is wrong.
     */
    public void handleUnmarkingFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input a single space, followed by a number after unmark"
                + "!\n" + HR);
    }

    /**
     * Prints to console when either mark or unmark is of correct format but is acting on a number that doesn't exist
     * in the userTaskList.
     */
    public void handleInvalidNum() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input a valid number that exists in your TaskList!"
                + "\n" + HR);
    }

    /**
     * Prints to console when task is successfully added to userTaskList.
     */
    public void handleSuccessfulAdd(Task task) {
        System.out.println(HR + "\nWans:\n"
                + "Ok! I've added " + task.toString()
                + "\n" + HR);
    }

    /**
     * Prints to console when user successfully removes a task, as well as the task deleted.
     */
    public void handleRemoveTask(TaskList taskList, int posTask) {
        System.out.println(HR + "\nWans:\n"
                + "Ok! I've removed " + taskList.getTask(posTask)
                + "\n" + HR);
    }

    /**
     * Prints to console when wrong format of remove command is used.
     */
    public void handleRemoveFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input a single space, followed by a number after remove"
                + "!\n" + HR);
    }

    /**
     * Prints to console depending on the result of find command. If empty, WansBot will tell user that no tasks
     * fall on the date. If not then WansBot will tell the user exactly which events lie on date.
     */
    public void handleFindFiles(TaskList taskList, String date) {
        if (taskList.numOfTasks() != 0) {
            System.out.println(HR + "\nWans:"
                    + "\nHere are your tasks on " + LocalDate.parse(date)
                            .format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n"
                    + taskList.toString());
            System.out.println("You have " + taskList.numOfTasks() + " tasks " + "on "
                    + LocalDate.parse(date)
                            .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + "\n" + HR);
        } else {
            System.out.println(HR + "\nWans:"
                    + "\n You have no tasks on "
                    + LocalDate.parse(date)
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + "!" + "\n" + HR);
        }
    }

    /**
     * Prints to console when IOException occurs when user tries to save tasks to data folder.
     */
    public void handleIoExceptionSave() {
        System.out.println(HR + "\nWans:\n"
                + "I can't seem to save your tasks!"
                + "\n" + HR);
    }

    /**
     * Prints to console when user encounters IOException when trying to load files using the load command
     */
    public void handleIoExceptionLoad() {
        System.out.println(HR + "\nWans:\n"
                + "I can't seem to load your tasks!"
                + "\n" + HR);
    }

    /**
     * Prints to console when user does not currently have data folder which holds taskList.txt
     */
    public void handleFileNotFound() {
        System.out.println("\nWans:\n"
                + "You don't have files to load!"
                + "\n" + HR);
    }

    /**
     * Prints to console Goodbye message when user inputs bye
     */
    public void handleGoodbye() {
        String exit = "|  _ \\ \\   / /  ____|"
                + "\n| |_) \\ \\_/ /| |__"
                + "\n|  _ < \\   / |  __|"
                + "\n| |_) | | |  | |____"
                + "\n|____/  |_|  |______";
        System.out.println(HR + "\nWans: \n"
                + exit
                + "\nI'll miss you :( (I really wanna go home)\n" + HR);
        System.exit(0);
    }
}
