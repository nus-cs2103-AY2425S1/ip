import java.util.ArrayList;
import java.util.Scanner;

public class Nen2 {
    private final static String logo = " _   _                 __\n"
            + "| \\ | |  ___  _ ___   |_  \\ \n"
            + "|  \\| | / _ \\| '__ |    ) |\n"
            + "| |\\  ||  __/| | | |   / /_ \n"
            + "|_| \\_| \\___||_| |_|  |____|\n";
    private static final String separator = "--------------------------------------------";
    private static final Scanner messageReader = new Scanner(System.in);

    private static final ArrayList<Task> listOfTasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        parseInput(messageReader.nextLine());
        exit();
    }

    private static void parseInput(String input) {
        System.out.println(separator);

        int arg;
        String action = input.split(" ")[0];

        try{
            switch(action) {
                case "bye":
                    return;
                case "mark":
                    arg = getIndex(input);
                    listOfTasks.get(arg - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(listOfTasks.get(arg - 1).toString());
                    break;
                case "unmark":
                    arg = getIndex(input);
                    listOfTasks.get(arg - 1).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(listOfTasks.get(arg - 1).toString());
                    break;
                case "delete":
                    arg = getIndex(input);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(listOfTasks.get(arg - 1).toString());
                    listOfTasks.remove(arg - 1);
                    System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
                    break;
                case "list":
                    printList();
                    break;
                default:
                    Task t = Task.of(input);
                    listOfTasks.add(t);
                    System.out.println("Got it. I've added this task: \n" + t);
                    System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
            }
        } catch (InvalidInputException | ArgumentMissingException | EmptyDescriptionException e){
            System.out.println(e.getMessage());
        }

        System.out.println(separator);
        parseInput(messageReader.nextLine());
    }

    private static int getIndex(String text) throws ArgumentMissingException, InvalidInputException{
        String[] arr = text.split(" ");
        if (arr.length < 2) {
            throw new ArgumentMissingException(arr[0] + " which one??");
        }
        try {
            int out = Integer.parseInt(arr[1]);
            if (out > listOfTasks.size()) {
                throw new ArgumentMissingException("Helloo there's only " + listOfTasks.size() + " tasks\n"
                        + "How to " + arr[0] + " " + out + "th task");
            }
            return out;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Huh?? \"" + arr[1] + "\" is not a number lah!");
        }
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + listOfTasks.get(i));
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
