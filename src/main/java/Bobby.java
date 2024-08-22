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

    private static void add_task(String task) {
        if (count < tasks.length) {
            tasks[count] = new Task(task);
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
                System.out.println(String.format("%d.[%s] %s", i + 1, tasks[i].getStatusIcon(), tasks[i]));
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
                System.out.println("Nice! I've marked this task as done: " + tasks[taskNumber]);
                tasks[taskNumber].markTask();
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                System.out.println("OK, I've marked this task as not done yet: " + tasks[taskNumber]);
                tasks[taskNumber].unmarkTask();
            } else {
                add_task(userInput);
            }
        }
    }
}
