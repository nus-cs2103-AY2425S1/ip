package moimoi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import moimoi.exception.MoiMoiException;
import moimoi.task.Task;

public class Ui {

    private static final String LOGO = "               __\n" +
            "              / >)\n" +
            "     _.----._/ /\n" +
            "    /         /\n" +
            " __/ (  | (  |\n" +
            "/__.-'|_|--|_|\n";
    private static final String MOI_MOI_HEADER = "\n_._._MoiMoi_._._";
    private static final String USER_HEADER = "_._._User_._._";
    private Scanner sc;

    public Ui(){
        this.sc = new Scanner(System.in);
    }

    public void showMoiMoiHeader() {
        print(Ui.MOI_MOI_HEADER);
    }

    public void showUserHeader() {
        print(Ui.USER_HEADER);
    }

    public void showGreeting() {
        print(Ui.LOGO + Ui.MOI_MOI_HEADER + "\nHello, master! How may I help you today? ><" + "\n");
    }

    public void showExitMessage() {
        print("I'll always be here for you~ See ya, master! ^^" + "\n");
    }

    public void showMoiMoiException(MoiMoiException e) {
        print(e.getMessage() + "\n");
    }

    public void showCompletionMessage(String message) {
        print(message + "\n");
    }

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

    public void showList(TaskList tasks, LocalDate date) {

        boolean isEmpty = true;

        print("Here's your list of tasks, occurring on "
                + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "!");
        for (int i = 1; i <= tasks.size(); i = i + 1) {
            Task task = tasks.get(i);
            if (task.occurringOn(date)) {
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

    public String getInput() {
        return sc.nextLine();
    }

    private void print(String string) {
        System.out.println(string);
    }

}
