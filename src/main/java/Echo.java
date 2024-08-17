import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Echo {
    private Tasks tasks;
    public Echo() {
        tasks = new Tasks();
    }
    private enum Command {
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        LIST,
        BYE,
        UNKNOWN;
        public static Command fromString(String command) {
            try {
                return Command.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }
    // Handles user input
    private Boolean handleInput(Scanner s) {
        // Reads user input
        String userInput = s.nextLine();
        String[] userInputs = userInput.split(" ", 2);
        Command command = Command.fromString(userInputs[0]);
        String arg = userInputs.length > 1 ? userInputs[1] : "";

        // Handles command
        switch (command) {
            case MARK:
                return handleMark(arg);
            case UNMARK:
                return handleUnmark(arg);
            case TODO:
                return handleTodo(arg, s);
            case DEADLINE:
                return handleDeadline(arg, s);
            case EVENT:
                return handleEvent(arg, s);
            case DELETE:
                return handleDelete(arg);
            case LIST:
                return handleList();
            case BYE:
                return handleBye();
            case UNKNOWN:
                return handleUnknown();
        }
        return true;
    }

    private Boolean handleMark(String arg) {
        // Error handling
        if (arg.length() != 1) { // Arg of incorrect length
            System.out.println("Please input 'mark [index]'");
            return true;
        }

        int index;
        try {
            index = Integer.valueOf(arg);
        } catch (NumberFormatException e) { // Index is not an integer
            System.out.println("Please input 'mark [index]'");
            return true;
        }
        if (index > tasks.getNumTasks()) { // Index is not within tasks length
            System.out.println("Invalid index.");
            return true;
        }

        // Mark task
        tasks.markTask(index);

        // Print success message
        System.out.println(
                "____________________________________________________________\n" +
                "Nice! I've marked this task as done:");
        tasks.printTask(index);
        System.out.print(
                "____________________________________________________________\n");
        return true;
    }
    private Boolean handleUnmark(String arg) {
        if (arg.length() != 1) { // Incorrect argument length
            System.out.println("Please input 'unmark [index]'");
            return true;
        }
        int index;
        try {
            index = Integer.valueOf(arg);
        } catch (NumberFormatException e) { // Not a number input
            System.out.println("Please input 'unmark [index]'");
            return true;
        }
        if (index > tasks.getNumTasks()) { // Index exceeds tasks length
            System.out.println("Invalid index.");
            return true;
        }

        // Unmark task
        tasks.unmarkTask(index);

        // Print success msg
        System.out.println(
                "____________________________________________________________\n" +
                "Ok, I've marked this task as not done yet: ");
        tasks.printTask(index);
        System.out.print(
                "____________________________________________________________\n");
        return true;
    }
    private Boolean handleTodo(String task, Scanner s) {
        while (task.isEmpty()) {
            System.out.println("Enter task description: ");
            task = s.nextLine();
        }
        tasks.addTask(task.trim(), TaskType.TODO, "");
        printSuccessMsg();
        return true;
    }
    private Boolean handleDeadline(String arg, Scanner s) {
        String[] args = arg.split("/by ");

        while (args[0].isEmpty()) {
            System.out.println("Enter task description: ");
            args[0] = s.nextLine();
        }
        args[0] = args[0].trim() + " ";

        String deadline = "";
        if (args.length < 2) { // Only task desription provided
            while (deadline.isEmpty()) {
                System.out.println("Deadline: ");
                deadline = s.nextLine();
            }
        } else {
            deadline = args[1];
        }

        tasks.addTask(args[0], TaskType.DEADLINE, deadline);
        printSuccessMsg();
        return true;
    }
    private Boolean handleEvent(String arg, Scanner s) {
        String[] args = arg.split("/from ");

        String endDate = "";
        String startDate = "";
        if (args[0].contains("/to")) {
            String[] temp = args[0].split("/to ");
            endDate = temp[1];
            args[0] = temp[0];
        }

        while (args[0].isEmpty()) { // No task description, start date provided
            System.out.println("Enter task description: ");
            args[0] = s.nextLine();
        }
        args[0] = args[0].trim() + " ";

        if (args.length < 2) { // No start date provided
            while (startDate.isEmpty()) {
                System.out.println("Start: ");
                startDate = s.nextLine();
            }
        } else {
            if (args[1].contains("/to")) {
                String[] temp = args[1].split("/to ");
                endDate = temp[1];
                startDate = temp[0];
            } else {
                startDate = args[1];
            }
        }

        while (endDate.isEmpty()) {
            System.out.println("End: ");
            endDate = s.nextLine();
        }

        tasks.addTask(args[0], TaskType.EVENT, startDate + "->" + endDate);
        printSuccessMsg();
        return true;
    }
    private Boolean handleDelete(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Please input 'delete [item index]'");
            return true;
        }
        int index;

        try {
            index = Integer.valueOf(arg);
        } catch (NumberFormatException e) {
            System.out.println("Please input 'delete [item index]'");
            return true;
        }

        if (index > tasks.getNumTasks()) {
            System.out.println("Invalid index.");
            return true;
        }

        System.out.println(
                "____________________________________________________________\n" +
                "Noted. I've removed this task:");
        tasks.deleteAndPrintTask(index);
        System.out.print(
                "____________________________________________________________\n");
        return true;
    }
    private Boolean handleBye() {
        System.out.println(
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
        return false;
    }

    private Boolean handleList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        tasks.printTasks();
        System.out.println("____________________________________________________________");
        return true;
    }

    private Boolean handleUnknown() {
        System.out.println(
            "____________________________________________________________\n" +
            "OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
            "____________________________________________________________");
        return true;
    }

    public void printSuccessMsg() {
        System.out.print(
            "____________________________________________________________\n" +
            "Got it. I've added this task:\n"
        );
        tasks.printTask(tasks.getNumTasks());
        System.out.printf(
            "Now you have %d task" +
            (tasks.getNumTasks() == 1 ? "" : "s") +
            " in the list.\n" +
            "____________________________________________________________\n",
        tasks.getNumTasks());
    }

    public static void main(String[] args) {
        Echo e = new Echo();
        // Creates a scanner object
        Scanner scanner = new Scanner(System.in);

        // Greets user
        String welcomeMsg =
                "____________________________________________________________\n" +
                "Hello! I'm Echo!\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.print(welcomeMsg);

        Boolean isAcceptingInput = true;
        while (isAcceptingInput) { // handles input until user says bye
            if (scanner.hasNextLine()) {
                isAcceptingInput = e.handleInput(scanner);
            } else {
                isAcceptingInput = false;
            }
        }
    }
}
