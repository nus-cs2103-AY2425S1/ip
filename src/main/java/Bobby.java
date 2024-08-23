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

    private static void add_task(Task task) {
        if (count < tasks.length) {
            tasks[count] = task;
            count++;
            System.out.println("added: " + task);
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

    private static void print_task() {
        if (count == 0) {
            System.out.println("No tasks added to the list yet.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(String.format("%d.%s", i + 1, tasks[i]));
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            // Ask the user for input
            System.out.print("Enter something (type 'bye' to quit): ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                print_task();
            } else if (userInput.startsWith("mark ")) {

                int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                tasks[taskNumber].markTask();
                System.out.println("Nice! I've marked this task as done: " + tasks[taskNumber]);
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                tasks[taskNumber].unmarkTask();
                System.out.println("OK, I've marked this task as not done yet: " + tasks[taskNumber]);
            } else if (userInput.startsWith("todo ")){
                String description = userInput.substring(5);
                Task task = new Todo(description);
                add_task(task);
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                String description = parts[0];
                String by = parts[1];
                Task task = new Deadline(description, by);
                add_task(task);
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                String description = parts[0];
                String from = parts[1];
                String to = parts[2];
                Task task = new Event(description, from, to);
                add_task(task);
            }
        }
    }
}
