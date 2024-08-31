package Ponder_Pika.Ui;

public class Ui {

    /**
     * Prints a greeting message to the user with the bot's name.
     */
    public void greet() {
        String logo = "Ponder_Pika";

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

    /**
     * Prints a divider line for better readability in the console output.
     */
    public void printDivider() {
        System.out.println("...........................................................");
    }
}
