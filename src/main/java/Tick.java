import java.util.*;

public class Tick {
    private static final String separator = "____________________________________________________________\n";
    private static final String botName = "Tick";
    private ArrayList<Task> checklist = new ArrayList<>();

    public void greet() {
        System.out.print(Tick.separator);
        System.out.printf("Hello! I'm %s\n", Tick.botName);
        System.out.println("What can I do for you?");
        System.out.println(Tick.separator);
    }

    public void exit() {
        System.out.print(Tick.separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Tick.separator);
    }

    public void addToList(String description) {
        Task task = new Task(description);
        this.checklist.add(task);
        System.out.printf("added: %s\n", description);
    }

    public void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.checklist.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, this.checklist.get(i));
        }
    }

    public void markTaskAsDone(int index) {
        Task task = this.checklist.get(index - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void markTaskAsUndone(int index) {
        Task task = this.checklist.get(index - 1);
        task.markAsUndone();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void runCommand(String command) {
        System.out.print(Tick.separator);
        String[] commandParts = command.split(" ");
        switch (commandParts[0]) {
            case "list":
                this.displayList();
                break;
            case "mark":
                this.markTaskAsDone(Integer.parseInt(commandParts[1]));
                break;
            case "unmark":
                this.markTaskAsUndone(Integer.parseInt(commandParts[1]));
                break;
            default:
                this.addToList(command);
        }
        System.out.println(Tick.separator);
    }

    public static void main(String[] args) {
        Tick bot = new Tick();
        bot.greet();
        while (true) {
            Scanner scn = new Scanner(System.in);
            String command = scn.nextLine();
            if (command.equals("bye")) {
                break;
            }
            bot.runCommand(command);
        }
        bot.exit();
    }
}