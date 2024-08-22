import java.util.Scanner;

public class Ratchet {
    private static final String INDENT = "    ";
    private static final Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("list")) {
                displayList();
            } else if (input.equalsIgnoreCase("bye")) {
                exit();
            } else if (input.toLowerCase().startsWith("mark")) {
                mark(input);
            } else if (input.toLowerCase().startsWith("unmark")) {
                unmark(input);
            } else {
                addList(input);
            }
        }
    }

    private static void lineBreak() {
        System.out.println("   ________________________________________________________");
    }

    private static void greet() {
        lineBreak();
        System.out.println(INDENT + "Hello! I'm Ratchet\n" +
                INDENT + "What can I do for you?");
        lineBreak();
    }

    private static void exit() {
        lineBreak();
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        lineBreak();
    }

    private static void addList(String text) {
        taskList[taskCount++] = new Task(text);
        lineBreak();
        System.out.println(INDENT + "added: " + text);
        lineBreak();
    }

    private static void displayList() {
        lineBreak();
        if (taskCount == 0) {
            System.out.println(INDENT + "You have no tasks currently!");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(INDENT + (i + 1) + "." + taskList[i]);
            }
        }
        lineBreak();
    }

    private static void mark(String input) {
        int task = Integer.parseInt(input.substring(5)) - 1;
        taskList[task].markAsDone();
        lineBreak();
        System.out.println(INDENT + "Nice! I've marked this task as done:\n"
                + INDENT + "  " + taskList[task]);
        lineBreak();
    }

    private static void unmark(String input) {
        int task = Integer.parseInt(input.substring(7)) - 1;
        taskList[task].markAsNotDone();
        lineBreak();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:\n"
                + INDENT + "  " + taskList[task]);
        lineBreak();
    }
}