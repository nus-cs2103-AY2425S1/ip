import java.util.Scanner;

public class Fanny {

    private static TaskList list = new TaskList();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        printHello();

        while (true) {
            System.out.print("User:");
            String cmd = scanner.nextLine();
            String[] cmdParts = cmd.split(" ", 2);
            String action = cmdParts[0];
            try {
                switch (action) {
                    case "bye":
                        exitChat();
                        return;
                    case "list":
                        printList();
                        break;
                    case "mark":
                        markTask(cmdParts);
                        break;
                    case "unmark":
                        unmarkTask(cmdParts);
                        break;
                    case "todo":
                        addToDoTask(cmdParts);
                        break;
                    case "deadline":
                        addDeadlineTask(cmdParts);
                        break;
                    case "event":
                        addEventTask(cmdParts);
                        break;
                    case "delete":
                        removeTask(cmdParts);
                        break;
                    default:
                        addUnknownTask(cmd);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {}
        }
    }

    private static String horizontalLine = "_____________________________________________";

    private static void printHello() {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Fanny\nWhat can I do for you?");
        System.out.println(horizontalLine);
    }

    private static void exitChat() {
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
        scanner.close();
    }

    private static void printList() {
        System.out.println(horizontalLine);
        System.out.println("Fanny:");
        list.printList();
        System.out.println(horizontalLine);
    }

    private static void markTask(String[] cmdParts) {
        System.out.println(horizontalLine);
        int taskId = Integer.parseInt(cmdParts[1]);
        System.out.println("Fanny:\nNice! I've marked this task as done:");
        System.out.println(list.markAsDone(taskId - 1));
        System.out.println(horizontalLine);
    }

    private static void unmarkTask(String[] cmdParts) {
        System.out.println(horizontalLine);
        int taskId = Integer.parseInt(cmdParts[1]);
        System.out.println("Fanny:\nOK, I've marked this task as not done yet:");
        System.out.println(list.markAsNotDone(taskId - 1));
        System.out.println(horizontalLine);
    }

    private static void addToDoTask(String[] cmdParts) {
        System.out.println(horizontalLine);
        try {
            Task todo = new ToDo(cmdParts[1]);
            list.add(todo);
            System.out.println("Fanny:\nGot it. I've added this task:");
            System.out.println(todo.toString());
            System.out.println("Now you have " + list.getLength() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Task description cannot be empty");
        } finally {}
        System.out.println(horizontalLine);
    }

    private static void addDeadlineTask(String[] cmdParts) {
        System.out.println(horizontalLine);
        try {
            String[] cmdDeadline = cmdParts[1].split("/by ", 2);
            String time = cmdDeadline[1];
            String description = cmdDeadline[0];
            Task deadline = new Deadline(description, time);
            list.add(deadline);
            System.out.println("Fanny:\nGot it. I've added this task:");
            System.out.println(deadline.toString());
            System.out.println("Now you have " + list.getLength() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Task description and deadline cannot be empty");
        } finally {}
        System.out.println(horizontalLine);
    }

    private static void addEventTask(String[] cmdParts) {
        System.out.println(horizontalLine);
        try {
            String[] cmdEvent = cmdParts[1].split("/from ", 2);
            String description = cmdEvent[0];
            String[] duration = cmdEvent[1].split("/to ", 2);
            String from = duration[0];
            String to = duration[1];
            Task event = new Event(description, from, to);
            list.add(event);
            System.out.println("Fanny:\nGot it. I've added this task:");
            System.out.println(event.toString());
            System.out.println("Now you have " + list.getLength() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Event description and duration cannot be empty");
        } finally {}
        System.out.println(horizontalLine);
    }

    private static void removeTask(String[] cmdParts) {
        System.out.println(horizontalLine);
        int taskId = Integer.parseInt(cmdParts[1]);
        System.out.println("Fanny:\nNoted. I've removed this task:");
        System.out.println(list.delete(taskId));
        System.out.println("Now you have " + list.getLength() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    private static void addUnknownTask(String cmd) throws IllegalArgumentException{
        throw new IllegalArgumentException("Sorry, I'm confused! Please try again!");
    }
}

