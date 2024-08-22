import java.util.Scanner;

public class Nen2 {
    private final static String logo = " _   _                 __\n"
            + "| \\ | |  ___  _ ___   |_  \\ \n"
            + "|  \\| | / _ \\| '__ |    ) |\n"
            + "| |\\  ||  __/| | | |   / /_ \n"
            + "|_| \\_| \\___||_| |_|  |____|\n";
    private final static String separator = "--------------------------------------------";
    private static Scanner messageReader = new Scanner(System.in);

    private static String[] listOfText = new String[100];
    private static int amountOfText = 0;

    public static void main(String[] args) {
        greet();
        processMessage();
        exit();
    }

    private static void processMessage() {
        String message = messageReader.nextLine();
        System.out.println(separator);

        switch(message) {
            case "bye":
                return;
            case "list":
                printList();
                break;
            default:
                System.out.println("added: " + message);
                listOfText[amountOfText] = message;
                amountOfText++;
        }

        System.out.println(separator);
        processMessage();
    }

    private static void printList() {
        for (int i = 0; i < amountOfText; i++) {
            System.out.println(String.valueOf(i + 1) + ". " + listOfText[i]);
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
