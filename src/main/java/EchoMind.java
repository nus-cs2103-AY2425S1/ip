import java.util.ArrayList;
import java.util.Scanner;

public class EchoMind {
    private ArrayList<Task> toDoList = new ArrayList<>();

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
            if (this.toDoList.isEmpty()) {
                sendMessage("No items yet!");
                return true;
            }
            for (int i = 0; i < this.toDoList.size(); i++) {
                sendMessage((i + 1) + ". " + this.toDoList.get(i));
            }
        } else if (message.matches("^mark \\d+$")) {
            int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
            Task t = this.toDoList.get(num - 1);
            t.markAsDone();
            sendMessage("Nice! I've marked this task as done:");
            sendMessage(t.toString());
        } else if (message.matches("^unmark \\d+$")) {
            int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
            Task t = this.toDoList.get(num - 1);
            t.markAsNotDone();
            sendMessage("OK, I've marked this task as not done yet:");
            sendMessage(t.toString());
        } else {
            this.toDoList.add(new Task(message));
            sendMessage("added: " + message);
        }
        return true;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String message = "";
        do {
            message = scanner.nextLine().trim();
        } while (receiveMessage(message));
    }

    public static void main(String[] args) {
        EchoMind em = new EchoMind();
        em.start();
    }
}
