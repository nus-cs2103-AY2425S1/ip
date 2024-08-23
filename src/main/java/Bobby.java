import java.util.Scanner;

public class Bobby {

    /**
     * This function greets the user.
     */
    private static void greet() {
        String greeting = "Hello I'm Bobby\n"
                + "What can I do for you today?";
        System.out.println(greeting);
    }

    /**
     * This function exits the chat app with a default message.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * This function takes in the user input and prints out the input.
     * @param String input
     */
    private static void echo(String input) {
        System.out.println(input);
    }

    private static Task[] tasks = new Task[100];
    private static int count = 0;

    private static void addTask(Task task) {
        if (count < tasks.length) {
            tasks[count] = task;
            count++;
            System.out.println("added: " + task);
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

    private static void printTasks() {
        if (count == 0) {
            System.out.println("No tasks added to the list yet.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(String.format("%d.%s", i + 1, tasks[i]));
            }
        }
    }

private static void handleMarkTask(String userInput) throws InvalidTaskNumberException {
    int taskNumber = Integer.parseInt(userInput.substring(5).trim()) - 1;
    if (taskNumber < 0 || taskNumber >= count) {
        throw new InvalidTaskNumberException();
    }
    tasks[taskNumber].markTask();
    System.out.println("Nice! I've marked this task as done: " + tasks[taskNumber]);
}

    private static void handleUnmarkTask(String userInput) throws InvalidTaskNumberException {
        int taskNumber = Integer.parseInt(userInput.substring(7).trim()) - 1;
        if (taskNumber < 0 || taskNumber >= count) {
            throw new InvalidTaskNumberException();
        }
        tasks[taskNumber].unmarkTask();
        System.out.println("OK, I've marked this task as not done yet: " + tasks[taskNumber]);
    }
    private static void handleTask(String userInput) throws BobbyException {
        if (userInput.startsWith("todo ")) {
            String description = userInput.substring(5).trim();
            if (description.isEmpty()) {
                throw new EmptyTodoException();
            }
            Task task = new Todo(description);
            addTask(task);
        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new EmptyDeadlineException();
            }
            String description = parts[0];
            String by = parts[1];
            Task task = new Deadline(description, by);
            addTask(task);
        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split(" /from | /to ");
            if (parts.length < 3) {
                throw new EmptyEventException();
            }
            String description = parts[0];
            String from = parts[1];
            String to = parts[2];
            Task task = new Event(description, from, to);
            addTask(task);
        } else {
            throw new InvalidInputException();
        }
    }

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    greet();
    while (true) {
        String userInput = scanner.nextLine();
        try {
            if (userInput.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printTasks();
            } else if (userInput.startsWith("mark ")) {
                handleMarkTask(userInput);
            } else if (userInput.startsWith("unmark ")) {
                handleUnmarkTask(userInput);
            } else {
                handleTask(userInput);
            }
        } catch (BobbyException e) {
            System.out.println("OOPS!!! " + e.getMessage());
        }
    }
    scanner.close();
}
}
