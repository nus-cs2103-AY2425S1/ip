package sigma;

import sigma.exception.SigmaException;

import java.util.Scanner;

public class Ui {

    private Scanner input = new Scanner(System.in);

    public String readCommand() {
        return input.nextLine();
    }

    public static void welcome() {
        print("Hello! I'm SIGMA! \nLooking forward to slaying with you!\nWhat do you need today?" +
                "\nYou can add tasks with \"todo\", \"deadline\", \"event\" or view tasks with \"list\"." +
                "\nYou can also mark tasks as done with \"mark\" or \"unmark\" them." +
                "\nYou can also delete tasks with \"delete\"." +
                "\nIf you're done, just type \"bye\" to exit.");
    }

    public static void exit() {
        print("What the sigma? You're leaving so soon? Bye chat, see you again!");
    }

    public static void print(String message) {
        line();
        System.out.println(message);
        line();
    }

    private static void line() {
        String line = "----------------------------------------------------------------------------------------------";
        System.out.println(line);
    }

    public static void throwUnrecognisedError() throws SigmaException {
        throw new SigmaException("What the sigma? I don't understand! Try again! Enter " +
                "\"todo\", \"deadline\", \"event\", \"list\", \"mark\", \"unmark\" or \"bye\"!");
    }

    public static void throwError(String message) throws SigmaException {
        throw new SigmaException(message);
    }

}
