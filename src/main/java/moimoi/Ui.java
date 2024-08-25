package moimoi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import moimoi.exception.MoiMoiException;
import moimoi.task.Task;

/**
 * Represents the user interface.
 */
public class Ui {

    private static final String LOGO = "               __\n"
            + "              / >)\n"
            + "     _.----._/ /\n"
            + "    /         /\n"
            + " __/ (  | (  |\n"
            + "/__.-'|_|--|_|\n";
    private static final String HEADER_MOIMOI = "\n_._._MoiMoi_._._";
    private static final String HEADER_USER = "_._._User_._._";
    private Scanner sc;

    /**
     * Constructs the user interface.
     */
    public Ui(){
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays header for program output.
     */
    public void showMoiMoiHeader() {
        print(Ui.HEADER_MOIMOI);
    }

    /**
     * Displays header for user input.
     */
    public void showUserHeader() {
        print(Ui.HEADER_USER);
    }

    /**
     * Displays greeting upon program startup.
     */
    public void showGreeting() {
        print(Ui.LOGO + Ui.HEADER_MOIMOI + "\nHello, master! How may I help you today? ><" + "\n");
    }

    /**
     * Displays the exit message.
     */
    public void showExitMessage() {
        print("I'll always be here for you~ See ya, master! ^^" + "\n");
    }

    /**
     * Displays error message encapsulated by the specified exception.
     *
     * @param e Exception that encapsulates an error message to be displayed.
     */
    public void showMoiMoiException(MoiMoiException e) {
        print(e.getMessage() + "\n");
    }

    /**
     * Displays the specified completion message of a command.
     *
     * @param message Completion message to be displayed.
     */
    public void showCompletionMessage(String message) {
        print(message + "\n");
    }

    /**
     * Displays all tasks from the given task list.
     *
     * @param tasks List of tasks to be displayed.
     */
    public void showList(TaskList tasks) {

        boolean isEmpty = true;

        print("Here's your list of tasks!");
        for (int i = 1; i <= tasks.size(); i = i + 1) {
            isEmpty = false;
            Task task = tasks.get(i);
            print(i + ". " + task.stringUI());
        }

        if (isEmpty) {
            print("(No tasks found...)");
        }

        print("");

    }

    /**
     * Displays all tasks occurring on the specified date, from the given task list.
     *
     * @param tasks List of tasks to be filtered.
     * @param date Date to be checked against tasks' scheduled dates.
     */
    public void showList(TaskList tasks, LocalDate date) {

        boolean isEmpty = true;

        print("Here's your list of tasks, occurring on "
                + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "!");
        for (int i = 1; i <= tasks.size(); i = i + 1) {
            Task task = tasks.get(i);
            if (task.occursOn(date)) {
                print(i + ". " + task.stringUI());
            }
        }

        if (isEmpty) {
            print("(No such tasks found...)");
        }

        print("");

    }

    /**
     * Displays all tasks containing the specified keyword in their descriptions, from the given task list.
     *
     * @param tasks List of tasks to be filtered.
     * @param keyword Keyword to be checked within tasks' descriptions.
     */
    public void showList(TaskList tasks, String keyword) {

        boolean isEmpty = true;

        print("Here's your list of tasks, containing '" + keyword + "' in their descriptions!");
        for (int i = 1; i <= tasks.size(); i = i + 1) {
            Task task = tasks.get(i);
            if (task.hasKeyword(keyword)) {
                isEmpty = false;
                print(i + ". " + task.stringUI());
            }
        }

        if (isEmpty) {
            print("(No such tasks found...)");
        }

        print("");

    }

    /**
     * Retrieves next line of user input.
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Displays specified String, followed by a new line.
     *
     * @param string String to be displayed.
     */
    private void print(String string) {
        System.out.println(string);
    }

}
