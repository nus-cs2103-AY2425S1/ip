import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class KukiShinobu {
    private final String name = "Kuki Shinobu";
    private final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        KukiShinobu shinobu = new KukiShinobu();
        shinobu.listen();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void listen() {
        this.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();  // Read user input
            KukiShinobu.printHorizontalLine();

            // split user input into commands and argument (if applicable)
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            //prevents index out of bounds error if no arguments provided after command
            String arguments = parts.length > 1 ? parts[1] : "";

            // break out of while loop if user issues "bye" command
            if (command.equals("bye")) {
                break;
            }

            try {
                // otherwise, handle all other commands as appropriate
                switch(command) {
                    case "list":
                        this.listTasks();
                        break;
                    case "mark":
                        // argument is task index
                        this.markAsDone(arguments);
                        break;
                    case "unmark":
                        // argument is task index
                        this.unmarkAsDone(arguments);
                        break;
                    // TODO: Add cases for todo, deadline and event
                    case "todo":
                        // argument is desc, pass desc in
                        this.addTodo(arguments);
                        break;
                    case "deadline":
                        // Break arguments into desc + by
                        this.addDeadline(arguments);
                        break;
                    case "event":
                        // break arguments into desc, start and end
                        this.addEvent(arguments);
                        break;
                    default:
                        this.handleUnknownCommand();
                }
            } catch (KukiShinobuException e) {
                System.out.println(e.getMessage());
            }

            KukiShinobu.printHorizontalLine();
        }
        this.goodbye();
    }
    private void handleUnknownCommand() throws KukiShinobuException {
        throw new KukiShinobuException("Hmm... I don't quite understand what you mean!");
    }
    private void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + "." + this.tasks.get(i));
        }
    }

    private void markAsDone(String indexString) {
        int i = Integer.parseInt((indexString));
        this.tasks.get(i - 1).markAsDone();
    }

    private void unmarkAsDone(String indexString) {
        int i = Integer.parseInt((indexString));
        this.tasks.get(i - 1).unmarkAsDone();
    }

    private void printAddedTaskSummary(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    private void addTodo(String arguments) throws KukiShinobuException {
        // TODO: Check for missing description
        if (arguments.isEmpty()) {
            throw new KukiShinobuException("Todo is missing description!");
        }
        // argument is taskDescription
        Task newTodo = new Todo(arguments);
        this.tasks.add(newTodo);
        this.printAddedTaskSummary(newTodo);

    }

    private void addDeadline(String arguments) throws KukiShinobuException {
        // TODO: Check for missing description or /by
        String[] parts = arguments.split(" /by ", 2);

        // Checks for missing arguments
        if (parts.length != 2) {
            if (!arguments.contains("/by")) {
                throw new KukiShinobuException("You're missing the /by flag and argument!");
            } else {
                throw new KukiShinobuException("Deadline is missing the description!");
            }
        }

        String taskDescription = parts[0];
        String by = parts[1];
        Task newDeadline = new Deadline(taskDescription, by);
        this.tasks.add(newDeadline);
        this.printAddedTaskSummary(newDeadline);
    }

    private void addEvent(String arguments) throws KukiShinobuException {
        //TODO: Check for missing desc, /from or /to
        //TODO: Modify the logic to split based on "/" instead to accommodate flipped order of flags
//        String[] parts = arguments.split("/", 3);


        String[] parts = arguments.split("\\s+/from\\s+|\\s+/to\\s+", 3);
        if (parts.length != 3) {
            throw new KukiShinobuException("Event is missing description, from or to.");
        }

        String taskDescription = parts[0];
        String start = parts[1];
        String end = parts[2];
        Task newEvent = new Event(taskDescription, start, end);
        this.tasks.add(newEvent);
        this.printAddedTaskSummary(newEvent);
    }

//    private void addTask(String taskDescription) {
//        Task newTask = new Task(taskDescription);
//        this.tasks.add(newTask);
//        System.out.println("added: " + taskDescription);
//    }

    public void greet() {
        KukiShinobu.printHorizontalLine();
        System.out.println("Hey Traveller! I'm " + this.name + ", deputy leader of the Arataki Gang.");
        System.out.println("Just let me know if you ever find yourself in a pinch. I can help you out.");
        KukiShinobu.printHorizontalLine();
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        KukiShinobu.printHorizontalLine();
    }

    public void fancyGreet() {
        String logo = " ____  __.      __   .__       _________.__    .__             ___.          \n"
                + "|    |/ _|__ __|  | _|__|     /   _____/|  |__ |__| ____   ____\\_ |__  __ __ \n"
                + "|      < |  |  \\  |/ /  |     \\_____  \\ |  |  \\|  |/    \\ /  _ \\| __ \\|  |  \\\n"
                + "|    |  \\|  |  /    <|  |     /        \\|   Y  \\  |   |  (  <_> ) \\_\\ \\  |  /\n"
                + "|____|__ \\____/|__|_ \\__|    /_______  /|___|  /__|___|  /\\____/|___  /____/ \n"
                + "        \\/          \\/               \\/      \\/        \\/           \\/      \n";
        System.out.println("Hello from\n" + logo);
    }


}
