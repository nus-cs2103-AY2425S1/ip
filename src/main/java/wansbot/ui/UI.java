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
    public String introduceToUser() {
        String logo = "                 __"
                + "\n|  |  /\\  |\\ | /__` "
                + "\n|/\\| /~~\\ | \\| .__/\n";

        return "Hey, I'm\n"
                + logo
                + "\nCan I help? (I can only manage a todo list so...)\n";
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
    public String handleUnrecognisedInput(String userInput) {
        return "I'm sorry I'm not that useful I don't know what "
                + userInput + " means!!!";
    }

    /**
     * Prints to console when wrong date amd time format is input.
     */
    public String handleDateTimeException() {
        return "Your date needs to be in the form YYYY-MM-DD! It needs to be a valid date too please!";
    }

    /**
     * Prints to console when deadline date format is wrong.
     */
    public String handleDeadlineFormat() {
        return "You need to input deadline, followed by /by, then the deadline!";
    }

    /**
     * Prints to console when event date format is wrong
     */
    public String handleEventFormat() {
        return "You need to input event, followed by /from, then your start time, then /to, then "
                + "your end time!";
    }

    /**
     * Prints to console the list of tasks in current userTaskList
     */
    public String handleListingTask(TaskList taskList) {
        return "Here are your tasks!\n"
                + taskList.toString()
                + "\nYou have " + taskList.numOfTasks() + " tasks!";
    }

    /**
     * Prints to console when marking is successful.
     *
     * @param taskList Current useTaskList.
     * @param posTask Postion of task user wants to remove.
     */
    public String handleSuccesfulMarking(TaskList taskList, int posTask) {
        return "Nice! I've marked\n"
                + taskList.getTask(posTask).toString()
                + " as completed";
    }

    /**
     * Prints to console when user inputs wrong format for mark.
     */
    public String handleMarkingFormat() {
        return "You need to input a single space, followed by a number after mark";
    }

    /**
     * Prints to console when task is unmarked successfully, along with the task deleted.
     */
    public String handleSuccesfulUnmarking(TaskList taskList, int posTask) {
        return "Okay, so you lied! I've marked\n"
                + taskList.getTask(posTask).toString()
                + " as uncompleted";
    }

    /**
     * Prints to console when unmarking format is wrong.
     */
    public String handleUnmarkingFormat() {
        return "You need to input a single space, followed by a number after unmark"
                + "!";
    }

    /**
     * Prints to console when either mark or unmark is of correct format but is acting on a number that doesn't exist
     * in the userTaskList.
     */
    public String handleInvalidNum() {
       return "You need to input a valid number that exists in your TaskList!";
    }

    /**
     * Prints to console when task is successfully added to userTaskList.
     */
    public String handleSuccessfulAdd(Task task) {
        return "Ok! I've added " + task.toString();
    }

    /**
     * Prints to console when user successfully removes a task, as well as the task deleted.
     */
    public String handleRemoveTask(Task task) {
        return "Ok! I've removed " + task;
    }

    /**
     * Prints to console when wrong format of remove command is used.
     */
    public String handleRemoveFormat() {
        return "You need to input a single space, followed by a number after remove";
    }

    /**
     * Prints to console depending on the result of find command. If empty, WansBot will tell user that no tasks
     * fall on the date. If not then WansBot will tell the user exactly which events lie on date.
     */
    public String handleFindFiles(TaskList taskList, String date) {
        if (taskList.numOfTasks() != 0) {
            return "Here are your tasks on " + LocalDate.parse(date)
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n"
                    + taskList.toString()
                    + "\nYou have " + taskList.numOfTasks() + " tasks " + "on "
                    + LocalDate.parse(date)
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return "You have no tasks on "
                    + LocalDate.parse(date)
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + "!";
        }
    }

    /**
     * Prints to console when IOException occurs when user tries to save tasks to data folder.
     */
    public String handleIoExceptionSave() {
        return "I can't seem to save your tasks!";
    }

    /**
     * Prints success on successful save
     */
    public String handleSuccessfulSave(TaskList taskList) {
        return taskList + "\nI have saved these tasks!";
    }

    /**
     * Prints to user that save was unsuccessful
     */
    public String handleUnsuccessfulSave(TaskList taskList) {
        return "You have no tasks to save!";
    }

    /**
     * Prints that user successfully loads tasklist
     */
    public String handleSuccessfulLoad(TaskList taskList) {
        return taskList + "\nYou have loaded these tasks!";
    }
    /**
     * Prints to console when user encounters IOException when trying to load files using the load command
     */
    public String handleIoExceptionLoad() {
         return "I can't seem to load your tasks!";
    }

    /**
     * Prints to console when user does not currently have data folder which holds taskList.txt
     */
    public String handleFileNotFound() {
        return "You don't have files to load!";
    }

    /**
     * Prints to console when WansBot finds tasks whose name contain the keyword.
     */
    public String handleFindKeyword(TaskList tasklist) {
        if (tasklist.numOfTasks() == 0) {
            return "You have no matching tasks!!";
        } else {
           return "Here are your matching tasks!\n"
                    + tasklist.toString()
                    + "\nYou have " + tasklist.numOfTasks() + " tasks!";
        }
    }

    public String handleLearnQuestions() {
        return "I have successfully learnt how to answer your question!";
    }

    public String handleWrongQuestionFormat() {
        return "You need to input question followed by your question and then an answer after the ?";
    }

    /**
     * Prints to console Goodbye message when user inputs bye
     */
    public String handleGoodbye() {
        String exit = "|  _ \\ \\   / /  ____|"
                + "\n| |_) \\ \\_/ /| |__"
                + "\n|  _ < \\   / |  __|"
                + "\n| |_) | | |  | |____"
                + "\n|____/  |_|  |______";
        return "I'll miss you :( (I really wanna go home)\n";
    }
}
