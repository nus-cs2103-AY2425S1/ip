import java.util.Scanner;

public class Optimus {
    private static Task[] taskList = new Task[100]; //stores list of tasks
    private static int taskCount = 0; //tracks number of tasks
    public static void main(String[] args) {
        String greeting = "Hello! I'm Optimus.\n" +
                "What can I do for you?\n";
        System.out.println(greeting); //greeting for user
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
            } else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ");
                int taskIndex = Integer.parseInt(parts[1]) - 1; // Get the index (1-based index to 0-based)
                if (taskIndex + 1 > taskCount) {
                    System.out.println("Sorry, you only have up to task number " + taskCount);
                    continue;
                }
                markTaskAsDone(taskIndex);
            }else {
                // else add user task to taskList array and echo "added: <task>"
                addTask(new Task(userInput));
            }
        }
    }

    //add task to taskList array upon user input
    private static void addTask(Task task) {
        taskList[taskCount] = task;
        taskCount++;
        System.out.println("added: " + task.description + "\n");
    }

    //list out tasks from taskList array
    private static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
        }
    }

    private static void markTaskAsDone(int taskIndex) {
        taskList[taskIndex].isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskList[taskIndex].description);
    }
}
