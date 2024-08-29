package Main;

import java.util.Scanner;

public class Ui {
    //Horizontal line for better readability
    public static String LINE = "    __________________________________________";
    /* create an instance of scanner class to read userinput */
    private Scanner scanner;

    public Ui () {
        //initliaze an instance of scanner class
        scanner = new Scanner(System.in);
    }


    /**
     * Prints the welcome message upon the start of the application.
     */
    public void showIntroMessage() {
        // print intro
        System.out.println(LINE);
        System.out.println("    Hey! I'm Main.Flash");
        System.out.println("    What can I do for ya?");
        System.out.println(LINE);

    }

    /**
     * Prints the exit message of the application.
     */
    public void showExitMessage() {
        //if user inputs bye, exit
        System.out.println("    Bye. Hope to see ya again soon!");
        System.out.println(LINE);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * @return userInput (full line) entered by the user
     */
    public String getUserInput() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    // close scanner
    public void closeScanner() {
        scanner.close();
    }
}

