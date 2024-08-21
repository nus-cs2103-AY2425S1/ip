import java.util.ArrayList;
import java.util.Scanner;

public class EchoMind {
    private ArrayList<Task> TaskList = new ArrayList<>();

    public EchoMind() {
        sendMessage("Hello! I'm EchoMind!");
        sendMessage("What can I do for you?");
    }

    public void sendMessage(String message) {
        System.out.println("[EchoMind] " + message);
    }

    public void addTask(Task t) {
        sendMessage("Got it. I've added this task:");
        this.TaskList.add(t);
        sendMessage(t.toString());
        sendMessage("Now you have " + this.TaskList.size() + " tasks in the list.");
    }

    public boolean receiveMessage(String message) {
        if (message.equalsIgnoreCase("bye")) {
            sendMessage("Bye. Hope to see you again soon!");
            return false;
        } else if (message.equalsIgnoreCase("list")) {
            if (this.TaskList.isEmpty()) {
                sendMessage("No items yet!");
                return true;
            }
            for (int i = 0; i < this.TaskList.size(); i++) {
                sendMessage((i + 1) + ". " + this.TaskList.get(i));
            }
        } else if (message.matches("^mark \\d+$")) {
            int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
            Task t = this.TaskList.get(num - 1);
            t.markAsDone();
            sendMessage("Nice! I've marked this task as done:");
            sendMessage(t.toString());
        } else if (message.matches("^unmark \\d+$")) {
            int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
            Task t = this.TaskList.get(num - 1);
            t.markAsNotDone();
            sendMessage("OK, I've marked this task as not done yet:");
            sendMessage(t.toString());
        } else if (message.matches("^todo .+")) {
            String x = message.replaceFirst("todo ", "");
            Task t = new ToDo(x);
            this.addTask(t);
        } else if (message.matches("^deadline.+/by.+$")) {
            String x = message.replaceFirst("deadline ", "");
            String[] parts = x.split(" /");
            Task t = new Deadline(parts[0].trim(), parts[1].trim());
            this.addTask(t);
        } else if (message.matches("^event.+/from.+/to.+$")) {
            String x = message.replaceFirst("event ", "");
            String[] parts = x.split(" /");
            Task t = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
            this.addTask(t);
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
