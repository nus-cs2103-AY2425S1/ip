import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BobbyBot {
    public static void main(String[] args) {
        String chatBotName = "BobbyBot";

        String[] messages = new String[100];
        Scanner myScanner = new Scanner(System.in);
        printInput("Hello! I'm " + chatBotName, "What can I do for you?");
        while (true) {
            String input = myScanner.nextLine();
            switch (input) {
                case "list":
                    String[] formattedMessages = IntStream.
                            range(0, messages.length).
                            filter(i -> messages[i] != null).
                            mapToObj(i -> i + 1 + ". " + messages[i]).
                            toArray(String[]::new);
                    printInput(formattedMessages);
                    break;
                case "bye":
                    printInput("Bye. Hope to see you again soon!");
                    return;
                default:
                    printInput("added: " + input);
                    for (int i = 0; i < messages.length; i++) {
                        if (messages[i] == null) {
                            messages[i] = input;
                            break;
                        }
                    }
                    break;
            }
        }
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
