import java.util.Objects;
import java.util.Scanner;

public class JEFF {
    private static final String LINE = "--------------------------------------------";
    private static final Scanner sc = new Scanner(System.in); // Scanner object to detect user input

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
            chatCont = echoEvent();
        }
        exitEvent();
    }

    private static boolean echoEvent() {
        System.out.println(LINE);
        String input = sc.nextLine();
        if (Objects.equals(input, "bye")) {
            return false;
        }
        System.out.println(input);
        return true;
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