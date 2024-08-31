package carly.ui;

import java.util.Scanner;

public class Ui {
    private String username;

    public Ui(String username) {
        this.username = username;
    }

    /** Displays a welcome message to the user with the current username. */
    public void welcomeMsg() {
        System.out.println("Hey " + username + "! I'm Carly.\nWhat can I do for you?");
    }

    /** Displays a farewell message to the user with the current username. */
    public void byeMsg() {
        System.out.println("Bye " + username + ". I'll see you next time!");
    }

    public String ReadInput() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) {
            return scan.nextLine();
        } else {
            return "No input detected. Exiting...";
        }
    }
}

