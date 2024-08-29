package yapbot.util;

import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "\n-----------------------------------------------\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcomeUser() {
        System.out.println(SEPARATOR
                + "Powering up...System booted successfully.\nYapBot online.\nExtensive damage detected."
                + "\nCore Systems 28% functional."
                + SEPARATOR);
    }

    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        this.scanner.close();
        System.out.println(SEPARATOR + "Shutting down...\nYapBot process terminated." + SEPARATOR);
    }
}
