package tayoo;

import java.util.Scanner;

/** The Ui class will deal with interactions with users, including input, output */
public class Ui {

    private final String chatbotName;

    public Ui(String name) {
        this.chatbotName = name;
    }

    public String getExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String getWelcomeMessage() {
        return "Hello! I'm " + this.chatbotName + "\nAt your service! O7";
    }

    /** Prints out the welcome default message of the chatbot. */
    public void showWelcome() {
        printText(getWelcomeMessage());
    }

    /** Prints out the standard chatbot message when exiting the bot */
    public void showExit() {
        printText(getExitMessage());
    }

    private void showLine() {
        System.out.println("\t_______________________________________________________________________");
    }

    /**
     * Prints out the text provided to it in a standardised "segment". Automatically include the linebreak, showLine()
     * and newline
     *
     * @param text String to be printed out by the chatbot
     */
    public void printText(String text) {
        System.out.println("\n");
        System.out.println(text);
        showLine();
    }

    /**
     * Prints out the error provided to it in a standardised "segment". Automatically include the linebreak, showLine()
     * and newline
     *
     * @param text String to be printed out by the chatbot
     */
    public void printError(String text) {
        System.out.println("Error! \n");
        System.out.println(text);
        showLine();
    }

    /**
     * Waits for the user's input then returns the whole input as a string
     * @return The user input as a string
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine().trim();
    }

}
