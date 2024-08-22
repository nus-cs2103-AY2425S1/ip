import java.util.Scanner;

public class GavinChatBot{
    // array to hold Task objects
    static Task[] tasks = new Task[100];
    static int taskCount = 0;
    public static void main(String[] args) {

        String horizontalLine = "___________________________________________________________________________________\n";

        // print welcome message
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Gavin's Chat Bot!\n");
        System.out.println("What can I do for you?\n");
        System.out.println(horizontalLine);

        // create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // echo input by user until user types bye
        while (true) {
            try {
                input = scanner.nextLine();
                // skip adding tasks for "list" or "bye"
                if (input.equalsIgnoreCase("list") || input.equalsIgnoreCase("bye") || input.startsWith("mark") || input.startsWith("unmark") || input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    handleInput(input);
                } else {
                    /*
                    System.out.println(horizontalLine);
                    tasks[taskCount] = new Task(input);
                    taskCount++;
                    System.out.println("added: " + input);
                    System.out.println(horizontalLine);
                    handleInput(input);
                     */
                    // throw an error for invalid input
                    throw new GavinException("Invalid input! Please start with 'todo', 'deadline', or 'event'.");
                }
            } catch (GavinException e) {
                System.out.println(horizontalLine);
                System.out.println("!!!ERROR!!! " + e.getMessage());
                System.out.println(horizontalLine);
            }
        }
    }

    public static void handleInput(String input) throws GavinException{
        String horizontalLine = "___________________________________________________________________________________\n";
        if (input.equalsIgnoreCase("bye")) {
            // print exit message
            System.out.println(horizontalLine);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontalLine);
            System.exit(0);
        } else if (input.equalsIgnoreCase("list")) {
            // print out list of items
            System.out.println(horizontalLine);
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] == null) {
                    break;
                }
                System.out.println(i + 1 + ". " + tasks[i]);
            }
            System.out.println(horizontalLine);
            //System.exit(1);
        } else if (input.startsWith("mark")) {
            markTask(input);
        } else if (input.startsWith("unmark")) {
            unmarkTask(input);
        } else if (input.startsWith("todo")) {
            toDoTask(input);
        } else if (input.startsWith("deadline")) {
            deadlineTask(input);
        } else if (input.startsWith("event")) {
            eventTask(input);
        }
    }

    public static void markTask(String input) throws GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        // mark task as done
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

        // throw an error if taskNumber is negative, or greater than the current task count
        if (taskNumber < 0 || taskNumber >= taskCount) {
            throw new GavinException("Task number is invalid!!!");
        }

        tasks[taskNumber].markAsDone();
        System.out.println(horizontalLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + tasks[taskNumber].getStatusIcon() + "] " + tasks[taskNumber].description);
        System.out.println(horizontalLine);
    }

    public static void unmarkTask(String input) throws GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        // mark task as undone
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

        // throw an error if taskNumber is negative, or greater than the current task count
        if (taskNumber < 0 || taskNumber >= taskCount) {
            throw new GavinException("Task number is invalid!!!");
        }
        tasks[taskNumber].markAsNotDone();
        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet!");
        System.out.println("[" + tasks[taskNumber].getStatusIcon() + "]" + " " + tasks[taskNumber].description);
        System.out.println(horizontalLine);
    }

    public static void toDoTask(String input) throws GavinException {
        String[] inputParts = input.split(" ", 2);

        // throw an error if description is empty
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new GavinException("A ToDo must have a description!!!");
        }

        String taskDescription = inputParts[1];
        tasks[taskCount] = new ToDos(taskDescription);
        taskCount++;
        printAddTaskMessage(tasks[taskCount - 1], taskCount);
    }

    public static void deadlineTask(String input) throws GavinException {
        // throw an error if there is no description
        if (input.length() <= 9) {
            throw new GavinException("A deadline must have a description and a /by date!!!");
        }

        String[] inputParts = input.split("/by", 2); // ["deadline return book" , "Sunday"]
        String taskDescription = inputParts[0].substring(9); // skip first 9 chars, which is "deadline "
        String deadlineDay = inputParts[1];

        // throw an error if there is no /by date
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new GavinException("A deadline must have a /by date!!!");
        }
        tasks[taskCount] = new Deadline(taskDescription, deadlineDay);
        taskCount++;
        printAddTaskMessage(tasks[taskCount - 1], taskCount);
    }

    public static void eventTask(String input) throws GavinException {
        // throw an error if there is no taskDescription
        if (input.length() <= 6) {
            throw new GavinException("An event must have a task description!!!");
        }

        String[] inputParts = input.split("/from", 2); //["event project meeting" , "Mon 2pm /to 4pm"]
        String taskDescription = inputParts[0].substring(6);
        String[] timeParts = inputParts[1].split("/to", 2); //["Mon 2pm" , "4pm"]
        String fromTime = timeParts[0];
        String toTime = timeParts[1];

        // throw an error if there is either no 'from' or 'to' time
        if (timeParts.length < 2) {
            throw new GavinException("An event must have a /from and /to time!!!");
        }
        tasks[taskCount] = new Event(taskDescription, fromTime, toTime);
        taskCount++;
        printAddTaskMessage(tasks[taskCount - 1], taskCount);
    }

    private static void printAddTaskMessage(Task task, int taskCount) {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(horizontalLine);

    }
}
