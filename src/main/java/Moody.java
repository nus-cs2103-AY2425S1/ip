import java.util.Scanner;

public class Moody {
    private static final String SPACER = "____________________________________________________________\n";
    private static final String INDENT = "    ";
    private static Task[] userTasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println(SPACER + "Hello! I'm Moody!\nWhat can I do for you?\n" + SPACER);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            try {
                userInput = scanner.nextLine().trim();
                if (userInput.equals("bye")) {
                    sayGoodbye();
                    break;
                } else if (userInput.equals("list")) {
                    listTasks();
                } else if (userInput.startsWith("mark ")) {
                    markTask(userInput);
                } else if (userInput.startsWith("unmark ")) {
                    unmarkTask(userInput);
                } else if (userInput.startsWith("todo")) {
                    addTodoTask(userInput);
                } else if (userInput.startsWith("deadline")) {
                    addDeadlineTask(userInput);
                } else if (userInput.startsWith("event")) {
                    addEventTask(userInput);
                } else {
                    invalidCommand();
                }
            } catch (TaskInputException | InvalidCommandException | TaskOutOfBoundsException e) {
                System.out.println(SPACER + e.getMessage() + SPACER);
            }
        }
        scanner.close();
    }

    private static void sayGoodbye() {
        System.out.println(SPACER + "Bye. Hope to see you again soon!\n" + SPACER);
    }

    private static void listTasks() {
        System.out.print(SPACER);
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + userTasks[i]);
        }
        System.out.println(SPACER);
    }

    private static void markTask(String userInput) throws TaskOutOfBoundsException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

        // to handle array out-of-bounds cases when accessing userTasks array
        if (taskNumber >= taskCount || taskNumber < 0) {
            throw new TaskOutOfBoundsException("""
                    Error: Cannot mark a task that does not exist
                    
                    Please check that you are marking the correct task number.
                    """);
        }

        userTasks[taskNumber].markAsDone();
        System.out.println(SPACER + "Nice! I've marked this task as done:");
        System.out.println(INDENT + userTasks[taskNumber].toString());
        System.out.println(SPACER);
    }

    private static void unmarkTask(String userInput) throws TaskOutOfBoundsException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

        // to handle array out-of-bounds cases when accessing userTasks array
        if (taskNumber >= taskCount || taskNumber < 0) {
            throw new TaskOutOfBoundsException("""
                    Error: Cannot mark a task that does not exist
                    
                    Please check that you are marking the correct task number.
                    """);
        }

        userTasks[taskNumber].markAsNotDone();
        System.out.println(SPACER + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + userTasks[taskNumber].toString());
        System.out.println(SPACER);
    }

    private static void addTodoTask(String userInput) throws TaskInputException, InvalidCommandException {
        // to handle empty description case
        if (userInput.equals("todo")) {
            throw new TaskInputException("""
                    Error: Missing task description
                    
                    Please use the following format: todo <description>
                    """);
        }

        // to handle cases where userInput has same prefix but different command
        if (!userInput.startsWith("todo ")) {
            invalidCommand();
        }

        String description = userInput.substring(5).trim();
        userTasks[taskCount] = new Todo(description);
        taskCount++;
        System.out.println(SPACER + "Got it. I've added this task:\n" + INDENT + userTasks[taskCount - 1] + "\n"
                + "Now you have " + taskCount + " tasks in the list.\n" + SPACER);
    }

    private static void addDeadlineTask(String userInput) throws TaskInputException, InvalidCommandException {
        // to handle empty description and date case
        if (userInput.equals("deadline")) {
            throw new TaskInputException("""
                    Error: Missing deadline description and date
                    
                    Please use the following format: deadline <description> /by <date>
                    """);
        }

        // to handle cases where userInput has same prefix but different command
        if (!userInput.startsWith("deadline ")) {
            invalidCommand();
        }

        String[] substrings = userInput.substring(9).split(" /by ");

        // to handle empty date case (i.e. wrong format)
        if (substrings.length != 2) {
            throw new TaskInputException("""
                    Error: Wrong input format
                    
                    Please use the following format: deadline <name> /by <date>
                    """);
        }

        userTasks[taskCount] = new Deadline(substrings[0].trim(), substrings[1].trim());
        taskCount++;
        System.out.println(SPACER + "Got it. I've added this task:\n" + INDENT + userTasks[taskCount - 1] + "\n"
                + "Now you have " + taskCount + " tasks in the list.\n" + SPACER);
    }

    private static void addEventTask(String userInput) throws TaskInputException, InvalidCommandException {
        // to handle empty description and date case
        if (userInput.equals("event")) {
            throw new TaskInputException("""
                    Error: Missing event description and date
                    
                    Please use the following format: event <name> /from <date> <time> /to <date> <time>
                    """);
        }

        // to handle cases where userInput has same prefix but different command
        if (!userInput.startsWith("event ")) {
            invalidCommand();
        }

        String[] substrings = userInput.substring(5).split(" /from | /to ");

        // to handle empty date(s) case
        if (substrings.length != 3) {
            throw new TaskInputException("""
                    Error: Wrong input format
                    
                    Please use the following format: event <name> /from <date> <time> /to <date> <time>
                    """);

        }

        userTasks[taskCount] = new Event(substrings[0].trim(), substrings[1].trim(), substrings[2].trim());
        taskCount++;
        System.out.println(SPACER + "Got it. I've added this task:\n" + INDENT + userTasks[taskCount - 1] + "\n"
                + "Now you have " + taskCount + " tasks in the list.\n" + SPACER);
    }

    private static void invalidCommand() throws InvalidCommandException {
        throw new InvalidCommandException("""
                Error: Command not found
                
                Please input a valid command
                """);
    }
}
