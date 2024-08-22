import java.util.Scanner;

public class Nen2 {
    private final static String logo = " _   _                 __\n"
            + "| \\ | |  ___  _ ___   |_  \\ \n"
            + "|  \\| | / _ \\| '__ |    ) |\n"
            + "| |\\  ||  __/| | | |   / /_ \n"
            + "|_| \\_| \\___||_| |_|  |____|\n";
    private static final String separator = "--------------------------------------------";
    private static final Scanner messageReader = new Scanner(System.in);

    private static final Task[] listOfTasks = new Task[100];
    private static int amountOfTask = 0;

    public static void main(String[] args) {
        greet();
        parseInput(messageReader.nextLine());
        exit();
    }

    private static void parseInput(String input) {
        System.out.println(separator);

        int arg;
        String action = input.split(" ")[0];

        switch(action) {
            case "bye":
                return;
            case "mark":
                arg = Integer.parseInt(input.split(" ")[1]);
                listOfTasks[arg - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(listOfTasks[arg - 1].toString());
                break;
            case "unmark":
                arg = Integer.parseInt(input.split(" ")[1]);
                listOfTasks[arg - 1].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(listOfTasks[arg - 1].toString());
                break;
            case "list":
                printList();
                break;
            default:
                Task t = Task.of(input);
                listOfTasks[amountOfTask] = t;
                amountOfTask++;
                System.out.println("Got it. I've added this task: \n" + t);
                System.out.println("Now you have " + amountOfTask + " tasks in the list.");
        }

        System.out.println(separator);
        parseInput(messageReader.nextLine());
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < amountOfTask; i++) {
            System.out.println(String.valueOf(i + 1) + "." + listOfTasks[i]);
        }
    }

    /**
     * Greets user by printing out logo and greeting messages
     */
    public static void greet() {
        System.out.println(separator);
        System.out.println(logo + "Hello! I'm Nen2 \nWhat can I do for you?");
        System.out.println(separator);
    }

    /**
     * End the conversation with ending messages
     */
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
