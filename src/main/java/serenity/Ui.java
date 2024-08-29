package serenity;

import serenity.TaskList;

import java.util.Scanner;

public class Ui {

    private static final String horizontalLine = "__________________________________________";

    protected Scanner sc;

    public Ui () {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(horizontalLine);
        System.out.println("Hi, I'm Serenity!\n" + "What can I do for you?");
        System.out.println(horizontalLine);
    }

    public void showGoodbye() {
        this.sc.close();
        System.out.println(horizontalLine);
        System.out.println("Goodbye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error: File cannot be loaded.");
    }

    public void showMessage(String message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(horizontalLine);
        System.out.println(tasks.toString());
        System.out.println(horizontalLine);
    }




}
