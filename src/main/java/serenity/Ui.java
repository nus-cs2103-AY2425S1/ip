package serenity;

import java.util.Scanner;

public class Ui {

    private static final String HORIZONTAL_LINE = "__________________________________________";
    protected Scanner sc;

    public Ui () {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hi, I'm Serenity!\n" + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showGoodbye() {
        this.sc.close();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Goodbye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error: File cannot be loaded.");
    }

    public void showMessage(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(tasks.toString());
        System.out.println(HORIZONTAL_LINE);
    }
}
