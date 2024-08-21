import java.util.Scanner;

public class Easton {

    public static final String CHATBOT_NAME = "Easton";
    public static Task[] taskArray = new Task[100];
    public static int taskArraySize = 0;

    public static void main(String[] args) {
        String logo = " _______  _______  _______  _______  _______  __    _ \n"
                + "|       ||   _   ||       ||       ||       ||  |  | |\n"
                + "|    ___||  |_|  ||  _____||_     _||   _   ||   |_| |\n"
                + "|   |___ |       || |_____   |   |  |  | |  ||       |\n"
                + "|    ___||       ||_____  |  |   |  |  |_|  ||  _    |\n"
                + "|   |___ |   _   | _____| |  |   |  |       || | |   |\n"
                + "|_______||__| |__||_______|  |___|  |_______||_|  |__|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        printDivider();

        Scanner scanner = new Scanner(System.in);
        boolean isFinished = false;
        String input;

        while (!isFinished) {
            input = prompt(scanner);
            printDivider();
            String[] splitString = input.split(" ");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isFinished = true;
            } else if (input.equalsIgnoreCase("list")) {
                printList();
            } else if (splitString[0].equalsIgnoreCase("mark") && splitString.length == 2) {
                changeTaskStatus(splitString, true, "Nice! I've marked this task as done:");
            } else if (splitString[0].equalsIgnoreCase("unmark") && splitString.length == 2) {
                changeTaskStatus(splitString, false, "OK, I've marked this task as not done yet:");
            } else {
                taskArray[taskArraySize] = new Task(input);
                taskArraySize++;
                System.out.println("added: " + input);
            }

            printDivider();
        }
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskArraySize; i ++) {
            System.out.println((i + 1) + ". " + taskArray[i]);
        }
    }

    private static void changeTaskStatus(String[] splitString, boolean isDone, String message) {
        try {
            int index = Integer.parseInt(splitString[1]);
            if (0 < index && index <= taskArraySize) {
                Task task = taskArray[index - 1];
                task.setDone(isDone);
                System.out.println(message);
                System.out.println(task);
            } else {
                System.out.println(index + " is not a valid index!");
            }
        } catch (NumberFormatException e) {
            System.out.println(splitString[1] + " is not an integer!");
        }
    }

    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    private static String prompt(Scanner scanner) {
        return scanner.nextLine();
    }
}
