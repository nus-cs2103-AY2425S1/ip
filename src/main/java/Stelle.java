import java.util.Scanner;

public class Stelle {
    static final String NAME = "Stelle";

    public static void main(String[] args) {
        ChatLogic chatLogic = new ChatLogic(NAME);
        Scanner scanner = new Scanner(System.in);

        chatLogic.printGreeting();

        while (true) {
            String input = scanner.nextLine();
            chatLogic.processInput(input);
        }
    }
}
