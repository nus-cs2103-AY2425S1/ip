import java.util.ArrayList;
import java.util.Scanner;

public class EchoMind {
    private ArrayList<String> toDoList = new ArrayList<>();

    public EchoMind() {
        sendMessage("Hello! I'm EchoMind!");
        sendMessage("What can I do for you?");
    }

    public void sendMessage(String message) {
        System.out.println("[EchoMind] " + message);
    }

    public boolean receiveMessage(String message) {
        if (message.equalsIgnoreCase("bye")) {
            sendMessage("Bye. Hope to see you again soon!");
            return false;
        } else if (message.equalsIgnoreCase("list")) {
            if (toDoList.size() == 0) {
                sendMessage("No todo items yet!");
                return true;
            }
            for (int i = 0; i < toDoList.size(); i++) {
                sendMessage((i + 1) + ". " + toDoList.get(i));
            }
        } else {
            toDoList.add(message);
            sendMessage("added: " + message);
        }
        return true;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (true) {
            message = scanner.nextLine().trim();
            if (!receiveMessage(message)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        EchoMind em = new EchoMind();
        em.start();
    }
}
