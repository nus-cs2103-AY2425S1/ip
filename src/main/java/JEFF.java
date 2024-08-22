import java.util.ArrayList;
import java.util.Scanner;

public class JEFF {
    private static final String LINE = "--------------------------------------------";
    private static final Scanner sc = new Scanner(System.in); // Scanner object to detect user input
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        // Init sequence
        String logo =
                """
                                                                            ____._________________________________|
                          _____ ___.__.   ____ _____    _____   ____       |    |\\_   _____/\\_   _____/\\_   _____/|
                         /     <   |  |  /    \\\\__  \\  /     \\_/ __ \\      |    | |    __)_  |    __)   |    __)  |
                        |  Y Y  \\___  | |   |  \\/ __ \\|  Y Y  \\  ___/  /\\__|    | |        \\ |     \\    |     \\   |
                        |__|_|  / ____| |___|  (____  /__|_|  /\\___  > \\________|/_______  / \\___  /    \\___  /   |
                              \\/\\/           \\/     \\/      \\/     \\/                    \\/      \\/         \\/    |
                        """;

        System.out.println("Hello there\n" + logo);

        // Chat loop
        beginChat();
    }

    public static void beginChat() {
        System.out.println("What can I do for you?");
        boolean chatCont = true;
        while (chatCont) {
            chatCont = chatEvent();
        }
        exitChat();
    }

    private static boolean chatEvent() {
        System.out.println(LINE);
        String input = sc.nextLine().trim();
        String[] parts = input.split(" ", 2);
        try {
            switch (parts[0].toLowerCase()) {
                case "bye":
                    return false;
                case "list":
                    printList();
                    break;
                case "mark":
                    if (parts.length == 2 && isNumeric(parts[1])) {
                        markDone(Integer.parseInt(parts[1]));
                    } else {
                        throw new JEFFException("You must provide only one number after mark!");
                    }
                    break;
                case "unmark":
                    if (parts.length == 2 && isNumeric(parts[1])) {
                        markNotDone(Integer.parseInt(parts[1]));
                    } else {
                        throw new JEFFException("You must provide only one number after unmark!");
                    }
                    break;
                case "delete":
                    if (parts.length == 2 && isNumeric(parts[1])) {
                        deleteTask(Integer.parseInt(parts[1]));
                    } else {
                        throw new JEFFException("You must provide only one number after unmark!");
                    }
                    break;
                case "todo":
                    if (parts.length != 2) {
                        throw new JEFFException("You must provide a valid task to do!");
                    } else {
                        addTask(parts[1], "todo");
                    }
                    break;
                case "deadline":
                    if (parts.length != 2) {
                        throw new JEFFException("You must provide a valid deadline task!");
                    } else {
                        addTask(parts[1], "deadline");
                    }
                    break;
                case "event":
                    if (parts.length != 2) {
                        throw new JEFFException("You must provide a valid event task!");
                    } else {
                        addTask(parts[1], "event");
                    }
                    break;
                default:
                    throw new JEFFException("That is not a valid command!");
            }
        } catch (JEFFException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private static void deleteTask(int i) throws JEFFException {
        if (i <= 0 || i > taskList.size()) {
            throw new JEFFException("The number is outside the range!");
        }
        System.out.println("Ok, I will delete this task:");
        System.out.printf("%s\n", taskList.get(i - 1));
        taskList.remove(i - 1);
    }

    private static void markNotDone(int i) throws JEFFException {
        if (i <= 0 || i > taskList.size()) {
            throw new JEFFException("The number is outside the range!");
        }
        taskList.get(i - 1).markNotDone();
        System.out.println("Ok, I marked this as undone:");
        System.out.printf("%s\n", taskList.get(i - 1));
    }

    private static void markDone(int i) throws JEFFException {
        if (i <= 0 || i > taskList.size()) {
            throw new JEFFException("The number is outside the range!");
        }
        taskList.get(i - 1).markDone();
        System.out.println("Alrighty, I marked this as done:");
        System.out.printf("%s\n", taskList.get(i - 1));
    }

    // Overloaded `addTask` to have more functionality
    private static void addTask(String input, String task) throws JEFFException {
        switch (task.toLowerCase()) {
            case "todo":
                taskList.add(new ToDo(input));
                break;
            case "deadline":
                String[] deadline_parts = input.split("/by", 2);
                if (deadline_parts.length < 2) {
                    throw new JEFFException("You did not follow the format for providing a deadline!\nIt should be: task /by date");
                }
                taskList.add(new Deadline(deadline_parts[0].trim(), deadline_parts[1].trim()));
                break;
            case "event":
                String[] event_parts = input.split("/from | /to ", 3);
                if (event_parts.length < 3) {
                    throw new JEFFException("You did not follow the format for providing a deadline!\nIt should be: task /from date /to date");
                }
                taskList.add(new Event(event_parts[0].trim(), event_parts[1].trim(), event_parts[2].trim()));
                break;
        }
        System.out.printf("added: %s\n", taskList.get(taskList.size() - 1));
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal
    }

    private static void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }
    }

    public static void exitChat() {
        System.out.println(LINE);
        System.out.println("Bye for now!");
        sc.close();
    }
}