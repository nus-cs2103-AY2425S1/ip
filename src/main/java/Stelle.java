import java.util.Scanner;

/** Represents the main chatbot class.
 * @author Lee Ze Hao (A0276123J)
 */

public class Stelle {
    static final String NAME = "Stelle";
    static final String FILE_PATH = "./data/stelle.txt";

    private Ui ui;
    private ChatLogic chatLogic;

    public Stelle(String name, String filePath) {
        ui = new Ui(name);
        chatLogic = new ChatLogic(ui, filePath);
    }

    public void run() {
        ui.printGreeting();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            ui.showLine();
            try {
                chatLogic.processInput(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Stelle(NAME, FILE_PATH).run();
    }
}
