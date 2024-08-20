import java.util.Scanner;

public class Optimus {
    boolean isLive;
    public static String linebreak = "____________________________";
    TaskManager taskManager;

    public Optimus() {
        this.isLive = true;
        this.greet();
        this.taskManager = new TaskManager();
    }

    public boolean getStatus() {
        return this.isLive;
    }

    private void greet() {
        System.out.println("Hello! I'm Optimus\nWhat can I do for you?");
        System.out.println(Optimus.linebreak);
    }

    private void leave() {
        this.isLive = false;
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Optimus.linebreak);
    }

    private void handleCommand(String input) throws InvalidCommandException {
        String[] commands = input.split(" ");
        if (commands.length == 0) {
            throw new InvalidCommandException("No command entered.");
        }

        String command = commands[0].toLowerCase();
        try{
            switch (command) {
                case "bye" -> leave();
                case "list" -> taskManager.printAllTasks();
                case "mark" -> taskManager.markTaskAsDone(commands);
                case "unmark" -> taskManager.markTaskAsIncomplete(commands);
                case "todo", "deadline", "event" -> taskManager.addTask(commands);
                case "delete" -> taskManager.deleteTask(commands);
                default -> throw new InvalidCommandException("This command does not exist.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Optimus optimus = new Optimus();
        try (Scanner scanner = new Scanner(System.in)) {
            while (optimus.getStatus()) {
                String input = scanner.nextLine();
                try {
                    optimus.handleCommand(input);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}