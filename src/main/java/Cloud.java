import java.util.Scanner;

public class Cloud {

    private static final TaskList tasks = new TaskList();
    private static final String EXIT_COMMAND = "bye";

    private static void greet() {
        System.out.println(
            "Hello! I'm Cloud\n" +
            "What can I do for you?"
        );
    }

    private static void printHorizLine() {
        System.out.println(
            "____________________________________________________________"
        );
    }

    private static void echo(String message) {
        System.out.println(message);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // manage the task adding logic for all task types
    private static void addTask(Query query) {
        String command = query.getCommand().strip();
        String[] details;
        switch (command) {
            case "todo":
                Todo todo = new Todo(query.getDetails());
                tasks.add(todo);
                break;
            case "deadline":
                details = query.getDetails().split("/by");
                Deadline dl = new Deadline(details[0].strip(), details[1].strip());
                tasks.add(dl);
                break;
            case "event":
                details = query.getDetails().split("/from|/to");
                Event event = new Event(
                        details[0].strip(),
                        details[1].strip(),
                        details[2].strip()
                );
                tasks.add(event);
                break;
            default:
                break;
        }
        System.out.printf(
                "Added the following task:\n\t%s\nNow you have %d task%s in the list\n",
                tasks.getLatestTask(),
                tasks.getTaskCount(),
                tasks.getTaskCount() != 1 ? "s" : ""
        );
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printHorizLine();
        greet();
        while (true) {
            printHorizLine();
            System.out.print(">> ");
            String userInput = sc.nextLine().strip();
            // exit chat if user enters exit command
            if (userInput.equals(EXIT_COMMAND)) {
                break;
            }
            // parse the user input to a Query object
            Query query = Parser.parse(userInput);
            String command = query.getCommand();

            int taskNum;
            switch (command) {
                case "list":
                    System.out.println(tasks);
                    break;
                case "mark":
                    taskNum = Integer.parseInt(query.getDetails());
                    tasks.mark(taskNum);
                    System.out.println("Task marked as done!");
                    System.out.println(tasks.getTaskStatus(taskNum));
                    break;
                case "unmark":
                    taskNum = Integer.parseInt(query.getDetails());
                    tasks.unmark(taskNum);
                    System.out.println("Task marked as not done");
                    System.out.println(tasks.getTaskStatus(taskNum));
                    break;
                // case fallthrough for tasks
                case ("event"):
                case ("deadline"):
                case ("todo"):
                    addTask(query);
                    break;
                default:
                    break;
            }
        }
        exit();
        printHorizLine();
    }
}
