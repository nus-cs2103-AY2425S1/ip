import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;


public class Bob {
    static final String FILE_PATH = "data/bob.txt";

    private ArrayList<Task> TaskList = new ArrayList<>();

    public Bob() {
        sendMessage("Hello! I'm Bob!");
        sendMessage("What can I do for you?");
    }

    public void loadTask(String t) {
        String[] task_list = t.trim().split(" \\| ");
        Task x;
        switch (task_list[0]) {
        case ("T"):
            x = new ToDo(task_list[2]);
            this.TaskList.add(x);
            break;
        case ("D"):
            x = new Deadline(task_list[2], task_list[3]);
            this.TaskList.add(x);
            break;
        case ("E"):
            x = new Event(task_list[2], task_list[3], task_list[4]);
            this.TaskList.add(x);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + task_list[0]);
        }
        if (task_list[1].equals("1")) {
            x.markAsDone();
        }
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

    public Task addToDo(String description) {
        Task t = new ToDo(description);
        this.addTask(t);
        return t;
    }

    public Task addDeadline(String description, String start) {
        Task t = new Deadline(description, start);
        this.addTask(t);
        return t;
    }

    public Task addEvent(String description, String start, String end) {
        Task t = new Event(description, start, end);
        this.addTask(t);
        return t;
    }

    public boolean receiveMessage(String message) {
        boolean result = true;
        try {
            if (message.equalsIgnoreCase("bye")) {
                exit();
                result = false;
            } else if (message.equalsIgnoreCase("list")) {
                listTasks();
            } else if (message.matches("^mark \\d+$")) {
                markTask(message);
            } else if (message.matches("^unmark \\d+$")) {
                unmarkTask(message);
            } else if (message.matches("^delete \\d+$")) {
                deleteTask(message);
            } else if (message.matches("^todo.*")) {
                String x = message.replaceFirst("todo", "");
                if (x.isEmpty()) {
                    throw new BobException("OOPS!!! The description of a todo cannot be empty.");
                }
                addToDo(x.trim());
            } else if (message.matches("^deadline.*")) {
                String x = message.replaceFirst("deadline", "");
                String[] parts = x.split(" /");
                if (parts.length != 2) {
                    throw new BobException("OOPS!!! The description/start time of a deadline cannot be empty.");
                }
                addDeadline(parts[0].trim(), parts[1].trim());
            } else if (message.matches("^event.*")) {
                String x = message.replaceFirst("event", "");
                String[] parts = x.split(" /");
                if (parts.length != 3) {
                    throw new BobException("OOPS!!! The description/start time/end time of an event cannot be empty.");
                }
                addEvent(parts[0].trim(), parts[1].trim(), parts[2].trim());
            } else {
                throw new BobException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (BobException e) {
            sendMessage(e.getMessage());
        } finally {
            return result;
        }
    }

    public void exit() {
        sendMessage("Bye. Hope to see you again soon!");
        try {
            Files.createDirectories(Path.of("data")); // Hard-coded
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task t: this.TaskList) {
                fw.write(t.toSave() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException ignored) {
            sendMessage("An error has occurred when trying to save.");
        }
    }
    public void start() {
        File f = new File(FILE_PATH);
        try {
            // For file
            Scanner s1 = new Scanner(f);
            while (s1.hasNext()) {
                loadTask(s1.nextLine());
            }
        } catch (FileNotFoundException ignored) {
        }

        // For others
        Scanner s2 = new Scanner(System.in);
        String message = "";
        do {
            message = s2.nextLine().trim();
        } while (receiveMessage(message));
    }

    public static void main(String[] args) {
        Bob b = new Bob();
        b.start();
    }
}
