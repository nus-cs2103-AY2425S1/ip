package chatbot;

import java.util.Scanner;

public class Bee {
    //Print out "Bee" logo in ASCII art
    private static final String logo = " ____\n"
            + "|  _ \\  ___   ___ \n"
            + "| |_/  / _ \\ / _ \\\n"
            + "| |_\\ |  __/|  __/ \n"
            + "|____/ \\___| \\___|\n";

    //Format standard output
    public static void printBtnLines(String content) {
        System.out.println("    _________________________________________");
        System.out.println(String.format("     %s", content));
        System.out.println("    _________________________________________\n");
    }

    public static void main(String[] args) {
        //Welcome user
        System.out.println(logo);
        printBtnLines("Hello, I'm Bee! What can I do for you?");

        //Scan for user input
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String next = sc.nextLine();

            //Exit program if user inputs bye
            if (next.equalsIgnoreCase("bye")) {
                printBtnLines("Bye~ Hope to see you again soon!");
                break;

            //Prompt user for input if no input
            } else if (next.isEmpty()){
                printBtnLines("Hey! Say something.");

            //Echo user input
            } else {
                printBtnLines(String.format("I heard: \"%s\"", next));
            }
        }
    }
}
