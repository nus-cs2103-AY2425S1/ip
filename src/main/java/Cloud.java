import java.util.Scanner;

public class Cloud {

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


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        printHorizLine();
        greet();
        while (true) {
            printHorizLine();
            String userInput = sc.nextLine().strip();
            // exit chat if user enters exit command
            if (userInput.equals(EXIT_COMMAND)) {
                break;
            }
            // print out the task list
            if (userInput.equals("list")) {
                System.out.println(tasks);
                continue;
            }
            // check the first word in the command
            String[] commandString = userInput.split(" ", 1);
            String command = commandString[0];
            int taskNum;
            switch (command) {
                case "mark":
                    taskNum = Integer.parseInt(commandString[1]);
                    tasks.mark(taskNum);
                    System.out.println("Task marked as done!");
                    System.out.println(tasks.getTaskStatus(taskNum));
                    break;
                case "unmark":
                    taskNum = Integer.parseInt(commandString[1]);
                    tasks.unmark(taskNum);
                    System.out.println("Task marked as not done");
                    System.out.println(tasks.getTaskStatus(taskNum));
                    break;
                case "todo":
                    Todo todo = new Todo(commandString[1].strip());
                    tasks.add(todo);
                    System.out.println("added: " + userInput);

            }
            // save each input to the task list
            tasks.add(userInput);

        }
        exit();
        printHorizLine();
    }
}
