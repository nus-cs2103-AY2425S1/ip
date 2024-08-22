import java.util.Objects;
import java.util.Scanner;

public class JEFF {
    private static final String LINE = "--------------------------------------------";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner object to detect user input

        // Init sequence
        String logo =
                """
                        ███████████████████████████████████████████████████████████████████████
                        █▄─▀█▀─▄█▄─█─▄███▄─▀█▄─▄██▀▄─██▄─▀█▀─▄█▄─▄▄─█████▄─▄█▄─▄▄─█▄─▄▄─█▄─▄▄─█
                        ██─█▄█─███▄─▄█████─█▄▀─███─▀─███─█▄█─███─▄█▀███─▄█─███─▄█▀██─▄████─▄███
                        ▀▄▄▄▀▄▄▄▀▀▄▄▄▀▀▀▀▄▄▄▀▀▄▄▀▄▄▀▄▄▀▄▄▄▀▄▄▄▀▄▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▄▄▀▄▄▄▀▀▀▄▄▄▀▀▀""";

        System.out.println("Hello there\n" + logo);

        // Chat loop
        boolean chatCont = true;
        while (chatCont) {
            chatCont = promptEvent(sc, "What can I do for you?");
        }
        exitEvent();
    }

    public static void exitEvent() {
        System.out.println(LINE);
        System.out.println("Bye for now!");
    }

    public static boolean promptEvent(Scanner sc, String prompt) {
        System.out.println(LINE);
        System.out.println(prompt);
        String input = sc.nextLine();
        if (Objects.equals(input, "nothing")) {
            return false;
        }
        return true;
    }
}