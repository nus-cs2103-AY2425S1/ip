import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class JEFF {
    private static final String LINE = "--------------------------------------------";
    private static final Scanner sc = new Scanner(System.in); // Scanner object to detect user input
    private static ArrayList<String> taskList = new ArrayList<>();

    public static void main(String[] args) {
        // Init sequence
        String logo =
                """
                        ███████████████████████████████████████████████████████████████████████
                        █▄─▀█▀─▄█▄─█─▄███▄─▀█▄─▄██▀▄─██▄─▀█▀─▄█▄─▄▄─█████▄─▄█▄─▄▄─█▄─▄▄─█▄─▄▄─█
                        ██─█▄█─███▄─▄█████─█▄▀─███─▀─███─█▄█─███─▄█▀███─▄█─███─▄█▀██─▄████─▄███
                        ▀▄▄▄▀▄▄▄▀▀▄▄▄▀▀▀▀▄▄▄▀▀▄▄▀▄▄▀▄▄▀▄▄▄▀▄▄▄▀▄▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▄▄▀▄▄▄▀▀▀▄▄▄▀▀▀""";

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
        exitEvent();
    }

    private static boolean chatEvent() {
        System.out.println(LINE);
        String input = sc.nextLine();
        if (Objects.equals(input, "bye")) {
            return false;
        } else if (Objects.equals(input, "list")) {
            printList(input);
        } else {
            // Add input to task list
            taskList.add("input");
            System.out.printf("added: %s\n", input);
        }
        return true;
    }

    private static void printList(String input) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i, input);
        }
    }

    public static void exitEvent() {
        System.out.println(LINE);
        System.out.println("Bye for now!");
    }

    public static boolean promptEvent(String prompt) {
        System.out.println(LINE);
        System.out.println(prompt);
        String input = sc.nextLine();
        if (Objects.equals(input, "bye")) {
            return false;
        }
        return true;
    }
}