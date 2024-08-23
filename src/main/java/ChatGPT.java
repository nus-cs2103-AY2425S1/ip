import java.util.Scanner;

public class ChatGPT {
    private static String NAME = "ChatGPT";
    private static String LINE = "________________________________________________";
    private static String[] list = new String[100];
    private static int listSize = 0;
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        sendGreeting();
        do {
            String input = userInput.nextLine();
            System.out.println("\t"+LINE);
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                printList();
            } else {
                list[listSize] = input;
                listSize++;
                System.out.println("\tadded: " + input);
                System.out.println("\t"+LINE);
            }
        } while (true);
        sendExit();
    }

    private static void sendGreeting() {
        System.out.println("\t"+LINE);
        System.out.println("\tHello! I'm " + NAME);
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t"+LINE);
    }

    private static void sendExit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t"+LINE);
    }

    private static void printList() {
        if (listSize == 0) {
            System.out.println("\tNothing has been added");
        }
        for (int i = 0; i < listSize; i++){
            System.out.println("\t" + (i+1) + ". " + list[i]);
        }
        System.out.println("\t"+LINE);
    }
}
