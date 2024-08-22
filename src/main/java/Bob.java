import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    private static final String LINE = "____________________________________________________________";
    private static final String NAME = "Bob";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(LINE);
        System.out.printf("Hello! I'm %s!\n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        String phrase = scanner.nextLine();

        while (!phrase.equals("bye")) {
            try {
                System.out.println(LINE);
                handleInput(phrase);
            } catch (ChatBotException e) {
                System.out.println(" Error: " + e.getMessage());
            } finally {
                System.out.println(LINE);
                phrase = scanner.nextLine();
            }
        }

        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void handleInput(String phrase) throws ChatBotException {
        String[] tmp = phrase.split(" ");
        switch (tmp[0]) {
            case "list":
                Bob.listTasks();
                break;
            case "mark":
                Bob.markTask(tmp);
                break;
            case "unmark":
                Bob.unmarkTask(tmp);
                break;
            case "todo":
                Bob.addToDo(phrase);
                break;
            case "deadline":
                Bob.addDeadline(phrase);
                break;
            case "event":
                Bob.addEvent(phrase);
                break;
            case "delete":
                Bob.deleteTask(tmp);
                break;
            default:
                throw new ChatBotException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Bob.tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, Bob.tasks.get(i));
        }
    }

    private static void markTask(String[] tmp) throws ChatBotException {
        try {
            int num = Integer.parseInt(tmp[1]) - 1;
            System.out.println(Bob.tasks.get(num).mark());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for marking.");
        }
    }

    private static void unmarkTask(String[] tmp) throws ChatBotException {
        try {
            int num = Integer.parseInt(tmp[1]) - 1;
            System.out.println(Bob.tasks.get(num).unmark());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for unmarking.");
        }
    }

    private static void deleteTask(String[] tmp) throws ChatBotException {
        try {
            int num = Integer.parseInt(tmp[1]) - 1;
            System.out.println(Bob.tasks.get(num).delete());
            Bob.tasks.remove(num);
            System.out.printf("Now you have %d tasks in the list.\n", Bob.tasks.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for deleting.");
        }
    }

    private static void addToDo(String phrase) throws ChatBotException {
        if (phrase.length() <= 5) {
            throw new ChatBotException("The description of a todo cannot be empty.");
        }
        String description = phrase.substring(5);
        Bob.tasks.add(new ToDo(description));
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s\n", Bob.tasks.get(tasks.size() - 1));
        System.out.printf("Now you have %d tasks in the list.\n", Bob.tasks.size());
    }

    private static void addDeadline(String phrase) throws ChatBotException {
        try {
            String[] parts = phrase.split(" /by ");
            String description = parts[0].substring(9);
            String by = parts[1];
            Bob.tasks.add(new Deadline(description, by));
            System.out.println("Got it. I've added this task:");
            System.out.printf("  %s\n", Bob.tasks.get(Bob.tasks.size() - 1));
            System.out.printf("Now you have %d tasks in the list.\n", Bob.tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid format for deadline. Correct format: deadline <description> /by <date>");
        }
    }

    private static void addEvent(String phrase) throws ChatBotException {
        try {
            String[] parts = phrase.split(" /from | /to ");
            String description = parts[0].substring(6);
            String from = parts[1];
            String to = parts[2];
            Bob.tasks.add(new Event(description, from, to));
            System.out.println("Got it. I've added this task:");
            System.out.printf("  %s\n", Bob.tasks.get(tasks.size() - 1));
            System.out.printf("Now you have %d tasks in the list.\n", Bob.tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid format for event. Correct format: event <description> /from <start> /to <end>");
        }
    }
}