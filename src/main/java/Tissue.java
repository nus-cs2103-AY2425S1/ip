import java.util.Scanner;
import java.util.ArrayList;

public class Tissue {
    private static ArrayList<Task> textArray = new ArrayList<>();
    private static final String LINE =
            "--------------------------------------------------------------";
    private static final String INDENT = "       ";

    private static Scanner scanner;

    public static void main(String[] args) {
        chatFunction();
    }

    private static void chatFunction() {
        System.out.println(LINE);
        scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Tissue");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        String in = scanner.next();

        while (!in.equals("bye")) {
            System.out.println(LINE);

            if (in.equals("list")) {
                System.out.println(INDENT + "Here are the tasks in your list:");
                System.out.println(listTask());

            } else if (in.equals("mark")) {

                Task task = textArray.get(scanner.nextInt() - 1).markTask();
                System.out.println(INDENT + "Nice! I've marked this task as done:");
                System.out.println(INDENT + "  " + task);

            } else if (in.equals("unmark")) {

                Task task = textArray.get(scanner.nextInt() - 1).unmarkTask();
                System.out.println(INDENT + "OK, I've marked this task as not done yet:");
                System.out.println(INDENT + "  " + task);

            } else {
                storeTask(in);
            }

            System.out.println(LINE);
            in = scanner.next();
        }

        scanner.close();

        System.out.println(LINE);
        System.out.print(INDENT);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void storeTask(String in) {
        if (in.equals("todo")) {

            String item = scanner.nextLine();
            Task task = new ToDo(false, item);
            textArray.add(task);
            System.out.println(INDENT + "Got it. I've added this task:");
            System.out.println(INDENT + "  " + task);
            System.out.println(INDENT + "Now you have " + textArray.size() + " tasks in the list.");


        } else if (in.equals("deadline")) {
            String item = scanUntil("/by");
            String by = scanner.nextLine().strip();
            Task task = new Deadline(false, item, by);
            textArray.add(task);
            System.out.println(INDENT + "Got it. I've added this task:");
            System.out.println(INDENT + "  " + task);
            System.out.println(INDENT + "Now you have " + textArray.size() + " tasks in the list.");

        } else if (in.equals("event")) {
            String item = scanUntil("/from");
            String from = scanUntil("/to");
            String to = scanner.nextLine().strip();
            Task task = new Event(false, item, from, to);
            textArray.add(task);
            System.out.println(INDENT + "Got it. I've added this task:");
            System.out.println(INDENT + "  " + task);
            System.out.println(INDENT + "Now you have " + textArray.size() + " tasks in the list.");
        } else {
            String item = in + scanner.nextLine();
            System.out.println(INDENT + "added: " + item);
            textArray.add(new Task(false, item));
        }
    }

    private static String scanUntil(String pattern) {
        String item = "";
        String temp = scanner.next();
        while (!temp.equals(pattern)) {
            item += temp + " ";
            temp = scanner.next();
        }
        return item;
    }

    private static String listTask() {
        String parsedText = "";
        for (int i = 0; i < textArray.size(); i++) {
            Task task = textArray.get(i);
            parsedText += INDENT + String.valueOf(i + 1) + "." + " " + task + "\n";
        }
        return parsedText;
    }
}
