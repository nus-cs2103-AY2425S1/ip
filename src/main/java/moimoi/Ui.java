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
            "/__.-'|_|--|_|\n\n";
    private static final String MOI_MOI_HEADER = "⋆⭒˚.⋆MoiMoi⋆⭒˚.⋆";
    private static final String USER_HEADER = "⋆⭒˚.⋆User⋆⭒˚.⋆\n";
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
        print("Here's your list of tasks!");
        for (int i = 1; i <= tasks.size(); i = i + 1) {
            Task task = tasks.get(i);
            print(i + ". " + task.stringUI());
        }
        print("");
    }

    public void showList(TaskList tasks, LocalDate date) {
        print("Here's your list of tasks, occurring on "
                + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "!");
        for (int i = 1; i <= tasks.size(); i = i + 1) {
            Task task = tasks.get(i);
            if (task.occurringOn(date)) {
                print(i + ". " + task.stringUI());
            }
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
