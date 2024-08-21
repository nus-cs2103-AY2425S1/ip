import java.util.Scanner;

public class PandaBot {
    private static void printLine() {
        String line = "_________________________________________";
        System.out.println(line);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // statically storing tasks
        Task[] taskList = new Task[100];
        int taskCount = 0;

        // Simple greeting to the user by PandaBot
        printLine();
        System.out.println("Hello! I'm PandaBot");
        System.out.println("What can I do for you?");
        printLine();

        // Echo user input until the user inputs "bye"
        while (true) {
            String input = scanner.nextLine();

            // if user has said "bye", stop echo and exit
            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equalsIgnoreCase("list")){
                printLine();
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
                printLine();
            } else if (input.startsWith("mark ")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskCount) {
                    taskList[taskNum].markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList[taskNum]);
                    printLine();
                } else {
                    System.out.println("The specified task does not exist");
                }
            } else if (input.startsWith("unmark ")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskCount) {
                    taskList[taskNum].unmark();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskList[taskNum]);
                    printLine();
                } else {
                    System.out.println("The specified task does not exist");
                }
            } else {
                taskList[taskCount] = new Task(input);
                taskCount++;

                // echo user input
                printLine();
                System.out.println("added: " + input);
                printLine();
            }
        }
        scanner.close();
    }
}
