import java.util.Scanner;

public class ChatKaki {

    public static void startChatService() {
        greetUser();
        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            Command command = Parser.parse(scanner);
            try {
                command.execute();
            } catch (Exception e) {
                Ui.printMessage(e.getMessage());
            }
            isExit = command.isExit();
        }
    }
    public static void main(String[] args) {
        startChatService();
    }

    private static void greetUser() {
        Storage.loadTasksFromFile();
        Ui.printMessage("Hello! I'm ChatKaki" + "\n What can I do for you?");
    }
}