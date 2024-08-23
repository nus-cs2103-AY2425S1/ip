import java.util.*;

public class Tick {
    private static final String separator = "____________________________________________________________";
    private static final String botName = "Tick";
    private ArrayList<Task> checklist = new ArrayList<>();

    public void greet() {
        System.out.println(Tick.separator);
        System.out.printf("Hello! I'm %s!\n", Tick.botName);
        System.out.println("What can I do for you?");
        System.out.println(Tick.separator);
    }

    public void exit() {
        System.out.println(Tick.separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Tick.separator);
    }

    public void addTaskToList(String command) throws TickException {
        if (command.startsWith("todo")) {
            if (command.substring(5).isEmpty()) {
                throw new TickException("The description of a todo cannot be empty.");
            }
            ToDo task = new ToDo(command.substring(5));
            this.checklist.add(task);
        } else if (command.startsWith("deadline")) {
            String[] parts = command.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new TickException("Please specify BOTH the description and deadline of a deadline.");
            }
            Deadline task = new Deadline(parts[0], parts[1]);
            this.checklist.add(task);
        } else if (command.startsWith("event")) {
            String[] parts = command.substring(6).split(" /from | /to ");
            if (parts.length < 3) {
                throw new TickException("Please specify the description, start time and end time of an event.");
            }
            Event task = new Event(parts[0], parts[1], parts[2]);
            this.checklist.add(task);
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(this.checklist.get(this.checklist.size() - 1));
        System.out.printf("Now you have %d tasks in the list.\n", this.checklist.size());
    }

    public void displayList() {
        if (this.checklist.isEmpty()) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.checklist.size(); i++) {
                System.out.printf("%d.%s\n", i + 1, this.checklist.get(i));
            }
        }
    }

    public void markTaskAsDone(int index) throws TickException {
        if (index < 1 || index > this.checklist.size()) {
            throw new TickException("The task number is out of range.");
        }
        Task task = this.checklist.get(index - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void markTaskAsUndone(int index) throws TickException {
        if (index < 1 || index > this.checklist.size()) {
            throw new TickException("The task number is out of range.");
        }
        Task task = this.checklist.get(index - 1);
        task.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void runCommand(String command) throws TickException {
        String[] commandParts = command.split(" ");
        switch (commandParts[0]) {
            case "list":
                this.displayList();
                break;
            case "mark":
                if (commandParts.length < 2) {
                    throw new TickException("Please specify the task number to mark as done.");
                }
                this.markTaskAsDone(Integer.parseInt(commandParts[1]));
                break;
            case "unmark":
                if (commandParts.length < 2) {
                    throw new TickException("Please specify the task number to mark as not done yet.");
                }
                this.markTaskAsUndone(Integer.parseInt(commandParts[1]));
                break;
            case "todo":
            case "deadline":
            case "event":
                if (commandParts.length < 2) {
                    throw new TickException("Please specify task arguments.");
                }
                this.addTaskToList(command);
                break;
            default:
                throw new TickException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        Tick bot = new Tick();
        bot.greet();
        Scanner scn = new Scanner(System.in);
        while (true) {
            String command = scn.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(Tick.separator);
            try {
                bot.runCommand(command);
            } catch (TickException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Tick.separator);
        }
        bot.exit();
    }
}