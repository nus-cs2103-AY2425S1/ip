import java.util.Scanner;

/** Represents the main chatbot class.
 * @author Lee Ze Hao (A0276123J)
 */

public class Stelle {
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static final String NAME = "Stelle";
    static final String FILE_PATH = "./data/stelle.txt";

    public static void main(String[] args) {
        ChatLogic chatLogic;
        try {
            chatLogic = new ChatLogic(NAME, FILE_PATH);
        } catch (Exception e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println(e.getMessage());
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        Scanner scanner = new Scanner(System.in);

        chatLogic.printGreeting();

        while (true) {
            String input = scanner.nextLine();
            try {
                chatLogic.processInput(input);
            } catch (Exception e) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println(e.getMessage());
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}
