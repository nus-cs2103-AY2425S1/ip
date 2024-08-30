package WindeBot;

import java.util.Scanner;

public class Ui {
    private static Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void greet() {
        System.out.println("__        __              _");
        System.out.println("\\ \\      / /(_) _ __   __| | ___ ");
        System.out.println(" \\ \\ /\\ / / | || '_  \\/ _` |/ _ \\ ");
        System.out.println("  \\ V  V /  | || | | || (_ || __/ ");
        System.out.println("   \\_/\\_/   |_||_| |_|\\__,_|\\___| ");
        System.out.println("Hello! I'm Winde\n" + "What can I do for you?");
    }

    public static void exit(History history, Reminder reminder) {
        history.save(reminder.getSchedule());
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String read() {
        return scanner.nextLine().trim();
    }

    public static boolean hasNextCommand() {
        return scanner.hasNextLine();
    }

    public static void showLoadingError() {
        System.out.println("Error Loading Tasks to File");
    }

    public static void showLine() {
        System.out.println("---------------------");
    }

    public static void print(String output) {
        System.out.println(output);
    }
}
