package main.java;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CasperBot {
    private static List<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        line();
        System.out.println("Hello! I'm CasperBot.\n" +
                "What can I do for you?");
        line();
        echo();
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
            } else if (isMatch(input, "mark \\d+")) {
                String[] parts = input.split(" ");
                Task task = list.get(Integer.parseInt(parts[1])-1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            } else if (isMatch(input, "unmark \\d+")) {
                String[] parts = input.split(" ");
                Task task = list.get(Integer.parseInt(parts[1])-1);
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
            }
            else if (!input.equalsIgnoreCase("bye")) {
                System.out.println("added: " + input);
                list.add(new Task(input));
            } else {
                System.out.println("Bye. Hope to see you again soon!");
            }
            line();
        }
        scanner.close();
    }

    private static boolean isMatch(String input, String regex) {
        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(input);
        String[] parts = input.split(" ");
        return matcher.matches() && Integer.parseInt(parts[1]) <= list.size() && Integer.parseInt(parts[1]) > 0;
    }
}
