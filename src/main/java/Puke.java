import java.util.Scanner;
public class Puke {
    private MessageBuilder messageBuilder;
    private TaskManager taskManager;
    private InputManager inputManager;
    public Puke() {
        this.messageBuilder = new MessageBuilder();
        this.taskManager = new TaskManager();
        this.inputManager = new InputManager(taskManager, messageBuilder);
    }
    public static void main(String[] args) {
        Puke chatBot = new Puke();
        chatBot.start();
    }

    private void start() {
        messageBuilder.sendGreetingMessage();
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.trim().equalsIgnoreCase("bye")) {
                break;
            }
            inputManager.handleInput(userInput);
        }

        terminate();
        scanner.close();
    }

    private void terminate() {
        messageBuilder.sendGoodbyeMessage();
    }
}
