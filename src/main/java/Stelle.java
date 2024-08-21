import java.util.Scanner;

public class Stelle {
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static final String NAME = "Stelle";

    public static void main(String[] args) {
        ChatLogic chatLogic = new ChatLogic(NAME);
        Scanner scanner = new Scanner(System.in);

        chatLogic.printGreeting();

        while (true) {
            String input = scanner.nextLine();
            try {
                chatLogic.processInput(input);
            } catch (StelleException e) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println(e.getMessage());
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}
