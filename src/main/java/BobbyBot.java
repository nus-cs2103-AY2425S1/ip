import java.util.Scanner;
public class BobbyBot {
    public static void main(String[] args) {
        String chatBotName = "BobbyBot";

        Scanner myScanner = new Scanner(System.in);
        printInput("Hello! I'm " + chatBotName, "What can I do for you?");
        while (true) {
            String input = myScanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                printInput(input);
            }
        }

        printInput("Bye. Hope to see you again soon!");
    }

    private static void printInput(String ...input) {
        System.out.print("\t");
        printLine();
        for (String i : input) {
            System.out.println("\t" + i);
        }
        System.out.print("\t");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
