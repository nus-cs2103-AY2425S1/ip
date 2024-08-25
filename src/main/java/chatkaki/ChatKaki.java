package chatkaki;

import java.util.Scanner;
import chatkaki.commands.Command;

/**
 * Represents the ChatKaki chatbot.
 */
public class ChatKaki {

    /**
     * Starts the chat service.
     */
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

    /**
     * Main entry-point for the ChatKaki application.
     */
    public static void main(String[] args) {
        startChatService();
    }

    /**
     * Greets the user.
     */
    private static void greetUser() {
        Storage.loadTasksFromFile();
        Ui.printMessage("Hello! I'm ChatKaki" + "\n What can I do for you?");
    }
}