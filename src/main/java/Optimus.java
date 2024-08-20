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

    public static void main(String[] args) {
        Optimus optimus = new Optimus();
        Scanner scanner = new Scanner(System.in);
        
        while (optimus.getStatus()) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ");
            String command = commands[0];

            try {
                switch (command) {
                    case "bye":
                        optimus.leave();
                        break;
                    case "list":
                        optimus.taskManager.printAllTasks();
                        break;
                    case "mark":
                        optimus.taskManager.markTaskAsDone(commands);
                        break;
                    case "unmark":
                        optimus.taskManager.markTaskAsIncomplete(commands);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        optimus.taskManager.addTask(commands);
                        break;
                    default:
                        throw new InvalidCommandException("This command does not exist.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
