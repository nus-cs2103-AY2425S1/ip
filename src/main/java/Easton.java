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

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isFinished = true;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                printList();
            } else if (input.toLowerCase().startsWith("mark")) {
                changeTaskStatus(getTextFromInput(input),
                        true,
                        "Nice! I've marked this task as done:");
            } else if (input.toLowerCase().startsWith("unmark")) {
                changeTaskStatus(getTextFromInput(input),
                        false,
                        "OK, I've marked this task as not done yet:");
            } else if (input.toLowerCase().startsWith("todo")) {
                addTask(new ToDo(getTextFromInput(input)));
            } else if (input.toLowerCase().startsWith("deadline")) {
                String[] text = getTextFromInput(input).split(" /by ", 2);
                addTask(new Deadline(text[0], text[1]));

            } else if (input.toLowerCase().startsWith("event")) {
                String[] text = getTextFromInput(input).split(" /from ", 2);
                String[] timings = text[1].split(" /to ", 2);
                addTask(new Event(text[0], timings[0], timings[1]));
            } else {
                System.out.println(input);
            }

            printDivider();
        }
    }

    private static void printList() {
        for (int i = 0; i < taskArraySize; i ++) {
            System.out.println((i + 1) + ". " + taskArray[i]);
        }
    }

    private static void changeTaskStatus(String text, boolean isDone, String message) {
        try {
            int index = Integer.parseInt(text);
            if (0 < index && index <= taskArraySize) {
                Task task = taskArray[index - 1];
                task.setDone(isDone);
                System.out.println(message);
                System.out.println(task);
            } else {
                System.out.println(index + " is not a valid index!");
            }
        } catch (NumberFormatException e) {
            System.out.println(text + " is not an integer!");
        }
    }

    private static String getTextFromInput(String input) {
        return input.split(" ", 2)[1];
    }

    private static void addTask(Task task) {
        taskArray[taskArraySize] = task;
        taskArraySize++;
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskArraySize + " tasks in the list.");
    }

    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    private static String prompt(Scanner scanner) {
        return scanner.nextLine();
    }
}
