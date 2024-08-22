import java.util.Scanner;
public class Puke {
    private MessageBuilder messageBuilder;
    public Puke() {
        this.messageBuilder = new MessageBuilder();
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
            messageBuilder.echoMessage(userInput);
        }

        terminate();
        scanner.close();
    }

    private void terminate() {
        messageBuilder.sendGoodbyeMessage();
    }
}
