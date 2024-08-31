package botty.ui;

import java.util.Scanner;

/**
 * Handles all interaction with the user
 */
public class UI {
    // The Botty logo to be displayed at the start
    private static final String LOGO = " ________  ________  _________  _________    ___    ___                 \n"
            + "|\\   __  \\|\\   __  \\|\\___   ___\\\\___   ___\\ |\\  \\  /  /|                \n"
            + "\\ \\  \\|\\ /\\ \\  \\|\\  \\|___ \\  \\_\\|___ \\  \\_| \\ \\  \\/  / /                \n"
            + " \\ \\   __  \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\   \\ \\    / /                 \n"
            + "  \\ \\  \\|\\  \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\   \\/  /  /                  \n"
            + "   \\ \\_______\\ \\_______\\   \\ \\__\\     \\ \\__\\__/  / /                    \n"
            + "    \\|_______|\\|_______|    \\|__|      \\|__|\\___/ /                     \n"
            + "                                           \\|___|/                      \n"
            + "                                                                        \n"
            + " _________  ___  ___  _______           ________  ________  _________   \n"
            + "|\\___   ___\\\\  \\|\\  \\|\\  ___ \\         |\\   __  \\|\\   __  \\|\\___   ___\\ \n"
            + "\\|___ \\  \\_\\ \\  \\\\\\  \\ \\   __/|        \\ \\  \\|\\ /\\ \\  \\|\\  \\|___ \\  \\_| \n"
            + "     \\ \\  \\ \\ \\   __  \\ \\  \\_|/__       \\ \\   __  \\ \\  \\\\\\  \\   \\ \\  \\  \n"
            + "      \\ \\  \\ \\ \\  \\ \\  \\ \\  \\_|\\ \\       \\ \\  \\|\\  \\ \\  \\\\\\  \\   \\ \\  \\ \n"
            + "       \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\       \\ \\_______\\ \\_______\\   \\ \\__\\\n"
            + "        \\|__|  \\|__|\\|__|\\|_______|        \\|_______|\\|_______|    \\|__|";
    // The prefix to be displayed with every message
    private static final String PREFIX = "Botty: ";
    // The indentation to be used to messages longer than one line
    private static final String INDENTATION = "       ";
    // The scanner to receive user input
    private final Scanner inputScanner;

    /**
     * Constructs a {@code UI} instance, initializing the scanner
     */
    public UI() {
        inputScanner = new Scanner(System.in);
    }

    /**
     * Returns the user input
     */
    public String getUserInput() {
        return inputScanner.nextLine();
    }

    /**
     * Prints the introduction
     */
    public void displayIntroduction() {
        System.out.println(LOGO);
        System.out.println();

        reply("Hello, I am Botty the Bot, how may I be of service today?");
    }

    /**
     * Prints the given content
     * @param content the content to be printed
     */
    public void reply(String content) {
        String[] strings = content.split("\n");
        System.out.println(PREFIX + strings[0]);
        for (int i = 1; i < strings.length; i++) {
            System.out.println(INDENTATION + strings[i]);
        }
    }
}
