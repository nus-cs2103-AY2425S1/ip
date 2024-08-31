package ui;

import java.util.Scanner;

public class UI {

    private final Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public void init() {
        String logo = """
                ___________        .__     .___               \s
                \\_   _____/_______ |__|  __| _/_____    ___.__.
                 |    __)  \\_  __ \\|  | / __ | \\__  \\  <   |  |
                 |     \\    |  | \\/|  |/ /_/ |  / __ \\_ \\___  |
                 \\___  /    |__|   |__|\\____ | (____  / / ____|
                     \\/                     \\/      \\/  \\/    \s
                """;

        System.out.println("Loading........\n" + logo);
        printLine();
        System.out.println("""
                Hello! I'm Friday!
                What would you like to do?
                """);
    }

    public String[] getInput() {
        String input = this.sc.nextLine();
        printLine();
        return input.split(" ", 2);
    }

    public static void printLine() {
        System.out.println("----------------------------------------------");
    }

    public void terminate() {
        System.out.println("Friday > Bye! See you soon!");
        printLine();
    }

}
