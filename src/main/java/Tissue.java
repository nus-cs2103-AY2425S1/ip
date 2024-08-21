import java.util.Scanner;
import java.util.ArrayList;

public class Tissue {
    private static ArrayList<Task> taskArray = new ArrayList<>();
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
                Task task = taskArray.get(scanner.nextInt() - 1).markTask();
                System.out.println(INDENT + "Nice! I've marked this task as done:");
                System.out.println(INDENT + "  " + task);
            } else if (in.equals("unmark")) {

                Task task = taskArray.get(scanner.nextInt() - 1).unmarkTask();
                System.out.println(INDENT + "OK, I've marked this task as not done yet:");
                System.out.println(INDENT + "  " + task);

            } else if (in.equals("delete")) {
                deleteTask(scanner.nextInt());
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


    private static void deleteTask(int number) {
        Task task = taskArray.remove(number - 1);
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + taskArray.size() + " tasks in the list.");
    }

    private static void storeTask(String in) {
        if (in.equals("todo")) {

            String item = scanner.nextLine();
            if (item.equals("")) {
                System.out.println("Decription of TODO cannot be empty.");
            } else {
                Task task = new ToDo(false, item);
                taskArray.add(task);
                System.out.println(INDENT + "Got it. I've added this task:");
                System.out.println(INDENT + "  " + task);
                System.out.println(
                        INDENT + "Now you have " + taskArray.size() + " tasks in the list.");
            }



        } else if (in.equals("deadline")) {
            String item = scanUntil("/by");
            String by = scanner.nextLine().strip();
            Task task = new Deadline(false, item, by);
            taskArray.add(task);
            System.out.println(INDENT + "Got it. I've added this task:");
            System.out.println(INDENT + "  " + task);
            System.out.println(INDENT + "Now you have " + taskArray.size() + " tasks in the list.");

        } else if (in.equals("event")) {
            String item = scanUntil("/from");
            String from = scanUntil("/to");
            String to = scanner.nextLine().strip();
            Task task = new Event(false, item, from, to);
            taskArray.add(task);
            System.out.println(INDENT + "Got it. I've added this task:");
            System.out.println(INDENT + "  " + task);
            System.out.println(INDENT + "Now you have " + taskArray.size() + " tasks in the list.");
        } else {

            System.out.println(
                    "Invalid input. Possible inputs are deadline, todo, event, list, mark, and unmark.");

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
        for (int i = 0; i < taskArray.size(); i++) {
            Task task = taskArray.get(i);
            parsedText += INDENT + String.valueOf(i + 1) + "." + " " + task + "\n";
        }
        return parsedText;
    }
}
