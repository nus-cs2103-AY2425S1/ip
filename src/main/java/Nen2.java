import java.util.Scanner;

public class Nen2 {
    private static String logo = " _   _                 __  \n"
            + "| \\ | |  ___  _ ___   |_  \\ \n"
            + "|  \\| | / _ \\| '__ |    ) |\n"
            + "| |\\  ||  __/| | | |   / /_ \n"
            + "|_| \\_| \\___||_| |_|  |____|\n";
    private static String seperator = "--------------------------------------------";
    private static Scanner messageReader = new Scanner(System.in);

    public static void main(String[] args) {
        Nen2.greet();
        Nen2.processMessage();
        Nen2.exit();
    }

    public static void processMessage() {
        String message = messageReader.nextLine();
        System.out.println(Nen2.seperator);

        if (message.equals("bye")) {
            return;
        } else {
            System.out.println(message);
        }
        System.out.println(Nen2.seperator);
        Nen2.processMessage();
    }

    /**
     * Greets user by printing out logo and greeting messages
     */
    public static void greet() {
        System.out.println(Nen2.seperator);
        System.out.println(logo + "Hello! I'm Nen2 \nWhat can I do for you?");
        System.out.println(Nen2.seperator);
    }

    /**
     * End the conversation with ending messages
     */
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Nen2.seperator);
    }
}
