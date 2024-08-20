package main.java;
import java.util.Objects;
import java.util.Scanner;

public class CasperBot {
    public static void main(String[] args) {
        line();
        System.out.println("Hello! I'm CasperBot.\n" +
                "What can I do for you?");
        line();
        echo();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void line() {
        System.out.println("------------------------------------------");
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = scanner.nextLine();
            line();
            if (!input.equalsIgnoreCase("bye")) {
                System.out.println(input);
                line();
            }
        }
        scanner.close();
    }
}
