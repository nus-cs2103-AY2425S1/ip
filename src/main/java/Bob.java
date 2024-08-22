import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    private ArrayList<Task> TaskList = new ArrayList<>();

    public Bob() {
        sendMessage("Hello! I'm Bob!");
        sendMessage("What can I do for you?");
    }

    public void sendMessage(String message) {
        System.out.println("[Bob] " + message);
    }

    public void addTask(Task t) {
        sendMessage("Got it. I've added this task:");
        this.TaskList.add(t);
        sendMessage(t.toString());
        sendMessage("Now you have " + this.TaskList.size() + " tasks in the list.");
    }

    public void listTasks() {
        if (this.TaskList.isEmpty()) {
            sendMessage("No items yet!");
        } else {
            for (int i = 0; i < this.TaskList.size(); i++) {
                sendMessage((i + 1) + ". " + this.TaskList.get(i));
            }
        }
    }

    public void markTask(String message) {
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        Task t = this.TaskList.get(num - 1);
        t.markAsDone();
        sendMessage("Nice! I've marked this task as done:");
        sendMessage(t.toString());
    }

    public void unmarkTask(String message) {
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        Task t = this.TaskList.get(num - 1);
        t.markAsNotDone();
        sendMessage("OK, I've marked this task as not done yet:");
        sendMessage(t.toString());
    }

    public void deleteTask(String message) {
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        Task t = this.TaskList.get(num - 1);
        sendMessage("Noted. I've removed this task:");
        sendMessage(t.toString());
        this.TaskList.remove(num - 1);
        sendMessage("Now you have " + this.TaskList.size() + " tasks in the list.");
    }

    public void addToDo(String message) throws BobException {
        String x = message.replaceFirst("todo", "");
        if (x.isEmpty()) {
            throw new BobException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task t = new ToDo(x.trim());
        this.addTask(t);
    }

    public void addDeadline(String message) throws BobException {
        String x = message.replaceFirst("deadline", "");
        String[] parts = x.split(" /");
        if (parts.length != 2) {
            throw new BobException("OOPS!!! The description/start time of a deadline cannot be empty.");
        }
        Task t = new Deadline(parts[0].trim(), parts[1].trim());
        this.addTask(t);
    }

    public void addEvent(String message) throws BobException {
        String x = message.replaceFirst("event", "");
        String[] parts = x.split(" /");
         if (parts.length != 3) {
            throw new BobException("OOPS!!! The description/start time/end time of an event cannot be empty.");
        }
        Task t = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        this.addTask(t);
    }

    public boolean receiveMessage(String message) {
        try {
            if (message.equalsIgnoreCase("bye")) {
                sendMessage("Bye. Hope to see you again soon!");
                return false;
            } else if (message.equalsIgnoreCase("list")) {
                listTasks();
            } else if (message.matches("^mark \\d+$")) {
                markTask(message);
            } else if (message.matches("^unmark \\d+$")) {
                unmarkTask(message);
            } else if (message.matches("^delete \\d+$")) {
                deleteTask(message);
            } else if (message.matches("^todo.*")) {
                addToDo(message);
            } else if (message.matches("^deadline.*")) {
                addDeadline(message);
            } else if (message.matches("^event.*")) {
                addEvent(message);
            } else {
                throw new BobException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return true;
        } catch (BobException e) {
            sendMessage(e.getMessage());
            return true;
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String message = "";
        do {
            message = scanner.nextLine().trim();
        } while (receiveMessage(message));
    }

    public static void main(String[] args) {
        Bob em = new Bob();
        em.start();
    }
}
