import java.util.Scanner;

public class Optimus {
    //stores list of tasks
    private static String[] taskList = new String[100];
    //tracks number of tasks
    private static int taskCount = 0;
    public static void main(String[] args) {
        //greeting for user
        String greeting = "Hello! I'm Optimus.\n" +
                "What can I do for you?\n";
        System.out.println(greeting);
        //Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        //while loop to keep taking in user input
        while (true) {
            String userInput = scanner.nextLine();

            //exit program if user enters "bye"
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                scanner.close();
                System.exit(0);
            }

            //if user inputs "list", list out all existing tasks from taskList array
            if (userInput.equals("list")) {
                listTasks();
            } else {
                // else add user task to taskList array and echo "added: <task>"
                addTask(userInput);
            }
        }
    }

    //add task to taskList array upon user input
    private static void addTask(String task) {
        taskList[taskCount] = task;
        taskCount++;
        System.out.println("added: " + task + "\n");
    }

    //list out tasks from taskList array
    private static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
    }
}
