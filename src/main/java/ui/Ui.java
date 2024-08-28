package ui;

import data.Task;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String logo = "";
    private static final String hr = "____________________________________________________________" ;

    public void displayWelcome() {
        this.displayLine();
        this.displayString("Hello! I'm Llama!");
        this.displayString(logo);
        this.displayString("What can I do for you?");
    }

    public void displayBye() {
        displayString("Baaaaaa byeeee. Come baaaaack soon!");
    }

    public void displayString(String str) {
        System.out.println("\t" + str);
    }

    public void displayTask(int num, Task task) {
        System.out.println("\t" + num + ". " + task);
    }

    public void displayLine() {
        this.displayString(hr);
    }

    public String getUserInput(Scanner sc) {
        this.displayLine();
        String input = sc.nextLine();
        this.displayLine();
        return input;
    }
}
