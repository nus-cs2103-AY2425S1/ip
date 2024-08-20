package main.java;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CasperBot {
    private static List<String> list = new ArrayList<>();
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
            if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, list.get(i));
                }
                line();
            }
            else if (!input.equalsIgnoreCase("bye")) {
                System.out.println("added: " + input);
                list.add(input);
                line();
            }
        }
        scanner.close();
    }
}
