package ponderpika.ui;

/**
 * class helps in display of user interface
 */
public class Ui {

    /**
     * Prints a greeting message to the user with the bot's name.
     */
    public void greet() {
        String logo = "ponderpika";

        System.out.println("----------------------------------------------------------");
        System.out.println("Hello I'm " + logo);
        System.out.println("\nIt is a great day to ponder! How may I help you?");
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void bidBye() {
        System.out.println("\nBye! See you real soon!");
    }
}
