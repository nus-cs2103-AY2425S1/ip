import java.util.Scanner;

public class PandaBot {
    public static void main(String[] args) {
        String line = "_________________________________________";
        Scanner scanner = new Scanner(System.in);

        // statically storing tasks
        Task[] taskList = new Task[100];
        int taskCount = 0;

        // Simple greeting to the user by PandaBot
        System.out.println(line);
        System.out.println("Hello! I'm PandaBot");
        System.out.println("What can I do for you?");
        System.out.println(line);

        // Echo user input until the user inputs "bye"
        while (true) {
            String input = scanner.nextLine();

            // if user has said "bye", stop echo and exit
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")){
                System.out.println(line);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskCount) {
                    taskList[taskNum].markAsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList[taskNum]);
                    System.out.println(line);
                } else {
                    System.out.println("The specified task does not exist");
                }
            } else if (input.startsWith("unmark ")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskCount) {
                    taskList[taskNum].unmark();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskList[taskNum]);
                    System.out.println(line);
                } else {
                    System.out.println("The specified task does not exist");
                }
            } else {
                taskList[taskCount] = new Task(input);
                taskCount++;

                // echo user input
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
        scanner.close();
    }
}
