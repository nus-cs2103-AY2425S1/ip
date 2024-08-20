import java.util.Scanner;

public class Easton {

    public static final String CHATBOT_NAME = "Easton";
    public static String[] itemList = new String[100];
    public static int itemListSize = 0;

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
                for (int i = 0; i < itemListSize; i ++) {
                    System.out.println((i + 1) + ". " + itemList[i]);
                }
            } else {
                System.out.println(input);
            }
            printDivider();
        }
    }

    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    private static String prompt(Scanner scanner) {
        return scanner.nextLine();
    }
}
