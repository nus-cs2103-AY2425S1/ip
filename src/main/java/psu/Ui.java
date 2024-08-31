package psu;

import java.util.Scanner;

import command.Command;

public class Ui {
    private Scanner uinput;
    private Parser inputParser;

    public Ui() {
        this.uinput = new Scanner(System.in);
        this.inputParser = new Parser();
    }

    /**
     * Prints chatbot output to console with surrounding lines
     * 
     * @param output the output string
     */
    public void printBotOutputString(String output) {
        System.out.println("\t-----------------------------------------");
        System.out.println(output);
        System.out.println("\t-----------------------------------------");
    }

    /**
     * Welcomes the user with ASCII art and a greeting
     */
    public void welcomeUser() {
        String logo = "________                ___ \n"
                + "| _____|             ___| |___ \n"
                + "| |___  __   _   ___ |__   __|\n"
                + "| ___|  | |/ /  / _ \\   | |\n"
                + "| |     |   /  <  __/   | |__\n"
                + "|_|     |__|    \\___|   |___|\n";
        
        System.out.println("Initiating...\n" + logo);

        printBotOutputString("\tPersonal AI Fret, coming online!\n\tHey, how can I be of assistance?");
    }

    /**
     * Gets command-line input from user and
     * passes it to the parser for parsing
     * 
     * @return the Command object returned by the Parser
     */
    public Command processUserInput() {
        return this.inputParser.parse(this.uinput.nextLine());
    }

    /**
     * Closes the Scanner object at the end of execution to prevent leaks
     */
    public void closeUi() {
        this.uinput.close();
    }
}
