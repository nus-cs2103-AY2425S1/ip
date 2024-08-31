package wansbot.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import wansbot.tasks.Task;
import wansbot.tasks.TaskList;

public class UI {
    private static final String HR = "----------------------------------------------------------------------";

    public UI() {
    }

    // Method that abstracts Bot message when bot first starts
    public void introduceToUser() {
        String logo = "                 __"
                + "\n|  |  /\\  |\\ | /__` "
                + "\n|/\\| /~~\\ | \\| .__/\n";

        System.out.println(HR + "\nWans:\n"
                + "Hey, I'm\n"
                + logo
                + "\nCan I help? (I can only manage a todo list so...)\n" + HR);
    }

    // handles invalid commands
    public void handleInvalidCommands(String userInput) {
        System.out.println(HR + "\nWans:\n"
                + "You need to input something after " + userInput
                + "!\n" + HR);
    }


    // method that tells user that bot does not understand
    public void handleUnrecognisedInput(String userInput) {
        System.out.println(HR + "\nWans: \n"
                + "I'm sorry I'm not that useful I don't know what "
                + userInput + " means!!!" + "\n" + HR);
    }

    // handle when wrong date format is used
    public void handleDateTimeException() {
        System.out.println(HR + "\nWans:\n"
                + "Your date needs to be in the form YYYY-MM-DD! It needs to be a valid \ndate too please!"
                + "\n" + HR);
    }

    // handles wrong deadline format
    public void handleDeadlineFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input deadline, followed by /by, then the deadline!"
                + "\n" + HR);
    }

    // handles wrong event format
    public void handleEventFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input event, followed by /from, then your start time, then /to, then "
                + "your end time!"
                + "\n" + HR);
    }

    // lists task to user
    public void handleListingTask(TaskList taskList) {
        System.out.println(HR + "\nWans:"
                + "\nHere are your tasks!\n"
                + taskList.toString());
        System.out.println("You have " + taskList.numOfTasks() + " tasks!"+"\n"+HR);
    }

    //handle marking user task successfully
    public void handleSuccesfulMarking(TaskList taskList, int posTask) {
        System.out.println(HR + "\nWans:"
                + "\nNice! I've marked\n"
                + taskList.number(posTask).toString()
                + " as completed\n" + HR);
    }

    //handle marking command format
    public void handleMarkingFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input a single space, followed by a number after mark"
                + "!\n" + HR);
    }

    //handles succesful unmarking
    public void handleSuccesfulUnmarking(TaskList taskList, int posTask) {
        System.out.println(HR + "\nWans:"
                + "\nOkay, so you lied! I've marked\n"
                + taskList.number(posTask).toString()
                + " as uncompleted\n" + HR);
    }

    public void handleUnmarkingFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input a single space, followed by a number after unmark"
                + "!\n" + HR);
    }

    // handle invalid mark/unmark input
    public void handleInvalidNum() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input a valid number that exists in your TaskList!"
                + "\n" + HR);
    }

    //handles successful adding of tasks
    public void handleSuccessfulAdd(Task task) {
        System.out.println(HR + "\nWans:\n"
                + "Ok! I've added " + task.toString()
                + "\n" + HR);
    }

    // handles succesful removal of tasks
    public void handleRemoveTask(TaskList taskList, int posTask) {
        System.out.println(HR + "\nWans:\n"
                + "Ok! I've removed " + taskList.number(posTask)
                + "\n" + HR);
    }

    //handles format of remove
    public void handleRemoveFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input a single space, followed by a number after remove"
                + "!\n" + HR);
    }

    // handles finding files
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

    // handles ioexception for save tasks
    public void handleIoExceptionSave() {
        System.out.println(HR + "\nWans:\n"
                + "I can't seem to save your tasks!"
                + "\n" + HR);
    }

    // handles ioexception for load tasks
    public void handleIoExceptionLoad() {
        System.out.println(HR + "\nWans:\n"
                + "I can't seem to load your tasks!"
                + "\n" + HR);
    }

    //handles filenotfound exception
    public void handleFileNotFound() {
        System.out.println("\nWans:\n"
                + "You don't have files to load!"
                + "\n" + HR);
    }

    // says goodbye to user and exits the program
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
